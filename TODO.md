<<<<<<< HEAD
# TODO: Implementações para Sistema de Licenças AntVirus

## Passos Implementados
- [x] Atualizar enum AntiVirusStatus para 'active' e 'inactive'
- [x] Adicionar método no ServiceAntiVirus para verificar e atualizar licenças expiradas
- [x] Adicionar método no ServiceAntiVirus para listar licenças com máquinas associadas
- [x] Criar ControllerAntiVirus com rotas para CRUD e listagem de licenças
- [x] Verificar e ajustar vínculo entre máquina e licença no cadastro de máquinas (vínculo via campo antVirusLicense na classe Machines)
- [x] Implementar lógica para salvar máquina com vínculo à licença (campo antVirusLicenseId no MachineDTO)

## Próximos Passos Opcionais
- [ ] Adicionar scheduler para atualização automática de status de licenças expiradas
=======
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
>>>>>>> e148e592b7da71c59912e5aa41c1a99ff32441ab
