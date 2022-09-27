package com.example.tamilnadureservoir.dao;

import com.example.tamilnadureservoir.model.Reservoir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservoirRepository extends JpaRepository<Reservoir, Long> {
}