package com.hsr.ativos.repositorys;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsr.ativos.models.AntiVirus;

import jakarta.transaction.Status;

@Repository
public interface AntiVirusRepo extends JpaRepository<AntiVirus, UUID>{
    List<AntiVirus> findByStatus(Status status);
}
