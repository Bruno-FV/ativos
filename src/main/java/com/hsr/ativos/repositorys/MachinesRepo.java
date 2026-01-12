package com.hsr.ativos.repositorys;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsr.ativos.models.Machines;

@Repository
public interface MachinesRepo extends JpaRepository<Machines, UUID> {
}
