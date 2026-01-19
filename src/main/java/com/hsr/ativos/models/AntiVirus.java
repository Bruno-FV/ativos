package com.hsr.ativos.models;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hsr.ativos.enums.AntiVirusStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AntiVirus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String keyLisence;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateStartLisence;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateEndLisence;

    @OneToMany(mappedBy="antVirusLicense")
    @JsonIgnore
    private List <Machines> machine;
    
    private String registrationDate;
    @Enumerated(EnumType.STRING)
    private AntiVirusStatus status;
    private String versionAntiVirus;


}
