package com.example.tamilnadureservoir.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Where(clause="is_deleted=0")
public class Reservoir extends BaseEntity {


    private Boolean isDeleted = Boolean.FALSE;
    private String name;
    private String region;
    private Double capacity;
    private Double fullHeight;

    @OneToOne(cascade = CascadeType.ALL,  orphanRemoval = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private ImageModel imageModel;


}
