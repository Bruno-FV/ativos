package com.hsr.ativos.dtos;

import java.util.UUID;

import com.hsr.ativos.enums.MachineStatus;

import lombok.NonNull;

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
        @NonNull UUID antVirusLicense,
        MachineStatus status) {
    // Construtor customizado que normaliza os campos de texto
    public MachineDTO {
        // this = canonical constructor (não precisa chamar super)
        hostName = normalizeUpper(hostName);
        ip = normalizeTrim(ip);
        sistemaOperacional = normalizeUpper(sistemaOperacional);
        setor = normalizeUpper(setor);
        processador = normalizeUpper(processador);
        memoria = normalizeUpper(memoria);
        armazenamento = normalizeUpper(armazenamento);
        tipoArmazenamento = normalizeUpper(tipoArmazenamento);
        antVirus = normalizeUpper(antVirus);
        licensaOffice = normalizeUpper(licensaOffice);
    }

    // Método auxiliar (pode ser static ou private)
    private static String normalizeUpper(String value) {
        return value != null ? value.trim().toUpperCase() : null;
    }

    private static String normalizeTrim(String value) {
        return value != null ? value.trim() : null;
    }
}