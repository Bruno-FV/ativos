package com.hsr.ativos.repositorys;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsr.ativos.models.Extensions;

public interface ExtensionsRepo extends JpaRepository<Extensions, UUID> {

    
}
