package com.hsr.ativos.controller;

import java.security.Provider.Service;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.dtos.RoutersDTO;
import com.hsr.ativos.services.ServiceRouters;

@RestController
@RequestMapping("/routers")
@CrossOrigin(origins = "http://localhost:8081")
public class ControllerRouters {
    private final ServiceRouters serviceRouters;

    public ControllerRouters(ServiceRouters serviceRouters) {
        this.serviceRouters = serviceRouters;
    }
    @GetMapping("/all")
    public List<RoutersDTO> getAllRouters() {
        return serviceRouters.getAllRouters();
    }
}
