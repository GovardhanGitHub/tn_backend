package com.example.tamilnadureservoir.service;

import com.example.tamilnadureservoir.model.Role;

import java.util.Optional;

public interface RoleService {
    Role findByName(String name);

    Iterable<Role> findAll();

    Optional<Role> findRoleById(Long id);
}
