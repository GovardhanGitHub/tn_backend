package com.example.tamilnadureservoir.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
public class KeyValuePair extends BaseEntity {


    private String keyType;

    private String value;
}