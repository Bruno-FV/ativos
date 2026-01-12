package com.hsr.ativos.dtos;

import java.util.UUID;

import com.hsr.ativos.enums.MachineStatus;

public record MachineDTO(
    UUID id,
    String hostName,
    String ip,
    String sistemaOperacional,
    String setor,
    String processador,
    String memoria,
    String armazenamento,
    String tipoArmazenamento,
    String antVirus,
    String licensaOffice,
    MachineStatus status
) {}