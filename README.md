# Ativos API

API REST desenvolvida em Java com Spring Boot para gerenciamento de ativos de TI.

Esse projeto é responsável por toda a regra de negócio da aplicação **Front Ativos**, armazenando, validando e disponibilizando as informações consumidas pelo Front-end.

O objetivo sempre foi construir uma API organizada, escalável e próxima de um ambiente corporativo, seguindo a arquitetura utilizada na maioria das aplicações Java modernas.

---

# Objetivo

Resolvi desenvolver essa API para centralizar essas informações em um banco de dados, disponibilizando tudo através de uma API REST que pode ser consumida por qualquer aplicação.

Atualmente ela é utilizada pelo projeto **Front Ativos**, desenvolvido em React.

---

# Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- Hibernate
- PostgreSQL
- Maven
- Docker
- Docker Compose

---

# Arquitetura

A aplicação segue uma arquitetura em camadas.

```
Controller

↓

Service

↓

Repository

↓

Banco de Dados
```

Cada camada possui uma responsabilidade específica.

### Controllers

Recebem as requisições HTTP e retornam as respostas da API.

### Services

Concentram toda a regra de negócio.

### Repositories

Realizam o acesso ao banco utilizando Spring Data JPA.

### Models

Representam as entidades persistidas.

### DTOs

Responsáveis por transportar dados entre cliente e servidor.

---

# Estrutura do projeto

```
src/main/java

config
│
controller
│
dtos
│
enums
│
models
│
repositorys
│
security
│
services
│
scheduler
│
utils
│
AtivosApplication.java
```

Cada pacote possui uma responsabilidade bem definida, facilitando futuras manutenções.

---

# Funcionalidades

Atualmente a API disponibiliza endpoints para gerenciamento de diversos ativos da empresa.

## Máquinas

Permite:

- cadastrar
- consultar
- editar
- remover

Informações armazenadas:

- Hostname
- Endereço IP
- Sistema Operacional
- Processador
- Memória
- Armazenamento
- Tipo de armazenamento
- Antivírus
- Licença Office
- Status

---

## Roteadores

Gerenciamento completo dos roteadores cadastrados.

---

## Impressoras

Cadastro e gerenciamento das impressoras.

---

## Ramais

Gerenciamento dos ramais telefônicos.

---

## Antivírus

Controle das informações relacionadas aos antivírus instalados.

---

## Usuários

A API também possui gerenciamento de usuários para autenticação da aplicação.

---

# Autenticação

O projeto utiliza autenticação baseada em JWT.

Fluxo:

```
Login

↓

Spring Security

↓

Validação do usuário

↓

Geração do Token JWT

↓

Cliente recebe Token

↓

Token enviado no Header

↓

API valida Token

↓

Acesso liberado
```

Essa abordagem evita manter sessões abertas no servidor e é amplamente utilizada em aplicações REST.

---

# Comunicação com o Front-end

O Front-end consome todos os recursos disponibilizados pela API.

Fluxo da comunicação:

```
React

↓

Axios

↓

HTTP REST

↓

Spring Boot

↓

Service

↓

Repository

↓

PostgreSQL
```

Após o processamento, a resposta retorna para o Front-end em formato JSON.

---

# Banco de dados

A persistência dos dados é realizada utilizando PostgreSQL.

O acesso ao banco acontece através do Spring Data JPA.

Isso elimina a necessidade de escrever SQL manual para as operações básicas.

Exemplo das operações disponíveis:

- INSERT
- UPDATE
- DELETE
- SELECT
- Paginação
- Consultas personalizadas

---

# Docker

O projeto possui configuração para execução utilizando Docker.

Isso permite subir toda a aplicação de maneira rápida e padronizada.

Além disso existe um backup do banco para facilitar testes e desenvolvimento.

---

# Scheduler

A aplicação também possui configuração para execução de tarefas agendadas.

Esse recurso permite automatizar processos periódicos quando necessário.

---

# Fluxo de uma requisição

Cadastro de máquina

```
Cliente

↓

POST /machines

↓

Controller

↓

Service

↓

Validação

↓

Repository

↓

PostgreSQL

↓

Repository

↓

Service

↓

Controller

↓

Resposta JSON
```

---

# Organização do código

Durante o desenvolvimento procurei separar bem as responsabilidades.

Isso evita que Controllers fiquem grandes e concentra toda a lógica de negócio dentro da camada Service.

Essa organização facilita testes, manutenção e futuras evoluções.

---

# Principais aprendizados

Durante esse projeto consegui praticar diversos conceitos importantes.

Entre eles:

- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- Docker
- PostgreSQL
- Arquitetura em Camadas
- DTOs
- Boas práticas em APIs REST
- Organização de projetos Java
- Integração com Front-end React

---

# Desafios encontrados

Durante o desenvolvimento alguns desafios precisaram ser resolvidos.

## CORS

Foi necessário configurar corretamente o Spring para permitir acesso do Front-end.

---

## Modelagem

Organizar as entidades mantendo o código desacoplado exigiu uma boa modelagem.

---

## Segurança

Implementar autenticação utilizando JWT foi um dos principais desafios do projeto.

---

## Integração

Manter Front-end e Back-end sincronizados durante a evolução da aplicação exigiu bastante atenção principalmente na estrutura dos DTOs.

---

# Como executar

## Clonar

```bash
git clone https://github.com/Bruno-FV/ativos.git
```

## Instalar dependências

```bash
mvn clean install
```

## Executar

```bash
mvn spring-boot:run
```

Ou utilizando sua IDE favorita.

---

# Roadmap

- ✅ CRUD de Máquinas
- ✅ CRUD de Roteadores
- ✅ CRUD de Impressoras
- ✅ CRUD de Ramais
- ✅ CRUD de Antivírus
- ✅ Autenticação JWT
- ✅ Docker
- ✅ PostgreSQL
- ⏳ Dashboard administrativo
- ⏳ Auditoria
- ⏳ Logs centralizados
- ⏳ Testes automatizados
- ⏳ Documentação Swagger

---

# Considerações finais

Esse projeto representa a parte Back-end da aplicação Front Ativos.

Durante o desenvolvimento procurei seguir uma arquitetura organizada, separando responsabilidades entre Controllers, Services, Repositories e Models.

Além de servir como projeto de estudo, ele foi pensado para reproduzir a estrutura utilizada em aplicações corporativas, permitindo praticar conceitos como autenticação JWT, integração com banco de dados, arquitetura em camadas e desenvolvimento de APIs REST utilizando Spring Boot.
