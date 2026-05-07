package com.hungryhive.backend.service;

import com.hungryhive.backend.model.OtpVerification;
import com.hungryhive.backend.model.User;
import com.hungryhive.backend.repository.OtpRepository;
import com.hungryhive.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final JwtService jwtService;
    private final SmsService smsService;

    public AuthService(UserRepository userRepository,
                       OtpRepository otpRepository,
                       JwtService jwtService,
                       SmsService smsService) {

        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
        this.jwtService = jwtService;
        this.smsService = smsService;
    }

    public Map<String, Object> sendOtp(String phoneNumber) {
        validatePhoneNumber(phoneNumber);

        String otp = generateOtp();

        OtpVerification otpVerification = new OtpVerification(
                phoneNumber,
                otp,
                LocalDateTime.now().plusMinutes(5),
                false
        );

        otpRepository.save(otpVerification);

        smsService.sendOtp(phoneNumber, otp);

        System.out.println("Hungry Hive OTP sent to " + phoneNumber);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "OTP sent successfully");

        // Keep this only while testing.
        // Remove this line in production.
        response.put("otp", otp);

        return response;
    }

    public Map<String, Object> verifyOtp(String phoneNumber, String otp) {
        validatePhoneNumber(phoneNumber);

        OtpVerification latestOtp = otpRepository
                .findTopByPhoneNumberOrderByExpiresAtDesc(phoneNumber)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (latestOtp.isVerified()) {
            throw new RuntimeException("OTP already used");
        }

        if (latestOtp.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        if (!latestOtp.getOtp().equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }

        latestOtp.setVerified(true);
        otpRepository.save(latestOtp);

        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseGet(() -> userRepository.save(
                        new User(phoneNumber, LocalDateTime.now())
                ));

        String token = jwtService.generateToken(user.getId(), user.getPhoneNumber());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("token", token);
        response.put("userId", user.getId());
        response.put("phoneNumber", user.getPhoneNumber());

        return response;
    }

    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("^[0-9]{10}$")) {
            throw new RuntimeException("Phone number must be exactly 10 digits");
        }
    }
}