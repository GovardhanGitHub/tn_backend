package com.example.tamilnadureservoir.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Where(clause="is_deleted=0")
public class Reservoir extends BaseEntity {


    private Boolean isDeleted = Boolean.FALSE;
    private String name;
    private String region;
    private Double capacity;
    private Double fullHeight;


//    @OneToMany(cascade=CascadeType.ALL)
//    private List<KeyValuePair> keyValuePairs;


}
