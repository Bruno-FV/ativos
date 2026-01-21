package com.hsr.ativos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.dtos.MachineDTO;
import com.hsr.ativos.models.Machines;
import com.hsr.ativos.services.ServiceMachines;

@RestController
@RequestMapping("/popular-banco")
public class PopularBanco {

    private final ServiceMachines serviceMachines;

    public PopularBanco(ServiceMachines serviceMachines) {
        this.serviceMachines = serviceMachines;
    }

    @PostMapping("/add")
    public ResponseEntity<List<Machines>> popularBanco(@RequestBody List<MachineDTO> machinesList) {
        if (machinesList == null || machinesList.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        // Salvar cada máquina usando o serviço, filtrando apenas os salvamentos bem-sucedidos
        List<Machines> savedMachines = machinesList.stream()
                .map(dto -> serviceMachines.saveMachines(dto))
                .filter(response -> response.getStatusCode().is2xxSuccessful())
                .map(response -> (Machines) response.getBody())
                .toList();
        return ResponseEntity.ok(savedMachines);
    }
}
