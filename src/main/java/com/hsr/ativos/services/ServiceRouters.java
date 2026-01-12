package com.hsr.ativos.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hsr.ativos.dtos.MachineDTO;
import com.hsr.ativos.dtos.RoutersDTO;
import com.hsr.ativos.repositorys.RoutersRepo;
import com.hsr.ativos.models.Machines;
import com.hsr.ativos.models.Routers;
import com.hsr.ativos.enums.RoutersStatus;

import jakarta.transaction.Transactional;

@Service
public class ServiceRouters {
    private final RoutersRepo routersRepo;
    public ServiceRouters(RoutersRepo routersRepo) {
        this.routersRepo = routersRepo;
    }
    //listar todos os roteadores
    public List<RoutersDTO> getAllRouters() {
        return routersRepo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    @Transactional
    @Scheduled(fixedDelay = 10000) // a cada 30s
    public void updateStatus() {
        List<Routers> routers = routersRepo.findAll();

        routers.forEach(router -> {
            boolean online = ping(router.getIp());
            router.setStatus(
                online ? RoutersStatus.online : RoutersStatus.offline
            );
        });
    }
    private boolean ping(String ip) {
        try {
            return java.net.InetAddress.getByName(ip).isReachable(1500);
        } catch (Exception e) {
            return false;
        }
    }
    private RoutersDTO toDTO(Routers r) {
        return new RoutersDTO(
                r.getId(),
                r.getIp(),
                r.getPorta(),
                r.getSetor(),
                r.getSsid(),
                r.getSenhaRedeWifi(),
                r.getLoginConfiguracao(),
                r.getSenhaConfiguracao(),
                r.getStatus()
        );
    }
    //salvar roteador no banco
      public Routers saveRouters(RoutersDTO routersDTO) {
        var newRouter = new Routers();
        if (routersDTO != null) {
            BeanUtils.copyProperties(routersDTO, newRouter);
        }
        routersRepo.save(newRouter);
        return newRouter;
    }
    //atualizar roteador
    public ResponseEntity<String> updateRouters(UUID id,RoutersDTO routersDTO){
        //validação que id recebido do front não é nulo
        if(id == null){
            return ResponseEntity.status(404).body("id não encontrado");
        }
        //validação que o dto recebido do front não é nulo
        if(routersDTO == null){
            return ResponseEntity.status(400).body("RoutersDTO não pode ser nulo");
        }
        var updateRouter = routersRepo.findById(id);
        if(updateRouter.isPresent()){
            Routers router = updateRouter.get();
            if(router != null){
                BeanUtils.copyProperties(routersDTO, router);
                routersRepo.save(router);
                return ResponseEntity.ok("Roteador atualizado com sucesso");
            }
        }
        return ResponseEntity.status(404).body("Roteador não encontrado");
    }
    //deletar roteador
    public ResponseEntity<String> deleteRouters(UUID id){
        if(id == null){
            return ResponseEntity.status(404).body("id não encontrado");
        }
        var deleteRouter = routersRepo.findById(id);
        if(deleteRouter.isPresent()){
            routersRepo.deleteById(id);
            return ResponseEntity.ok("Roteador deletado com sucesso");
        }
        return ResponseEntity.status(404).body("Roteador não encontrado");
    }
}
