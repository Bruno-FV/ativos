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

import com.hsr.ativos.dtos.ExtensionsDTO;
import com.hsr.ativos.services.ServiceExtensions;

@RestController
@RequestMapping("/extensions")
public class ControllerExtensions {
    private final ServiceExtensions serviceExtensions;

    public ControllerExtensions(ServiceExtensions serviceExtensions) {
        this.serviceExtensions = serviceExtensions;
    }

    @GetMapping("/all")
    public List<ExtensionsDTO> getAllExtensions() {
        return serviceExtensions.getAllExtensions();
    }

    @PostMapping("/save")
    public void saveExtensions(@RequestBody ExtensionsDTO extensionsDTO) {
        serviceExtensions.saveExtensions(extensionsDTO);
    }

    @PutMapping("/update/{id}")
    public void updateExtensions(UUID id, @RequestBody ExtensionsDTO extensionsDTO) {
        serviceExtensions.updateExtensions(id, extensionsDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExtensions(@PathVariable UUID id) {
        serviceExtensions.deleteExtensions(id);
    }
}
