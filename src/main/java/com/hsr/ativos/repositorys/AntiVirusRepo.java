package com.hsr.ativos.repositorys;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsr.ativos.models.AntVirus;

import jakarta.transaction.Status;

@Repository
public interface AntiVirusRepo extends JpaRepository<AntVirus, UUID>{
    List<AntVirus> findByStatus(Status status);
}
