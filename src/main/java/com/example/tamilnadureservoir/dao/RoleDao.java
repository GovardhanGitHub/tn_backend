package com.example.tamilnadureservoir.dao;

import com.example.tamilnadureservoir.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findRoleByName(String name);
}