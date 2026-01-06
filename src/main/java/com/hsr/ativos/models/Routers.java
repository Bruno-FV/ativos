package com.hsr.ativos.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Routers {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private String modelo;
    private String ip;
    private String setor;
    private String redeWifi;
    private String senhaRedeWifi;
    private String loginConfiguracao;
    private String senhaConfiguracao;

    // Constructors
    public Routers(UUID id, String modelo, String ip, String setor, String redeWifi,
                      String senhaRedeWifi, String loginConfiguracao, String senhaConfiguracao) {
        this.id = id;
        this.modelo = modelo;
        this.ip = ip;
        this.setor = setor;
        this.redeWifi = redeWifi;
        this.senhaRedeWifi = senhaRedeWifi;
        this.loginConfiguracao = loginConfiguracao;
        this.senhaConfiguracao = senhaConfiguracao;
    }
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getSetor() {
        return setor;
    }
    public void setSetor(String setor) {
        this.setor = setor;
    }
    public String getRedeWifi() {
        return redeWifi;
    }
    public void setRedeWifi(String redeWifi) {
        this.redeWifi = redeWifi;
    }
    public String getSenhaRedeWifi() {
        return senhaRedeWifi;
    }
    public void setSenhaRedeWifi(String senhaRedeWifi) {
        this.senhaRedeWifi = senhaRedeWifi;
    }
    public String getLoginConfiguracao() {
        return loginConfiguracao;
    }
    public void setLoginConfiguracao(String loginConfiguracao) {
        this.loginConfiguracao = loginConfiguracao;
    }
    public String getSenhaConfiguracao() {
        return senhaConfiguracao;
    }
    public void setSenhaConfiguracao(String senhaConfiguracao) {
        this.senhaConfiguracao = senhaConfiguracao;
    }

}
