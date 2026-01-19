package com.hsr.ativos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.enums.Role;
import com.hsr.ativos.models.Users;
import com.hsr.ativos.repositorys.UserRepo;

@RestController
@RequestMapping("/auth")
public class AuthController {
     private final UserRepo repository;
    private final PasswordEncoder encoder;

    public AuthController(UserRepo repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {

        user.setPassword(encoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole(Role.ROLE_USER);
        }

        repository.save(user);
        return ResponseEntity.ok("Usuário cadastrado");
    }
    
}
