package com.hsr.ativos.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hsr.ativos.services.ServiceMachines;

@RestController
@RequestMapping("/machines")
public class ControllerMachines {

    private final ServiceMachines serviceMachines;

    public ControllerMachines(ServiceMachines serviceMachines) {
        this.serviceMachines = serviceMachines;
    }
    @GetMapping("/all")
    public ResponseEntity<Iterable<?>> getAllMachines() {
        Iterable<?> machines = serviceMachines.getAllMachines();
        return ResponseEntity.ok(machines);
    }
}
