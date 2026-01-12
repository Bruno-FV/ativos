package com.hsr.ativos.repositorys;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsr.ativos.models.Routers;

@Repository
public interface RoutersRepo extends JpaRepository<Routers, UUID> {
    
}
