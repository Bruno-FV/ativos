package com.hsr.ativos.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.hsr.ativos.dtos.PrintersDTO;
import com.hsr.ativos.models.Printers;
import com.hsr.ativos.repositorys.PrintersRepo;


@Service
public class ServicePrinters {
    private PrintersRepo printersRepo;

    public ServicePrinters(PrintersRepo printersRepo){
        this.printersRepo = printersRepo;
    }
    //Service to polulate database
    public Printers populateDataBase(PrintersDTO printersDTO){
        var  newPrinters = new Printers();
        if(printersDTO != null)
        BeanUtils.copyProperties(printersDTO, newPrinters);
        return printersRepo.save(newPrinters);
    }
    //Service to getAll printers
    @SuppressWarnings("unused")
    public List<Printers> getAll(){
        var getList = printersRepo.findAll();
        return getList;
      
    }
    //Service to update printers
    public Printers updatePrinters(PrintersDTO printersDTO){
        var updatePrinters = printersRepo.findById(printersDTO.id());
        if(updatePrinters.isEmpty()){
            return null;
        }
        var newUpdate = updatePrinters.get();
        if(printersDTO != null && newUpdate != null){
            BeanUtils.copyProperties(printersDTO, newUpdate);
        }
        if(newUpdate == null){
            return null;
        }
        return printersRepo.save(newUpdate);
    }

    // service to delete printers
    public void deletePrinters(PrintersDTO printersDTO){
        var deletePrinters = printersRepo.findById(printersDTO.id());
        if(deletePrinters.isPresent()){
            printersRepo.deleteById(printersDTO.id());
        }
    }
}
