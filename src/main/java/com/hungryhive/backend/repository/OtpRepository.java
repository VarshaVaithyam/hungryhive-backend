package com.hungryhive.backend.repository;

import com.hungryhive.backend.model.OtpVerification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends MongoRepository<OtpVerification, String> {

    Optional<OtpVerification> findTopByPhoneNumberOrderByExpiresAtDesc(String phoneNumber);

}