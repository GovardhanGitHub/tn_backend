package com.example.tamilnadureservoir.dao;

import com.example.tamilnadureservoir.model.Reservoir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservoirRepository extends JpaRepository<Reservoir, Long> {

}