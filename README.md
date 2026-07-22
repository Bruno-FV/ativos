# 🖥️ Ativos - Sistema de Gerenciamento de Ativos

Gerenciamento inteligente de máquinas, roteadores e extensões com monitoramento automático.

![Java](https://img.shields.io/badge/Java-17-007396?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-6DB33F?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13+-4169E1?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker)

---

## Sobre o Projeto

**Ativos** é um sistema backend desenvolvido para controlar e monitorar o parque tecnológico de uma empresa. Permite o cadastro, consulta e gerenciamento de ativos como máquinas, roteadores e extensões, com verificação automática de status via ping.

Ideal para empresas de TI, suporte técnico e gerenciamento de infraestrutura.

---

## ✨ Funcionalidades

- **CRUD completo** de Ativos (Máquinas, Roteadores, Extensões)
- **Autenticação e Autorização** com **JWT + Spring Security**
- **Monitoramento automático** de status das máquinas (Scheduler)
- **API REST** bem documentada e organizada
- **Suporte a Docker** (aplicação + banco PostgreSQL)
- **Banco de dados** PostgreSQL (com suporte a H2 para testes)
- Validações, DTOs e tratamento de exceções

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3** (Web, Data JPA, Security, Actuator)
- **JWT** (JSON Web Tokens)
- **PostgreSQL**
- **Lombok**
- **Maven**
- **Docker & Docker Compose**
- **Spring Scheduler**

---

## 🚀 Como Executar

### 1. Com Docker (Recomendado)

```bash
# Clone o repositório
git clone https://github.com/Bruno-FV/ativos.git
cd ativos

# Subir aplicação + banco
docker compose up --build
