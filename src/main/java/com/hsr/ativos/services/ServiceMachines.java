package com.hsr.ativos.services;

import org.springframework.stereotype.Service;

import com.hsr.ativos.repositorys.MachinesRepo;


@Service
public class ServiceMachines {
    private final MachinesRepo machinesRepo;
    public ServiceMachines(MachinesRepo machinesRepo) {
        this.machinesRepo = machinesRepo;
    }
    
    public Iterable<?> getAllMachines() {
        return machinesRepo.findAll();
    }
}
