package com.hsr.ativos.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de autenticação JWT.
 * Este filtro intercepta todas as requisições HTTP e verifica se há um token JWT válido
 * no header Authorization. Se válido, autentica o usuário no contexto de segurança.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Utilitário para manipulação de tokens JWT.
     */
    private final JwtUtil jwtUtil;

    /**
     * Serviço para carregar detalhes do usuário.
     */
    private final UserDetailsService userDetailsService;

    /**
     * Construtor do filtro JWT.
     *
     * @param jwtUtil Utilitário JWT
     * @param userDetailsService Serviço de detalhes do usuário
     */
    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Método principal do filtro que processa cada requisição.
     * Verifica se há um token JWT válido e autentica o usuário se necessário.
     *
     * @param request Requisição HTTP
     * @param response Resposta HTTP
     * @param filterChain Cadeia de filtros
     * @throws ServletException Se ocorrer erro no servlet
     * @throws IOException Se ocorrer erro de I/O
     */
    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Obtém o header Authorization da requisição
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Verifica se o header Authorization existe e começa com "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Se não há token, continua com o próximo filtro
            filterChain.doFilter(request, response);
            return;
        }

        // Extrai o token JWT do header (remove "Bearer " do início)
        jwt = authHeader.substring(7);

        // Extrai o email do usuário do token
        userEmail = jwtUtil.extractEmail(jwt);

        // Verifica se o email foi extraído e se não há autenticação atual no contexto
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Carrega os detalhes do usuário do banco de dados
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Valida se o token é válido para este usuário
            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
                // Cria token de autenticação com os detalhes do usuário
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // Define detalhes da requisição no token de autenticação
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Define a autenticação no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continua com o próximo filtro na cadeia
        filterChain.doFilter(request, response);
    }
}
