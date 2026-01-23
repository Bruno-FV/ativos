package com.hsr.ativos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
     @GetMapping("/profile")
    public String userProfile() {
        return "Perfil do usuário autenticado";
    }
}
