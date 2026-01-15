package com.hsr.ativos.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Extensions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String setor;
    private String ramal;

    public Extensions() {
    }

    public Extensions(UUID id, String setor, String ramal) {
        this.id = id;
        this.setor = setor;
        this.ramal = ramal;
    }

}