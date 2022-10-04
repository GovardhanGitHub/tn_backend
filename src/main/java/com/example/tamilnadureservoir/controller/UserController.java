package com.example.tamilnadureservoir.controller;

import com.example.tamilnadureservoir.config.TokenProvider;
import com.example.tamilnadureservoir.dto.ResponseLoginDto;
import com.example.tamilnadureservoir.dto.UserRequestDto;
import com.example.tamilnadureservoir.model.LoginUser;
import com.example.tamilnadureservoir.model.Role;
import com.example.tamilnadureservoir.model.User;
import com.example.tamilnadureservoir.model.UserDto;
import com.example.tamilnadureservoir.service.RoleService;
import com.example.tamilnadureservoir.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResponseLoginDto responseLoginDto = new ResponseLoginDto();
        responseLoginDto.setToken(jwtTokenUtil.generateToken(authentication));
        responseLoginDto.setAuthentication(SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok(responseLoginDto);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public User updateUser(@RequestBody UserRequestDto user) {
        if (user.getId() != null) return userService.update(user);
        return null;
    }


    @GetMapping(value = "/disableReservoir/{id}")
    public ResponseEntity<User> disableReservoir(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.accepted().body(userService.disableReservoir(id));
    }

    @RequestMapping(value = "/findMaintainerByName", method = RequestMethod.POST)
    public User findMaintainerByName(@RequestBody String name) {
        return userService.findOne(name);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/findAllUsers", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userService.findAll();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/findAllRoles", method = RequestMethod.GET)
    public Iterable<Role> findAllRoles() {
        return roleService.findAll();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/adminping", method = RequestMethod.GET)
    public String adminPing() {
        return "Only Admins Can Read This";
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @RequestMapping(value = "/userping", method = RequestMethod.GET)
    public String userPing() {
        return "Any User Can Read This";
    }

}
