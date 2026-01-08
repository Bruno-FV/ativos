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
    RoutersStatus status
) {}
