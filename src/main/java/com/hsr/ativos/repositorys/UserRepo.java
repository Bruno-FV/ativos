package com.hsr.ativos.repositorys;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsr.ativos.models.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail(String email);
}
