package com.hsr.ativos.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hsr.ativos.dtos.ExtensionsDTO;
import com.hsr.ativos.models.Extensions;
import com.hsr.ativos.repositorys.ExtensionsRepo;

@Service
public class ServiceExtensions {
    private final ExtensionsRepo extensionsRepo;

    public ServiceExtensions(ExtensionsRepo extensionsRepo) {
        this.extensionsRepo = extensionsRepo;
    }
    //listar todos os ramais
    public List<ExtensionsDTO> getAllMachines() {
        return extensionsRepo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    private ExtensionsDTO toDTO(Extensions e) {
        return new ExtensionsDTO(
                e.getId(),
                e.getSetor(),
                e.getRamal()
        );
    }
    //salvar ramal no banco
    public ResponseEntity<String> saveExtensions(ExtensionsDTO extensionsDTO){
        var newExtensions = new Extensions();
        if(extensionsDTO == null){
            return ResponseEntity.status(404).body("Dados do ramal não podem ser nulos");
        }
        BeanUtils.copyProperties(extensionsDTO, newExtensions);
        extensionsRepo.save(newExtensions);
        return ResponseEntity.ok("Ramal salvo com sucesso");
    }
    //atualizar ramal
    public ResponseEntity<String> updateExtensions(UUID id,ExtensionsDTO extensionsDTO){
        //validação que id recebido do front não é nulo
        if(id == null){
            return ResponseEntity.status(404).body("id não encontrado");
        }
        //validação que o dto recebido do front não é nulo
        if(extensionsDTO == null){
            return ResponseEntity.status(400).body("ExtensionsDTO não pode ser nulo");
        }
        var updateExtensions = extensionsRepo.findById(id);
        if(updateExtensions.isEmpty()){
            return ResponseEntity.status(404).body("Ramal com id " + id + " não encontrado");
        }
        var updatedExtensions = updateExtensions.get();
        if(updatedExtensions == null){
            return ResponseEntity.status(404).body("Ramal não encontrado");
        }
        BeanUtils.copyProperties(extensionsDTO, updatedExtensions);
        extensionsRepo.save(updatedExtensions);
        return ResponseEntity.ok("Ramal atualizado com sucesso");
    }
    //deletar ramal
    public ResponseEntity<String> deleteExtensions(UUID id){
        if(id == null){
            return ResponseEntity.status(404).body("id não encontrado");
        }
        var deleteExtensions = extensionsRepo.findById(id);
        if(deleteExtensions.isPresent()){
            extensionsRepo.deleteById(id);
            return ResponseEntity.ok("Ramal deletado com sucesso");
        }
        return ResponseEntity.status(404).body("Ramal não encontrado");
    }
}
