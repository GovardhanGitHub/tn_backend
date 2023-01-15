package com.example.tamilnadureservoir.dao;

import java.util.Optional;

import com.example.tamilnadureservoir.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
}