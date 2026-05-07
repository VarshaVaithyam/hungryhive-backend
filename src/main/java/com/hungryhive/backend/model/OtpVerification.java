package com.hungryhive.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "otp_verifications")
public class OtpVerification {

    @Id
    private String id;

    private String phoneNumber;

    private String otp;

    private LocalDateTime expiresAt;

    private boolean verified;

    public OtpVerification() {
    }

    public OtpVerification(String phoneNumber,
                           String otp,
                           LocalDateTime expiresAt,
                           boolean verified) {

        this.phoneNumber = phoneNumber;
        this.otp = otp;
        this.expiresAt = expiresAt;
        this.verified = verified;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOtp() {
        return otp;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}