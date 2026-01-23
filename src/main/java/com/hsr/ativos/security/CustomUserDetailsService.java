package com.hsr.ativos.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hsr.ativos.models.Users;
import com.hsr.ativos.repositorys.UserRepo;

/**
 * Serviço personalizado de detalhes do usuário.
 * Implementa UserDetailsService para integração com Spring Security.
 * Responsável por carregar informações do usuário durante autenticação.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Repositório para operações com usuários.
     */
    private final UserRepo userRepo;

    /**
     * Construtor do serviço.
     *
     * @param userRepo Repositório de usuários
     */
    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Carrega os detalhes do usuário pelo nome de usuário (email).
     * Método obrigatório da interface UserDetailsService.
     *
     * @param email Email do usuário
     * @return UserDetails com informações do usuário
     * @throws UsernameNotFoundException Se o usuário não for encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // Busca o usuário no banco de dados pelo email
        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Cria e retorna UserDetails com as informações do usuário
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name().replace("ROLE_", ""))  // Remove prefixo ROLE_
                .build();
    }
}
