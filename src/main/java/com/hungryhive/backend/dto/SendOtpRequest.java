package com.hungryhive.backend.dto;

public class SendOtpRequest {

    private String phoneNumber;

    public SendOtpRequest() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}