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
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Machines {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @ManyToOne
    private AntiVirus antVirusLicense;
    @Enumerated(EnumType.STRING)
    private MachineStatus status;
}
