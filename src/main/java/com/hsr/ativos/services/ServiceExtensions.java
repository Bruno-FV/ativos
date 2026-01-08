package com.hsr.ativos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hsr.ativos.dtos.ExtensionsDTO;
import com.hsr.ativos.models.Extensions;
import com.hsr.ativos.repositorys.ExtensionsRepo;

@Service
public class ServiceExtensions {
    private final ExtensionsRepo extensionsRepo;

    public ServiceExtensions(ExtensionsRepo extensionsRepo) {
        this.extensionsRepo = extensionsRepo;
    }

    public List<ExtensionsDTO> getAllMachines() {
        return extensionsRepo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    private ExtensionsDTO toDTO(Extensions e) {
        return new ExtensionsDTO(
                e.getId(),
                e.getSetor(),
                e.getRamal()
        );
    }
}
