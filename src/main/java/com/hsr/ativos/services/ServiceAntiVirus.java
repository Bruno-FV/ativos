package com.hsr.ativos.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsr.ativos.dtos.AntiVirusDTO;
import com.hsr.ativos.models.AntVirus;
import com.hsr.ativos.repositorys.AntiVirusRepo;

@Service
public class ServiceAntiVirus {

    private final AntiVirusRepo antiVirusRepo;

    public ServiceAntiVirus(AntiVirusRepo antiVirusRepo) {
        this.antiVirusRepo = antiVirusRepo;
    }

    public List<AntVirus> getAllAntVirus() {
        return antiVirusRepo.findAll();
    }
    //Serviço para salvar
    public ResponseEntity<?> saveLicense(@RequestBody AntiVirusDTO antiVirusDTO) {
        var newLicense = new AntVirus();
        if (antiVirusDTO != null) {
            BeanUtils.copyProperties(antiVirusDTO, newLicense);
        }
        try {
            antiVirusRepo.save(newLicense);
            return ResponseEntity.status(201).body(newLicense);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(400).body(Map.of("error", "Licença já cadastrada"));
        }
    }
}
