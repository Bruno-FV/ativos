package com.hsr.ativos.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.dtos.MachineDTO;
import com.hsr.ativos.models.Machines;
import com.hsr.ativos.services.ServiceMachines;

@RestController
@RequestMapping("/machines")

public class ControllerMachines {

    private final ServiceMachines service;

    public ControllerMachines(ServiceMachines service) {
        this.service = service;
    }

    @GetMapping("/all")
    ResponseEntity<List<MachineDTO>> getAllMachines() {
        List<MachineDTO> machines = service.getAllMachines();
        return ResponseEntity.ok(machines);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveMachine(@RequestBody MachineDTO machineDTO) {
        return service.saveMachines(machineDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Machines> updateMachine(@PathVariable UUID id, @RequestBody MachineDTO machineDTO) {
        Machines updatedMachine = service.updateMachines(id, machineDTO);
        return ResponseEntity.ok(updatedMachine);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable UUID id) {
        service.deleteMachine(id);
        return ResponseEntity.noContent().build();
    }
}
