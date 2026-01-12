package com.hsr.ativos.models;

import java.util.UUID;

import com.hsr.ativos.enums.RoutersStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Routers {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private String ip;
    private String porta;
    private String setor;
    private String ssid;
    private String senhaRedeWifi;
    private String loginConfiguracao;
    private String senhaConfiguracao;
    @Enumerated(EnumType.STRING)
    private RoutersStatus status;

    // Constructors
    public Routers() {}
    public Routers(UUID id, String ip, String porta, String setor, String ssid,
                      String senhaRedeWifi, String loginConfiguracao, String senhaConfiguracao, RoutersStatus status) {
        this.id = id;
        this.ip = ip;
        this.porta = porta;
        this.setor = setor;
        this.ssid = ssid;
        this.senhaRedeWifi = senhaRedeWifi;
        this.loginConfiguracao = loginConfiguracao;
        this.senhaConfiguracao = senhaConfiguracao;
    }
}
