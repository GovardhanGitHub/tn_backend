package com.example.tamilnadureservoir.service;

import com.example.tamilnadureservoir.dto.UserRequestDto;
import com.example.tamilnadureservoir.model.User;
import com.example.tamilnadureservoir.model.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);

    User disableReservoir(Long maintainerId);

    User update(UserRequestDto dto);
}
