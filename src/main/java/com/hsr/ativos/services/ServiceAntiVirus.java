package com.hsr.ativos.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsr.ativos.dtos.AntiVirusDTO;
import com.hsr.ativos.enums.AntiVirusStatus;
import com.hsr.ativos.models.AntiVirus;
import com.hsr.ativos.repositorys.AntiVirusRepo;

@Service
public class ServiceAntiVirus {

    private final AntiVirusRepo antiVirusRepo;

    public ServiceAntiVirus(AntiVirusRepo antiVirusRepo) {
        this.antiVirusRepo = antiVirusRepo;
    }

    public List<AntiVirus> getAllAntVirus() {
        return antiVirusRepo.findAll();
    }

    public List<AntiVirusDTO> getLicensesWithMachines() {
        List<AntiVirus> licenses = antiVirusRepo.findAll();
        return licenses.stream()
                .map(license -> new AntiVirusDTO(
                        license.getId(),
                        license.getKeyLisence(),
                        license.getDateStartLisence(),
                        license.getDateEndLisence(),
                        license.getMachine(),
                        license.getRegistrationDate(),
                        license.getStatus(),
                        license.getVersionAntiVirus()))
                .collect(Collectors.toList());
    }

    //Serviço para salvar
    public ResponseEntity<?> saveLicense(@RequestBody AntiVirusDTO antiVirusDTO) {
        var newLicense = new AntiVirus();
        if (antiVirusDTO != null) {
            BeanUtils.copyProperties(antiVirusDTO, newLicense);
            newLicense.setStatus(AntiVirusStatus.active); // Define status inicial como ativo
        }
        try {
            antiVirusRepo.save(newLicense);
            return ResponseEntity.status(201).body(newLicense);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(400).body(Map.of("error", "Licença já cadastrada"));
        }
    }

    public ResponseEntity<?> updateLicense(UUID id, AntiVirusDTO antiVirusDTO) {
        Optional<AntiVirus> optionalLicense = antiVirusRepo.findById(id);
        if (optionalLicense.isPresent()) {
            AntiVirus license = optionalLicense.get();
            BeanUtils.copyProperties(antiVirusDTO, license, "id");
            antiVirusRepo.save(license);
            return ResponseEntity.ok(license);
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "Licença não encontrada"));
        }
    }

    public ResponseEntity<?> deleteLicense(UUID id) {
        Optional<AntiVirus> optionalLicense = antiVirusRepo.findById(id);
        if (optionalLicense.isPresent()) {
            antiVirusRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "Licença não encontrada"));
        }
    }

    public void checkAndUpdateExpiredLicenses() {
        List<AntiVirus> licenses = antiVirusRepo.findAll();
        LocalDate today = LocalDate.now();
        for (AntiVirus license : licenses) {
            if (license.getDateEndLisence() != null && license.getDateEndLisence().isBefore(today) && license.getStatus() == AntiVirusStatus.active) {
                license.setStatus(AntiVirusStatus.inactive);
                antiVirusRepo.save(license);
            }
        }
    }
}
