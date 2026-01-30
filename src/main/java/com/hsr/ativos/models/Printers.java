package com.hsr.ativos.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Printers {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private String connectionType;
    private String sector;
    private String model;
    private int initialPrintQuantity;
    private int endPrintQuantity;
    private int totalPrint;
    private LocalDate printCycloInitial;
    private LocalDate printCycloEnd;

}
