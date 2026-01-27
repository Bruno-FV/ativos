package com.hsr.ativos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.models.Extensions;
import com.hsr.ativos.repositorys.ExtensionsRepo;

@RestController
@RequestMapping("/popular-ramal")
public class PopularRamal {
    private final ExtensionsRepo extensionsRepo;

    public PopularRamal(ExtensionsRepo extensionsRepo) {
        this.extensionsRepo = extensionsRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<List<Extensions>> popularBanco(@RequestBody List<Extensions> extensionsList) {
        if (extensionsList == null || extensionsList.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        List<Extensions> savedExtensions = extensionsRepo.saveAll(extensionsList);
        return ResponseEntity.ok(savedExtensions);
    }
}
