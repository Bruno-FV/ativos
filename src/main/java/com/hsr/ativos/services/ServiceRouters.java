package com.hsr.ativos.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsr.ativos.dtos.RoutersDTO;
import com.hsr.ativos.enums.RoutersStatus;
import com.hsr.ativos.models.Routers;
import com.hsr.ativos.repositorys.RoutersRepo;

import jakarta.transaction.Transactional;

@Service
public class ServiceRouters {
    private final RoutersRepo routersRepo;

    public ServiceRouters(RoutersRepo routersRepo) {
        this.routersRepo = routersRepo;
    }

    // listar todos os roteadores
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
                    online ? RoutersStatus.online : RoutersStatus.offline);
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
                r.getStatus());
    }

    // salvar roteador no banco
    public ResponseEntity<?> saveRouters(@RequestBody RoutersDTO routersDTO) {
        try {
            var newRouter = new Routers();
            if (routersDTO != null) {
                BeanUtils.copyProperties(routersDTO, newRouter);
            }
            routersRepo.save(newRouter);
            return ResponseEntity.status(201).body(newRouter);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(400).body(Map.of("error", "IP já existente."));
        }
    }

    /*
     * var newRouter = new Routers();
     * if (routersDTO != null) {
     * BeanUtils.copyProperties(routersDTO, newRouter);
     * }
     * routersRepo.save(newRouter);
     * return newRouter;
     * }
     */
    // atualizar roteador
    public Routers updateRouters(UUID id, @RequestBody RoutersDTO routersDTO) {
        if (id == null) {
            return null;
        }
        var updateRouters = routersRepo.findById(id);
        if (updateRouters.isEmpty()) {
            return null;
        }
        var newUpdate = updateRouters.get();
        if (routersDTO != null && newUpdate != null) {
            BeanUtils.copyProperties(routersDTO, newUpdate);
        }
        if (newUpdate == null) {
            return null;
        }
        return routersRepo.save(newUpdate);
    }

    // deletar roteador
    public ResponseEntity<String> deleteRouters(UUID id) {
        if (id == null) {
            return ResponseEntity.status(404).body("id não encontrado");
        }
        var deleteRouter = routersRepo.findById(id);
        if (deleteRouter.isPresent()) {
            routersRepo.deleteById(id);
            return ResponseEntity.ok("Roteador deletado com sucesso");
        }
        return ResponseEntity.status(404).body("Roteador não encontrado");
    }
}
