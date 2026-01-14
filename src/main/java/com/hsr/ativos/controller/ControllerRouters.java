package com.hsr.ativos.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/save")
    public void saveRouters(@RequestBody RoutersDTO routersDTO){
        serviceRouters.saveRouters(routersDTO);
    }
    @PutMapping("/update/{id}")
    public void updateRouters(@PathVariable UUID id, @RequestBody RoutersDTO routersDTO){
        serviceRouters.updateRouters(id, routersDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteRouters(@PathVariable UUID id){
        serviceRouters.deleteRouters(id);
    }
}
