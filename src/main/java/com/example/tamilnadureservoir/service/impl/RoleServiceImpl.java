package com.example.tamilnadureservoir.service.impl;

import com.example.tamilnadureservoir.dao.RoleDao;
import com.example.tamilnadureservoir.model.Role;
import com.example.tamilnadureservoir.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        Role role = roleDao.findRoleByName(name);
        return role;
    }

    @Override
    public Iterable<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Optional<Role> findRoleById(Long id) {
        return roleDao.findById(id);
    }
}
