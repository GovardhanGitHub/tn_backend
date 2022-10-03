package com.example.tamilnadureservoir.dao;

import com.example.tamilnadureservoir.model.Reservoir;
import com.example.tamilnadureservoir.model.ReservoirEveryDayUpdate;
import com.example.tamilnadureservoir.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservoirEveryDayUpdateRepository extends JpaRepository<ReservoirEveryDayUpdate, Long> {
    List<ReservoirEveryDayUpdate> findByReservoir(Reservoir reservoir);
    ReservoirEveryDayUpdate findByDate(String date);

    ReservoirEveryDayUpdate findByDate(LocalDate date);
}