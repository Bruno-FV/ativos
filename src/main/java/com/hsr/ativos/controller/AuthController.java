package com.hsr.ativos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsr.ativos.dtos.AuthResponse;
import com.hsr.ativos.dtos.LoginRequest;
import com.hsr.ativos.dtos.RegisterRequest;
import com.hsr.ativos.enums.Role;
import com.hsr.ativos.models.Users;
import com.hsr.ativos.repositorys.UserRepo;
import com.hsr.ativos.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * Repositório para operações com usuários no banco de dados.
     */
    private final UserRepo repository;

    /**
     * Encoder para criptografar senhas dos usuários.
     */
    private final PasswordEncoder encoder;

    /**
     * Gerenciador de autenticação do Spring Security.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Utilitário para manipulação de tokens JWT.
     */
    private final JwtUtil jwtUtil;

    /**
     * Construtor da classe AuthController. Injeta as dependências necessárias
     * para autenticação e manipulação de usuários.
     *
     * @param repository Repositório de usuários
     * @param encoder Encoder de senhas
     * @param authenticationManager Gerenciador de autenticação
     * @param jwtUtil Utilitário JWT
     */
    public AuthController(UserRepo repository, PasswordEncoder encoder,
            AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.repository = repository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Endpoint para registro de novos usuários. Recebe os dados do usuário via
     * RegisterRequest, mapeia para Users, criptografa a senha e salva no banco
     * de dados. Se nenhum role for especificado, define como ROLE_USER por
     * padrão.
     *
     * @param request Dados do usuário a ser cadastrado
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        var checkEmail = repository.findByEmail(request.getEmail());
        if (checkEmail == null) {
            Users user = new Users();
            user.setName(request.getName());
            user.setSector(request.getSector());
            user.setEmail(request.getEmail());

            user.setPassword(encoder.encode(request.getPassword()));

            // Define role padrão se não especificado
            user.setRole(Role.ROLE_USER);

            // Salva o usuário no banco de dados
            repository.save(user);

            return ResponseEntity.ok("Usuário cadastrado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
        }
        // Cria objeto Users a partir do RegisterRequest

    }

    /**
     * Endpoint para login de usuários. Autentica as credenciais fornecidas e
     * retorna um token JWT se válido. O token contém informações do usuário
     * (email e role) para autorização.
     *
     * @param loginRequest Dados de login (email e senha)
     * @return ResponseEntity com AuthResponse contendo token JWT ou erro
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Cria token de autenticação com email e senha
            UsernamePasswordAuthenticationToken authToken
                    = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

            // Autentica o usuário usando o AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(authToken);

            // Obtém os detalhes do usuário autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Busca o usuário completo no banco para obter o role
            Users user = repository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            // Gera token JWT com email e role do usuário
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

            // Cria resposta com token e informações do usuário
            AuthResponse response = new AuthResponse(token, user.getEmail(), user.getRole().name(), user.getName());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Retorna erro se autenticação falhar
            return ResponseEntity.badRequest().body("Credenciais inválidas: " + e.getMessage());
        }
    }
}
