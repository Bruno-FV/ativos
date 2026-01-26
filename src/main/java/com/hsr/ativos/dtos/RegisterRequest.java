package com.hsr.ativos.dtos;

/**
 * DTO para requisição de registro de usuário.
 * Esta classe representa os dados necessários para cadastrar um novo usuário,
 * incluindo nome, setor, email e senha.
 */
public class RegisterRequest {

    /**
     * Nome do usuário.
     */
    private String name;

    /**
     * Setor do usuário.
     */
    private String sector;

    /**
     * Email do usuário.
     */
    private String email;

    /**
     * Senha do usuário.
     */
    private String password;

    /**
     * Construtor padrão necessário para desserialização JSON.
     */
    public RegisterRequest() {
    }

    /**
     * Construtor com parâmetros para facilitar a criação de instâncias.
     *
     * @param name Nome do usuário
     * @param sector Setor do usuário
     * @param email Email do usuário
     * @param password Senha do usuário
     */
    public RegisterRequest(String name, String sector, String email, String password) {
        this.name = name;
        this.sector = sector;
        this.email = email;
        this.password = password;
    }

    // Getters e Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Representação em string do objeto para depuração.
     *
     * @return String representando o objeto RegisterRequest
     */
    @Override
    public String toString() {
        return "RegisterRequest{" +
                "name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}
