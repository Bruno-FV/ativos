package com.hsr.ativos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.models.Machines;
import com.hsr.ativos.repositorys.MachinesRepo;


@RestController
@RequestMapping("/popular-banco")
public class PopularBanco {
    private final MachinesRepo machinesRepo;

    public PopularBanco(MachinesRepo machinesRepo) {
        this.machinesRepo = machinesRepo;
    }
    @PostMapping("/add")
    public ResponseEntity<List<Machines>> popularBanco(@RequestBody List<Machines> machinesList) {
        if (machinesList == null || machinesList.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        List<Machines> savedMachines = machinesRepo.saveAll(machinesList);
        return ResponseEntity.ok(savedMachines);
    }
}