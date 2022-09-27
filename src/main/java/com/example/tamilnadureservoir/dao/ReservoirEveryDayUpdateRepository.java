package com.example.tamilnadureservoir.dao;

import com.example.tamilnadureservoir.model.Reservoir;
import com.example.tamilnadureservoir.model.ReservoirEveryDayUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservoirEveryDayUpdateRepository extends JpaRepository<ReservoirEveryDayUpdate, Long> {
    List<ReservoirEveryDayUpdate> findByReservoir(Reservoir reservoir);
    ReservoirEveryDayUpdate findByDate(String date);
}