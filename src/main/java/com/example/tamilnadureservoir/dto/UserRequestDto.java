package com.example.tamilnadureservoir.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.example.tamilnadureservoir.model.User} entity
 */
@Data
public class UserRequestDto  {

    private Long id;
    private String username;
    private String password;
    private String plainPassword;
    private String email;
    private String phone;
    private String name;
    private String businessTitle;
    private Set<Long> roles;
    private Set<Long> reservoirs;
//
//    /**
//     * A DTO for the {@link com.example.tamilnadureservoir.model.Role} entity
//     */
//    @Data
//    public static class RoleDto implements Serializable {
//        private long id;
//    }
//
//    /**
//     * A DTO for the {@link com.example.tamilnadureservoir.model.Reservoir} entity
//     */
//    @Data
//    public static class ReservoirDto implements Serializable {
//        private Long id;
//    }
}