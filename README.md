# Hungry Hive Backend

## Overview

Hungry Hive is a full-stack food sharing and donation platform designed to reduce food waste by connecting food donors with individuals or communities in need.

This repository contains the backend service built using Spring Boot. The backend handles authentication, food management, database operations, authorization, and API communication with the Flutter mobile application.

---

# Features

## Authentication & Security
- OTP-based login using Twilio SMS API
- Secure user verification flow
- JWT-based authentication support
- User session persistence
- Owner-based authorization for food deletion

## Food Management
- Add food donation details
- View all food listings
- Retrieve available food items
- Food status management
- Mark food as deleted without removing data from database
- Restrict deletion access to food owner only

## Database & Cloud Integration
- MongoDB Atlas cloud database integration
- Persistent food storage
- Cloud-hosted backend deployment using Render

## REST API
- Structured RESTful API architecture
- JSON request/response handling
- Cross-Origin Resource Sharing (CORS) enabled

---

# Tech Stack

| Technology | Usage |
|---|---|
| Java 17 | Backend language |
| Spring Boot | REST API framework |
| MongoDB Atlas | Cloud database |
| Twilio | OTP SMS authentication |
| Maven | Dependency management |
| JWT | Authentication token handling |
| Render | Cloud deployment |

---

# Project Architecture

```text
Flutter App
     ↓
Spring Boot REST API
     ↓
MongoDB Atlas Database
     ↓
Twilio OTP Service

---

# Live Backend URL

```text
https://hungryhive-backend-f08i.onrender.com

---

# Main APIs

POST /auth/send-otp
POST /auth/verify-otp
POST /food/add
GET  /food/all
GET  /food/available
PUT  /food/delete/{id}?userId={userId}

---

# Environmental Variables

SPRING_DATA_MONGODB_URI
TWILIO_ACCOUNT_SID
TWILIO_AUTH_TOKEN
TWILIO_PHONE_NUMBER

---

# Frontend Repository
https://github.com/VarshaVaithyam/hungryhive-frontend

---

# Project Status
Fully deployed backend connected with:

Flutter frontend
MongoDB Atlas
Twilio OTP authentication
Render cloud hosting

---

# NOTE

The backend is deployed using Render free-tier hosting.  
Initial API requests may take a few seconds if the server is inactive due to cold-start behavior.

