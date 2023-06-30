package com.thalescoding.registerAPI.controller;

import com.thalescoding.registerAPI.model.Role;
import com.thalescoding.registerAPI.repository.RoleRepository;
import com.thalescoding.registerAPI.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserServiceInterface userService;

    @GetMapping("/")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok().body(roleRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
}
