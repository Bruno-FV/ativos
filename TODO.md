# TODO - Implementação da Autenticação JWT

## Passos a Implementar:
- [x] Adicionar dependências JWT ao pom.xml
- [x] Criar classe JwtUtil para manipulação de tokens
- [x] Criar DTOs para requisição de login e resposta de autenticação
- [x] Implementar endpoint de login no AuthController
- [x] Atualizar SecurityConfig para usar autenticação JWT
- [x] Testar o fluxo de autenticação

## Arquivos a Serem Modificados:
- pom.xml
- src/main/java/com/hsr/ativos/security/JwtUtil.java (novo)
- src/main/java/com/hsr/ativos/dtos/LoginRequest.java (novo)
- src/main/java/com/hsr/ativos/dtos/AuthResponse.java (novo)
- src/main/java/com/hsr/ativos/controller/AuthController.java
- src/main/java/com/hsr/ativos/security/SecurityConfig.java
