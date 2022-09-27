package com.example.tamilnadureservoir.dto;

import com.example.tamilnadureservoir.model.User;
import lombok.Data;
import org.springframework.security.core.Authentication;


@Data
public class ResponseLoginDto{
    private Authentication authentication;
    private String token;
}