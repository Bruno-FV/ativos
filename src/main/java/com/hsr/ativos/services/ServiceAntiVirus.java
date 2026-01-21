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

import jakarta.persistence.EntityNotFoundException;

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
            // Validação da validade da licença
            LocalDate today = LocalDate.now();
            if (antiVirusDTO.dateStartLisence() != null && antiVirusDTO.dateEndLisence() != null) {
                if (antiVirusDTO.dateStartLisence().isAfter(antiVirusDTO.dateEndLisence())) {
                    return ResponseEntity.status(400).body(Map.of("error", "Data de início da licença não pode ser posterior à data de fim"));
                }
                if (antiVirusDTO.dateEndLisence().isBefore(today)) {
                    return ResponseEntity.status(400).body(Map.of("error", "Licença expirada não pode ser salva"));
                }
            }
            newLicense.setStatus(AntiVirusStatus.active); // Define status inicial como ativo
        }
        try {
            antiVirusRepo.save(newLicense);
            return ResponseEntity.status(201).body(newLicense);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(400).body(Map.of("error", "Licença já cadastrada"));
        }
    }

    @SuppressWarnings("null")
    public ResponseEntity<?> updateLicense(UUID id, AntiVirusDTO antiVirusDTO) {
        Optional<AntiVirus> optionalLicense = antiVirusRepo.findById(id);
        if (optionalLicense.isPresent()) {
            AntiVirus license = optionalLicense.get();
            BeanUtils.copyProperties(antiVirusDTO, license, "id");

            // Validação da validade da licença ao atualizar
            LocalDate today = LocalDate.now();
            if (antiVirusDTO.dateStartLisence() != null && antiVirusDTO.dateEndLisence() != null) {
                if (antiVirusDTO.dateStartLisence().isAfter(antiVirusDTO.dateEndLisence())) {
                    return ResponseEntity.status(400).body(Map.of("error", "Data de início da licença não pode ser posterior à data de fim"));
                }
                if (antiVirusDTO.dateEndLisence().isBefore(today)) {
                    license.setStatus(AntiVirusStatus.inactive); // Define como inativa se expirada
                } else {
                    license.setStatus(AntiVirusStatus.active); // Define como ativa se válida
                }
            }

            antiVirusRepo.save(license);
            return ResponseEntity.ok(license);
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "Licença não encontrada"));
        }
    }

    @SuppressWarnings("null")
    public AntiVirusDTO getLicensesWithMachinesId(UUID id) {
        AntiVirus antiVirus = antiVirusRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Licença não encontrada"));

        return new AntiVirusDTO(
                antiVirus.getId(),
                antiVirus.getKeyLisence(),
                antiVirus.getDateStartLisence(),
                antiVirus.getDateEndLisence(),
                antiVirus.getMachine(),
                antiVirus.getRegistrationDate(),
                antiVirus.getStatus(),
                antiVirus.getVersionAntiVirus());
    }

    @SuppressWarnings("null")
    public ResponseEntity<?> deleteLicense(UUID id) {
        Optional<AntiVirus> optionalLicense = antiVirusRepo.findById(id);
        if (optionalLicense.isPresent()) {
            antiVirusRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "Licença não encontrada"));
        }
    }
}
