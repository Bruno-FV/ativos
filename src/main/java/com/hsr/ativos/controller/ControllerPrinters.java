package com.hsr.ativos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.dtos.PrintersDTO;
import com.hsr.ativos.models.Printers;
import com.hsr.ativos.services.ServicePrinters;

@RestController
@RequestMapping("/printers")
public class ControllerPrinters {

    private final ServicePrinters servicePrinters;


    public ControllerPrinters(ServicePrinters servicePrinters) {
        this.servicePrinters = servicePrinters;
    }
    @PostMapping("/add")
    public Printers savePrinters(@RequestBody PrintersDTO printersList) {
        return servicePrinters.populateDataBase(printersList);
    }
    @GetMapping("/all")
    public List<Printers> getAllPrinters() {
        return servicePrinters.getAll();
    }
    @PutMapping("/update/{id}")
    public void updatePrinters(@RequestBody PrintersDTO printersDTO){
        servicePrinters.updatePrinters(printersDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void deletePrinters(@RequestBody PrintersDTO printersDTO){
        servicePrinters.deletePrinters(printersDTO);
    }

}
