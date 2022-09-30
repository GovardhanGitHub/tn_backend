package com.example.tamilnadureservoir.model;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Data
public class ReservoirEveryDayUpdate  extends BaseEntity{


    @OneToOne
    private Reservoir reservoir;

    @OneToOne
    private User user;


//    @Column(unique = true, nullable = false)
    private LocalDate date;

    private Double fullHeight;
    private Double capacity;
    private Double presentDepthOfStorage;
    private Double presentStorage;
    private Double inflow;
    private Double outflow;
    private Double rainfall;


}
