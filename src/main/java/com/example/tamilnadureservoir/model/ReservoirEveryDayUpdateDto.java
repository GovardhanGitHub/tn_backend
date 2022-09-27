package com.example.tamilnadureservoir.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link ReservoirEveryDayUpdate} entity
 */
@Data
public class ReservoirEveryDayUpdateDto implements Serializable {
    private Long id;
    private Long reservoirId;
    private Long userId;
    private Double fullHeight;
    private Double capacity;
    private LocalDate date;
    private Double presentDepthOfStorage;
    private Double presentStorage;
    private Double inflow;
    private Double outflow;
    private Double rainfall;

    /**
     * A DTO for the {@link Reservoir} entity
     *//*
    @Data
    public static class ReservoirDto implements Serializable {
        private  Long id;
    }

    *//**
     * A DTO for the {@link User} entity
     *//*
    @Data
    public static class UserRequestDto implements Serializable {
        private  long id;
        private  String username;
    }*/
}