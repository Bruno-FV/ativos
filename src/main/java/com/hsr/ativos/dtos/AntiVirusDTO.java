package com.hsr.ativos.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.hsr.ativos.enums.AntiVirusStatus;
import com.hsr.ativos.models.Machines;

public record AntiVirusDTO(
        UUID id,
        String keyLisence,
        LocalDate dateStartLisence,
        LocalDate dateEndLisence,
        List<Machines> machine,
        LocalDate registrationDate,
        AntiVirusStatus status,
        String versionAntiVirus
        ) {

    public AntiVirusDTO        {
        keyLisence = normalizeTrim(keyLisence);
        versionAntiVirus = normalizeUpper(versionAntiVirus);
    }

    private static String normalizeUpper(String value) {
        return value != null ? value.toUpperCase() : null;
    }

    private static String normalizeTrim(String value) {
        return value != null ? value.trim() : null;
    }
}
