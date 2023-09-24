package com.example.tamilnadureservoir.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.tamilnadureservoir.model.ConferenceData;

@RepositoryRestResource(path = "conferences")
public interface ConferenceDataRepository extends JpaRepository<ConferenceData, Long> {
    Optional<ConferenceData> findByPhone(String phone); // Change 'phone' to your actual field name
}
