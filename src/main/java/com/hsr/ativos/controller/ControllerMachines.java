package com.hsr.ativos.controller;



import java.util.List;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.dtos.MachineDTO;
import com.hsr.ativos.services.ServiceMachines;

@RestController
@RequestMapping("/machines")
@CrossOrigin(origins = "http://localhost:8081")
public class ControllerMachines {

    private final ServiceMachines service;

    public ControllerMachines(ServiceMachines service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<MachineDTO> getAllMachines() {
        return service.getAllMachines();
    }

}
