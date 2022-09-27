package com.example.tamilnadureservoir.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ReservoirDto implements Serializable {
    public Long id;
    public String name;
    public String region;
    public List<KeyValuePairDto> keyValuePairs;
    private Long capacity;
    private Long fullHeight;

    @Data
    public static class KeyValuePairDto implements Serializable {
        public Long id;
        public String keyType;
        public String value;
    }
}
