package com.hsr.ativos.dtos;

import java.util.UUID;

public record ExtensionsDTO(
        UUID id,
        String setor,
        String ramal) {
    public ExtensionsDTO {
        setor = normalizeUpper(setor);
        ramal = normalizeUpper(ramal);
    }

    private static String normalizeUpper(String value) {
        return value != null ? value.trim().toUpperCase() : null;
    }
}
