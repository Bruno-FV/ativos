package com.hsr.ativos.dtos;

import java.util.UUID;

import com.hsr.ativos.enums.RoutersStatus;

public record RoutersDTO(
        UUID id,
        String ip,
        String porta,
        String setor,
        String ssid,
        String senhaRedeWifi,
        String loginConfiguracao,
        String senhaConfiguracao,
        RoutersStatus status) {
    public RoutersDTO {
        ip = normalizeTrim(ip);
        porta = normalizeUpper(porta);
        setor = normalizeUpper(setor);
        ssid = normalizeUpper(ssid);
        senhaRedeWifi = normalizeTrim(senhaConfiguracao);
        loginConfiguracao = normalizeTrim(loginConfiguracao);
        senhaConfiguracao = normalizeTrim(senhaConfiguracao);
    }

    private static String normalizeUpper(String value) {
        return value != null ? value.trim().toUpperCase() : null;
    }

    private static String normalizeTrim(String value) {
        return value != null ? value.trim() : null;
    }
}
