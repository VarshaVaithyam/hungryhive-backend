package com.hungryhive.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "foods")
public class Food {

    @Id
    private String id;

    private String foodName;
    private String quantity;
    private String location;
    private String donorName;
    private String phoneNumber;
    private String expiryTime;
    private String description;

    // Status of donation
    private String status;
    private String organization;

    // Created time
    private LocalDateTime createdAt;

    // Default constructor
    public Food() {
        this.createdAt = LocalDateTime.now();
        this.status = "AVAILABLE";
    }

    // Getters
    public String getId() { return id; }

    public String getFoodName() { return foodName; }

    public String getOrganization() { return organization; }

    public String getQuantity() { return quantity; }

    public String getLocation() { return location; }

    public String getDonorName() { return donorName; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getExpiryTime() { return expiryTime; }

    public String getDescription() { return description; }

    public String getStatus() { return status; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(String id) { this.id = id; }

    public void setFoodName(String foodName) { this.foodName = foodName; }

    public void setOrganization(String organization) { this.organization = organization; }

    public void setQuantity(String quantity) { this.quantity = quantity; }

    public void setLocation(String location) { this.location = location; }

    public void setDonorName(String donorName) { this.donorName = donorName; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setExpiryTime(String expiryTime) { this.expiryTime = expiryTime; }

    public void setDescription(String description) { this.description = description; }

    public void setStatus(String status) { this.status = status; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    private String ownerUserId;
    public String getOwnerPhone() {
        return ownerUserId;
    }
    public void setOwnerPhone(String ownerPhone) {
        this.ownerUserId = ownerPhone;
    }
}