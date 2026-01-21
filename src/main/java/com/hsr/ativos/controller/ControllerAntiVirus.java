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

import com.hsr.ativos.dtos.AntiVirusDTO;
import com.hsr.ativos.models.AntiVirus;
import com.hsr.ativos.services.ServiceAntiVirus;

@RestController
@RequestMapping("/licenses")
public class ControllerAntiVirus {

    private final ServiceAntiVirus service;

    public ControllerAntiVirus(ServiceAntiVirus service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AntiVirus>> getAllLicenses() {
        List<AntiVirus> licenses = service.getAllAntVirus();
        return ResponseEntity.ok(licenses);
    }

    @GetMapping("/with-machines")
    public ResponseEntity<List<AntiVirusDTO>> getLicensesWithMachines() {
        List<AntiVirusDTO> licenses = service.getLicensesWithMachines();
        return ResponseEntity.ok(licenses);
    }

    @GetMapping("/with-machines/{id}")
    public ResponseEntity<AntiVirusDTO> getLicensesWithMachinesId(@PathVariable UUID id) {
        AntiVirusDTO dto = service.getLicensesWithMachinesId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveLicense(@RequestBody AntiVirusDTO antiVirusDTO) {
        return service.saveLicense(antiVirusDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLicense(@PathVariable UUID id, @RequestBody AntiVirusDTO antiVirusDTO) {
        return service.updateLicense(id, antiVirusDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLicense(@PathVariable UUID id) {
        return service.deleteLicense(id);
    }
}
