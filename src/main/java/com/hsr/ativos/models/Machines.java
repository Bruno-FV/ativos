package com.hsr.ativos.models;


import java.util.UUID;

import com.hsr.ativos.enums.MachineStatus;

import jakarta.persistence.Column;
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
    private String antVirus;
    private String licensaOffice;
    @Enumerated(EnumType.STRING)
    private MachineStatus status;

   //constructors
   public Machines() {
    }
    public Machines(UUID id, String hostName, String ip, String sistemaOperacional, String setor,
                     String processador, String memoria, String armazenamento, String tipoArmazenamento, String antVirus, String licensaOffice, MachineStatus status) {
         this.id = id;
         this.hostName = hostName;
         this.ip = ip;
         this.sistemaOperacional = sistemaOperacional;
         this.setor = setor;
         this.processador = processador;
         this.memoria = memoria;
         this.armazenamento = armazenamento;
         this.tipoArmazenamento = tipoArmazenamento;
        this.antVirus = antVirus;
        this.licensaOffice = licensaOffice;
        this.status = status;
       
    }
}
