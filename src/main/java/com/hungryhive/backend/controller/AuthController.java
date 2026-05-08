package com.hungryhive.backend.controller;

import com.hungryhive.backend.dto.SendOtpRequest;
import com.hungryhive.backend.dto.VerifyOtpRequest;
import com.hungryhive.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Test endpoint for Render deployment check
    @GetMapping("/ping")
    public String ping() {
        return "Auth controller working";
    }

    @PostMapping("/send-otp")
    public ResponseEntity<Map<String, Object>> sendOtp(@RequestBody SendOtpRequest request) {
        try {
            return ResponseEntity.ok(
                    authService.sendOtp(request.getPhoneNumber())
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", e.getMessage())
            );
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, Object>> verifyOtp(@RequestBody VerifyOtpRequest request) {
        try {
            return ResponseEntity.ok(
                    authService.verifyOtp(
                            request.getPhoneNumber(),
                            request.getOtp()
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", e.getMessage())
            );
        }
    }
}