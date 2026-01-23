
package com.hsr.ativos.dtos;

/**
 * DTO para requisição de login.
 * Esta classe representa os dados necessários para autenticar um usuário,
 * contendo email e senha fornecidos pelo cliente.
 */
public class LoginRequest {

    /**
     * Email do usuário para autenticação.
     * Este campo é obrigatório e deve ser um email válido.
     */
    private String email;

    /**
     * Senha do usuário para autenticação.
     * Este campo é obrigatório e deve corresponder à senha armazenada no banco de dados.
     */
    private String password;

    /**
     * Construtor padrão necessário para desserialização JSON.
     */
    public LoginRequest() {
    }

    /**
     * Construtor com parâmetros para facilitar a criação de instâncias.
     *
     * @param email Email do usuário
     * @param password Senha do usuário
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
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
     * Obtém a senha do usuário.
     *
     * @return Senha do usuário
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do usuário.
     *
     * @param password Senha do usuário
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Representação em string do objeto para depuração.
     *
     * @return String representando o objeto LoginRequest
     */
    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'" +  // Não exibir senha em logs
                '}';
    }
}
