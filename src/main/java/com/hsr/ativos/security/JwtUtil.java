package com.hsr.ativos.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * Classe utilitária para manipulação de tokens JWT.
 * Esta classe é responsável por gerar, validar e extrair informações dos tokens JWT
 * utilizados na autenticação da aplicação.
 */
@Component
public class JwtUtil {

    /**
     * Chave secreta utilizada para assinar os tokens JWT.
     * Em produção, esta chave deve ser armazenada de forma segura (ex: variáveis de ambiente).
     */
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Tempo de expiração do token em milissegundos (24 horas).
     */
    private final long jwtExpiration = 86400000; // 24 horas

    /**
     * Gera um token JWT para o usuário especificado.
     *
     * @param email Email do usuário autenticado
     * @param role Role do usuário (ex: ROLE_ADMIN, ROLE_USER)
     * @return Token JWT gerado
     */
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)  // Define o email como subject do token
                .claim("role", role)  // Adiciona o role como claim personalizado
                .setIssuedAt(new Date())  // Data de emissão
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))  // Data de expiração
                .signWith(secretKey)  // Assina com a chave secreta
                .compact();  // Compacta o token
    }

    /**
     * Extrai o email (subject) do token JWT.
     *
     * @param token Token JWT
     * @return Email do usuário
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrai o role do token JWT.
     *
     * @param token Token JWT
     * @return Role do usuário
     */
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    /**
     * Verifica se o token JWT está expirado.
     *
     * @param token Token JWT
     * @return true se expirado, false caso contrário
     */
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrai a data de expiração do token JWT.
     *
     * @param token Token JWT
     * @return Data de expiração
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Valida se o token JWT é válido para o usuário especificado.
     *
     * @param token Token JWT
     * @param email Email do usuário
     * @return true se válido, false caso contrário
     */
    public boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (email.equals(extractedEmail) && !isTokenExpired(token));
    }

    /**
     * Método genérico para extrair claims do token JWT.
     *
     * @param token Token JWT
     * @param claimsResolver Função para resolver o claim desejado
     * @param <T> Tipo do claim
     * @return Valor do claim extraído
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrai todos os claims do token JWT.
     *
     * @param token Token JWT
     * @return Claims do token
     */
    @SuppressWarnings("deprecation")
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)  // Define a chave para verificar a assinatura
                .build()
                .parseClaimsJws(token)  // Faz o parse do token
                .getBody();  // Retorna o corpo com os claims
    }
}
