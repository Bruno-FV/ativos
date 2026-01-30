package com.hsr.ativos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.dtos.MachineDTO;
import com.hsr.ativos.models.Extensions;
import com.hsr.ativos.models.Machines;
import com.hsr.ativos.models.Printers;
import com.hsr.ativos.models.Routers;
import com.hsr.ativos.repositorys.ExtensionsRepo;
import com.hsr.ativos.repositorys.PrintersRepo;
import com.hsr.ativos.repositorys.RoutersRepo;
import com.hsr.ativos.services.ServiceMachines;

@RestController
@RequestMapping("/auth")
public class PopularBanco {

    private final ServiceMachines serviceMachines;
    private final ExtensionsRepo extensionsRepo;
    private final RoutersRepo routersRepo;
    private final PrintersRepo printersRepo;

    public PopularBanco(ServiceMachines serviceMachines, ExtensionsRepo extensionsRepo, RoutersRepo routersRepo, PrintersRepo printersRepo) {
        this.serviceMachines = serviceMachines;
        this.extensionsRepo = extensionsRepo;
        this.routersRepo = routersRepo;
        this.printersRepo = printersRepo;
    }

    @PostMapping("/addMachines")
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

    @PostMapping("/addramal")
    public ResponseEntity<List<Extensions>> popularBancoRamal(@RequestBody List<Extensions> extensionsList) {
        if (extensionsList == null || extensionsList.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        List<Extensions> savedExtensions = extensionsRepo.saveAll(extensionsList);
        return ResponseEntity.ok(savedExtensions);
    }

    @PostMapping("/addrot")
    public ResponseEntity<List<Routers>> popularRouters(@RequestBody List<Routers> routersList) {
        if (routersList == null || routersList.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        List<Routers> savedRouters = routersRepo.saveAll(routersList);
        return ResponseEntity.ok(savedRouters);
    }

    @PostMapping("/allprinters")
    public List<Printers> polulate(@RequestBody List<Printers> printersList) {
        @SuppressWarnings("null")
        List<Printers> save = printersRepo.saveAll(printersList);
        return save;
    }
}
