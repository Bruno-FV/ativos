package com.hsr.ativos.repositorys;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsr.ativos.models.Printers;

@Repository
public interface PrintersRepo extends JpaRepository<Printers, UUID> {

}
