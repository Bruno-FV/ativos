package com.hsr.ativos.repositorys;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsr.ativos.models.Extensions;

@Repository
public interface ExtensionsRepo extends JpaRepository<Extensions, UUID> {

}
