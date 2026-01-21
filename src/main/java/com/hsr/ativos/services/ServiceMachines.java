package com.hsr.ativos.services;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsr.ativos.dtos.MachineDTO;
import com.hsr.ativos.enums.MachineStatus;
import com.hsr.ativos.models.Machines;
import com.hsr.ativos.repositorys.AntiVirusRepo;
import com.hsr.ativos.repositorys.MachinesRepo;

import jakarta.transaction.Transactional;

@Service
public class ServiceMachines {

    private final MachinesRepo machinesRepo;
    private final AntiVirusRepo antiVirusRepo;

    public ServiceMachines(MachinesRepo machinesRepo, AntiVirusRepo antiVirusRepo) {
        this.machinesRepo = machinesRepo;
        this.antiVirusRepo = antiVirusRepo;
    }

    // busca todas as máquinas no banco
    public List<MachineDTO> getAllMachines() {
        return machinesRepo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // 🔹 Atualização de status em background
    @Transactional
    @Scheduled(fixedDelay = 30000) // a cada 30s
    // 🔹 Apenas atualiza o status no banco
    public void updateStatus() {
        List<Machines> listMachines = machinesRepo.findAll();
        listMachines.forEach(machine -> {
            if (machine.getStatus() == MachineStatus.maintenance) {
                return; // pula máquinas em manutenção
            }
            boolean online = ping(machine.getIp());
            machine.setStatus(
                    online ? MachineStatus.online : MachineStatus.offline);
        });
    }

    // 🔹 Ping APENAS aqui
    @SuppressWarnings("null")
    private boolean ping(String ip) {
        try {
            return InetAddress.getByName(ip).isReachable(1500);
        } catch (Exception e) {
            return false;
        }
    }

    // dto converter
    private MachineDTO toDTO(Machines m) {
        return new MachineDTO(
                m.getId(),
                m.getHostName(),
                m.getIp(),
                m.getSistemaOperacional(),
                m.getSetor(),
                m.getProcessador(),
                m.getMemoria(),
                m.getArmazenamento(),
                m.getTipoArmazenamento(),
                m.getLicensaOffice(),
                m.getAntVirusLicense() != null ? m.getAntVirusLicense().getId().toString() : null, // Convertido para String para corresponder ao DTO
                m.getStatus());
    }

    // serviço para salvar máquinas no banco
    @SuppressWarnings("null")
    public ResponseEntity<?> saveMachines(@RequestBody MachineDTO machineDTO) {
        List<Machines> machinesWithSameIp = machinesRepo.findAll(machineDTO.ip());
        if (!machinesWithSameIp.isEmpty()) {
            return ResponseEntity.status(409).body(Map.of("error", "Já existe uma máquina com esse IP."));
        }

        var newMachine = new Machines();
        if (machineDTO != null) {
            BeanUtils.copyProperties(machineDTO, newMachine);
            // Vincular licença se fornecida
            if (machineDTO.antVirusLicense() != null && !machineDTO.antVirusLicense().isEmpty()) {
                try {
                    UUID licenseId = UUID.fromString(machineDTO.antVirusLicense());
                    var license = antiVirusRepo.findById(licenseId);
                    if (license.isPresent()) {
                        newMachine.setAntVirusLicense(license.get());
                    } else {
                        return ResponseEntity.status(400).body(Map.of("error", "Licença não encontrada"));
                    }
                } catch (IllegalArgumentException e) {
                    // Se não for um UUID válido, ignorar ou definir como null
                    newMachine.setAntVirusLicense(null);
                }
            }
        }
        try {
            machinesRepo.save(newMachine);
            return ResponseEntity.status(201).body(newMachine);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(400).body(Map.of("error", "HostName já existente."));
        }
    }

    // serviço para atualizar máquinas no banco
    @SuppressWarnings("null")
    public Machines updateMachines(UUID id, MachineDTO machineDTO) {
        if (id == null) {
            return null;
        }
        var updateMachines = machinesRepo.findById(id);
        if (updateMachines.isEmpty()) {
            return null;
        }
        var newUpdate = updateMachines.get();
        if (machineDTO != null && newUpdate != null) {
            BeanUtils.copyProperties(machineDTO, newUpdate);
            // Vincular licença se fornecida
            if (machineDTO.antVirusLicense() != null && !machineDTO.antVirusLicense().isEmpty()) {
                try {
                    UUID licenseId = UUID.fromString(machineDTO.antVirusLicense());
                    var license = antiVirusRepo.findById(licenseId);
                    if (license.isPresent()) {
                        newUpdate.setAntVirusLicense(license.get());
                    } else {
                        newUpdate.setAntVirusLicense(null); // Remove vínculo se ID não fornecido
                        // Não retorna erro aqui, apenas não vincula
                    }
                } catch (IllegalArgumentException e) {
                    // Se não for um UUID válido, definir como null
                    newUpdate.setAntVirusLicense(null);
                }
            }
        }
        if (newUpdate == null) {
            return null;
        }
        return machinesRepo.save(newUpdate);
    }

    // serviço para deletar máquinas no banco
    public boolean deleteMachine(UUID id) {
        if (id == null) {
            return false;
        }
        return machinesRepo.findById(id).map(machine -> {
            if (machine == null) {
                return false;
            }
            machinesRepo.delete(machine);
            return true;
        }).orElse(false);
    }
}
