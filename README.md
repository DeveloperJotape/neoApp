
# NeoApp - API RESTful de Cadastro de Clientes

[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.2-brightgreen)](https://spring.io/projects/spring-boot)

Bem-vindo ao **NeoApp**, uma API RESTful para cadastro de clientes pessoa física, desenvolvida como parte do processo seletivo para a vaga de **Desenvolvedor Back-End Estagiário**. Esta API oferece operações completas de CRUD em clientes, com autenticação JWT e documentação via Swagger.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.2**
- **Spring Security** com JWT
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Lombok**
- **JJWT** para tokens JWT
- **Springdoc OpenAPI** (Swagger)
- **Maven** para gerenciamento de dependências

---

## ⚙️ Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- Insomnia, Postman ou similar para testar a API

---

## 💻 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/DeveloperJotape/neoApp.git
   cd neoApp
   ```
2. Execute a aplicação com Maven

3. A aplicação estará disponível em:  
   ```
   http://localhost:8080
   ```

---

## 📄 Documentação e Banco de Dados

- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **H2 Console:** `http://localhost:8080/h2-console`
  - **JDBC URL:** `jdbc:h2:mem:neo_db`
  - **Usuário:** `sa`
  - **Senha:** (vazia)

---

## 🔐 Autenticação e Autorização

A API utiliza **JWT (JSON Web Tokens)** para autenticação.

### Registro de Usuário
Crie um usuário com POST em `/auth/register`:

```json
{
  "nome": "Seu Nome",
  "email": "seu.email@exemplo.com",
  "senha": "sua-senha",
  "cargo": "ADMIN"
}
```

Cargos possíveis: `ADMIN` ou `USER`.

### Login
Obtenha um token com POST em `/auth/login`:

```json
{
  "email": "seu.email@exemplo.com",
  "senha": "sua-senha"
}
```

A resposta incluirá um JWT:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

Use o token no header das requisições protegidas:

```
Authorization: Bearer <token>
```

---

## 📌 Endpoints da API

### Clientes

| Método | Endpoint | Descrição | Role |
|--------|---------|-----------|------|
| GET    | /clientes | Lista clientes (com paginação e filtros) | USER/ADMIN |
| GET    | /clientes/{id} | Busca cliente por ID | USER/ADMIN |
| POST   | /clientes | Cria um novo cliente | ADMIN |
| PUT    | /clientes/{id} | Atualiza cliente | ADMIN |
| DELETE | /clientes/{id} | Exclui cliente | ADMIN |

### Filtros de Busca (GET /clientes)

- `nome` → Filtra por nome (parcial, case insensitive)
- `cpf` → Filtra por CPF (exato)
- `email` → Filtra por email (parcial, case insensitive)
- `page` → Número da página (default: 0)
- `size` → Tamanho da página (default: 10)

### Exemplo de Cliente
```json
{
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao@email.com",
  "dataNascimento": "1990-01-01",
  "telefone": "11999999999",
  "endereco": "Rua A, 123"
}
```

---

## 🏗️ Estrutura do Projeto

```
src/main/java/br/com/devjoaopedro/neo/
├── config/        # Configurações (Security, CORS)
├── controller/    # Controladores REST
├── dto/           # Objetos de Transferência de Dados
├── exception/     # Tratamento de exceções
├── model/         # Entidades (Cliente, Usuario)
├── repository/    # Repositórios Spring Data JPA
├── security/      # Filtros e configs de segurança
└── service/       # Lógica de negócio e serviços
```

---

## ⚠️ Considerações Importantes

- O banco H2 é **em memória**, os dados são perdidos ao reiniciar a aplicação.
- Senhas são criptografadas com **BCrypt**.
- A idade do cliente é calculada automaticamente a partir da data de nascimento.
- A API está configurada para operações **stateless** com JWT.

---

## 📫 Contato

Desenvolvedor: **João Pedro Nascimento Conceição**  
GitHub: [DeveloperJotape](https://github.com/DeveloperJotape)  

> Este projeto foi desenvolvido com fins de aprendizado e participação em processo seletivo.
