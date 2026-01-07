package com.hsr.ativos.services;

import java.net.InetAddress;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hsr.ativos.dtos.MachineDTO;
import com.hsr.ativos.enums.MachineStatus;
import com.hsr.ativos.models.Machines;
import com.hsr.ativos.repositorys.MachinesRepo;

import jakarta.transaction.Transactional;

@Service
public class ServiceMachines {

    private final MachinesRepo machinesRepo;

    public ServiceMachines(MachinesRepo machinesRepo) {
        this.machinesRepo = machinesRepo;
    }

    // 🔹 Controller chama SOMENTE isso
    public List<MachineDTO> getAllMachines() {
        return machinesRepo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // 🔹 Atualização de status em background
    @Transactional
    @Scheduled(fixedDelay = 30000) // a cada 30s
    public void updateStatus() {
        List<Machines> machines = machinesRepo.findAll();

        machines.forEach(machine -> {
            boolean online = ping(machine.getIp());
            machine.setStatus(
                online ? MachineStatus.online : MachineStatus.offline
            );
        });
    }

    // 🔹 Ping APENAS aqui
    private boolean ping(String ip) {
        try {
            return InetAddress.getByName(ip).isReachable(1500);
        } catch (Exception e) {
            return false;
        }
    }

    // 🔹 DTO apenas lê o status já salvo
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
                m.getAntVirus(),
                m.getLicensaOffice(),
                m.getStatus()
        );
    }
}
