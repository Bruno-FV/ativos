package com.hsr.ativos.repositorys;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hsr.ativos.models.Machines;

@Repository
public interface MachinesRepo extends JpaRepository<Machines, UUID> {
    @Query("SELECT m FROM Machines m WHERE m.ip = :ip")
    List<Machines> findAll(@Param ("ip") String ip);
}
