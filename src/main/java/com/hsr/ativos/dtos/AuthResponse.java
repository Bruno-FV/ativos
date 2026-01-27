package com.hsr.ativos.dtos;

/**
 * DTO para resposta de autenticação.
 * Esta classe representa a resposta retornada após uma autenticação bem-sucedida,
 * contendo o token JWT e as informações básicas do usuário autenticado.
 */
public class AuthResponse {

    /**
     * Token JWT gerado para o usuário autenticado.
     * Este token deve ser incluído no header Authorization das requisições subsequentes.
     */
    private String token;

    /**
     * Email do usuário autenticado.
     * Usado para identificação do usuário na aplicação.
     */
    private String email;

    /**
     * Role do usuário autenticado.
     * Define as permissões de acesso do usuário (ex: ROLE_ADMIN, ROLE_USER).
     */
    private String role;

    private String name;

    /**
     * Construtor padrão necessário para desserialização JSON.
     */
    public AuthResponse() {
    }

    /**
     * Construtor com parâmetros para facilitar a criação de instâncias.
     *
     * @param token Token JWT do usuário
     * @param email Email do usuário
     * @param role Role do usuário
     */
    public AuthResponse(String token, String email, String role, String name) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.name = name;
    }

    /**
     * Obtém o token JWT.
     *
     * @return Token JWT
     */
    public String getToken() {
        return token;
    }

    /**
     * Define o token JWT.
     *
     * @param token Token JWT
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Obtém o email do usuário.
     *
     * @return Email do usuário
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do usuário.
     *
     * @param email Email do usuário
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o role do usuário.
     *
     * @return Role do usuário
     */
    public String getRole() {
        return role;
    }

    /**
     * Define o role do usuário.
     *
     * @param role Role do usuário
     */
    public void setRole(String role) {
        this.role = role;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    /**
     * Representação em string do objeto para depuração.
     *
     * @return String representando o objeto AuthResponse
     */
    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='[PROTECTED]'" +  // Não exibir token completo em logs por segurança
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
