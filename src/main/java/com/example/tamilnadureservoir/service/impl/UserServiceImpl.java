package com.example.tamilnadureservoir.service.impl;

import java.util.*;

import com.example.tamilnadureservoir.dao.ReservoirRepository;
import com.example.tamilnadureservoir.dao.RoleDao;
import com.example.tamilnadureservoir.dao.UserDao;
import com.example.tamilnadureservoir.dto.UserRequestDto;
import com.example.tamilnadureservoir.model.Reservoir;
import com.example.tamilnadureservoir.model.Role;
import com.example.tamilnadureservoir.model.User;
import com.example.tamilnadureservoir.model.UserDto;
import com.example.tamilnadureservoir.service.RoleService;
import com.example.tamilnadureservoir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;


    @Autowired
    private ReservoirRepository reservoirRepository;

    @Autowired
    private RoleDao roleDao;


    @Autowired
    private UserDao userDao;

    @Lazy
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User save(UserDto user) {

        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        nUser.setPlainPassword(user.getPassword());
        // 1. Admin 2.User
        Role role = null;
        Set<Role> roleSet = new HashSet<>();
        if (user.getRoles() != null) {
            if (user.getRoles() == 2) {
                role = roleService.findByName("USER");
                roleSet.add(role);
            }
            if (user.getRoles() == 1) {
                role = roleService.findByName("ADMIN");
                roleSet.add(role);
            }
        }
        nUser.setRoles(roleSet);
        return userDao.save(nUser);
    }

    @Override
    public User disableReservoir(Long maintainerId) {
        Optional<User> optionalUser = userDao.findById(maintainerId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setReservoirs(null);
            User savedUser = userDao.save(user);
            return savedUser;
        }
        return null;
    }


    @Override
    public User update(UserRequestDto dto) {


        User nUser = null;
        Optional<User> optionalUser = userDao.findById(dto.getId());
        if (optionalUser.isPresent()) {


            nUser = optionalUser.get();
            nUser.setUsername(dto.getUsername());
            nUser.setName(dto.getName());
            nUser.setPhone(dto.getPhone());
            nUser.setEmail(dto.getEmail());
            nUser.setPlainPassword(dto.getPassword());
            nUser.setPassword(bcryptEncoder.encode(dto.getPassword()));


            Set<Reservoir> reservoirs = new HashSet<>();
            Optional<Long> optionalReservoirIds = dto.getReservoirs().stream().findFirst();
            if (optionalReservoirIds.isPresent()) {
                Optional<Reservoir> optionalReservoir = reservoirRepository.findById(optionalReservoirIds.get());
                optionalReservoir.ifPresent(reservoirs::add);
            }
            nUser.setReservoirs(reservoirs);


            Set<Role> roles = new HashSet<>();
            Optional<Long> optionalRoleIds = dto.getRoles().stream().findFirst();
            if (optionalRoleIds.isPresent()) {
                Optional<Role> optionalRole = roleDao.findById(optionalRoleIds.get());
                optionalRole.ifPresent(roles::add);
            }
            nUser.setRoles(roles);


            return userDao.save(nUser);
        } else return null;
    }


}
