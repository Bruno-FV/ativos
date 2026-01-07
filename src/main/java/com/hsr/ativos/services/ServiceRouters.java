package com.hsr.ativos.services;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hsr.ativos.dtos.RoutersDTO;
import com.hsr.ativos.repositorys.RoutersRepo;
import com.hsr.ativos.models.Routers;
import com.hsr.ativos.enums.RoutersStatus;

import jakarta.transaction.Transactional;

@Service
public class ServiceRouters {
    private final RoutersRepo routersRepo;
    public ServiceRouters(RoutersRepo routersRepo) {
        this.routersRepo = routersRepo;
    }
    public List<RoutersDTO> getAllRouters() {
        return routersRepo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    @Transactional
    @Scheduled(fixedDelay = 30000) // a cada 30s
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
                r.getSetor(),
                r.getSSID(),
                r.getSenhaRedeWifi(),
                r.getLoginConfiguracao(),
                r.getSenhaConfiguracao(),
                r.getStatus()
        );
    }
}
