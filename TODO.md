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
