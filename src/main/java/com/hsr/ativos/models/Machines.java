package com.hsr.ativos.models;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Machines {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String hostName;
    private String ip;
    private String sistemaOperacional;
    private String setor;
    private String processador;
    private String memoria;
    private String armazenamento;
    private String tipoArmazenamento;
    private boolean antVirus;
    private boolean licensaOffice;

   //constructors
    public Machines(UUID id, String hostName, String ip, String sistemaOperacional, String setor,
                     String processador, String memoria, String armazenamento, String tipoArmazenamento, boolean antVirus, boolean licensaOffice) {
         this.id = id;
         this.hostName = hostName;
         this.ip = ip;
         this.sistemaOperacional = sistemaOperacional;
         this.setor = setor;
         this.processador = processador;
         this.memoria = memoria;
         this.armazenamento = armazenamento;
         this.tipoArmazenamento = tipoArmazenamento;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getHostName() {
        return hostName;
    }
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getSistemaOperacional() {
        return sistemaOperacional;
    }
    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }
    public String getSetor() {
        return setor;
    }
    public void setSetor(String setor) {
        this.setor = setor;
    }
    public String getProcessador() {
        return processador;
    }
    public void setProcessador(String processador) {
        this.processador = processador;
    }
    public String getMemoria() {
        return memoria;
    }
    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }
    public String getArmazenamento() {
        return armazenamento;
    }
    public void setArmazenamento(String armazenamento) {
        this.armazenamento = armazenamento;
    }
    public String getTipoArmazenamento() {
        return tipoArmazenamento;
    }
    public void setTipoArmazenamento(String tipoArmazenamento) {
        this.tipoArmazenamento = tipoArmazenamento;
    }
    public boolean isAntVirus() {
        return antVirus;
    }
    public void setAntVirus(boolean antVirus) {
        this.antVirus = antVirus;
    }
    public boolean isLicensaOffice() {
        return licensaOffice;
    }
    public void setLicensaOffice(boolean licensaOffice) {
        this.licensaOffice = licensaOffice;
    }
}
