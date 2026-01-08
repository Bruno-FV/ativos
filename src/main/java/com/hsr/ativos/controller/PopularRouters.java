package com.hsr.ativos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.models.Routers;
import com.hsr.ativos.repositorys.RoutersRepo;

@RestController
@RequestMapping("/popular-routers")
public class PopularRouters {
    private final RoutersRepo routersRepo;
    public PopularRouters(RoutersRepo routersRepo) {
        this.routersRepo = routersRepo;
    }
      @PostMapping("/add")
    public ResponseEntity<List<Routers>> popularRouters(@RequestBody List<Routers> routersList) {
        if (routersList == null || routersList.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        List<Routers> savedRouters = routersRepo.saveAll(routersList);
        return ResponseEntity.ok(savedRouters);
    }
}
