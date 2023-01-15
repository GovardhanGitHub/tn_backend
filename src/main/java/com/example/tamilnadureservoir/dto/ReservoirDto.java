package com.example.tamilnadureservoir.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservoirDto implements Serializable {
//    public MultipartFile image;

    public Long id;

    public Long imageId;



    public String name;
    public String region;
    public List<KeyValuePairDto> keyValuePairs;
    private Double capacity;
    private Double fullHeight;

    @Data
    public static class KeyValuePairDto implements Serializable {
        public Long id;
        public String keyType;
        public String value;
    }
}
