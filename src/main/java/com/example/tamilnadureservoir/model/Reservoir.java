package com.example.tamilnadureservoir.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
public class Reservoir extends BaseEntity {



    private String name;
    private String region;
    private Long capacity;
    private Long fullHeight;


//    @OneToMany(cascade=CascadeType.ALL)
//    private List<KeyValuePair> keyValuePairs;


}
