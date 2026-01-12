package com.hsr.ativos.services;

import java.net.InetAddress;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
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
            boolean online = ping(machine.getIp());
            machine.setStatus(
                    online ? MachineStatus.online : MachineStatus.offline);
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
                m.getStatus());
    }

    // serviço para salvar máquinas no banco
    public Machines saveMachines(MachineDTO machineDTO) {
        var newMachine = new Machines();
        if (machineDTO != null) {
            BeanUtils.copyProperties(machineDTO, newMachine);
        }
        machinesRepo.save(newMachine);
        return newMachine;
    }

    // serviço para atualizar máquinas no banco
    public Machines updateMachines(UUID id, MachineDTO machineDTO) {
        var updateMachines = machinesRepo.findById(id);
        if (updateMachines.isEmpty()) {
            return null;
        }
        var newUpdate = updateMachines.get();
        if (machineDTO != null && newUpdate != null) {
            BeanUtils.copyProperties(machineDTO, newUpdate);
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
