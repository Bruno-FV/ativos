package com.hsr.ativos.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record PrintersDTO(
        UUID id,
        String connectionType,
        String sector,
        String model,
        int initialPrintQuantity,
        int endPrintQuantity,
        int totalPrint,
        LocalDate printCycloInitial,
        LocalDate printCycloEnd
        ) {

}
