package com.hungryhive.backend.service;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromNumber;

    public void sendOtp(String phone, String otp) {
        try {
            Twilio.init(accountSid, authToken);

            String formattedPhone = formatPhoneNumber(phone);

            Message message = Message.creator(
                    new PhoneNumber(formattedPhone),
                    new PhoneNumber(fromNumber),
                    "Your Hungry Hive OTP is: " + otp + ". It is valid for 5 minutes."
            ).create();

            System.out.println("OTP SMS sent successfully.");
            System.out.println("Twilio Message SID: " + message.getSid());

        } catch (ApiException e) {
            System.out.println("Twilio API Error: " + e.getMessage());
            throw new RuntimeException("Failed to send OTP SMS: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("SMS Error: " + e.getMessage());
            throw new RuntimeException("Something went wrong while sending OTP SMS.");
        }
    }

    private String formatPhoneNumber(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new RuntimeException("Phone number cannot be empty");
        }

        phone = phone.trim();

        if (phone.startsWith("+")) {
            return phone;
        }

        if (phone.length() == 10) {
            return "+91" + phone;
        }

        return phone;
    }
}