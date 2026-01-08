package com.hsr.ativos.controller;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.dtos.RoutersDTO;
import com.hsr.ativos.services.ServiceRouters;

@RestController
@RequestMapping("/routers")

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
