
# 🚀 JWT Nimbus JOSE

Boilerplate desenvolvido com Spring Boot 3 para aplicações REST seguras utilizando Spring Security, OAuth2 Resource Server e Nimbus JOSE + JWT, seguindo boas práticas de arquitetura, testes e organização de código.
---

# Tecnologias

- Java 17
- Spring Boot 3
- Spring Security
- OAuth2 Resource Server
- Nimbus JOSE + JWT
- Spring Data JPA
- PostgreSQL
- MapStruct
- Lombok
- JUnit 5
- Mockito
- Testcontainers
- Maven

---

# Objetivos do Projeto

Este boilerplate possui como objetivo fornecer uma estrutura pronta contendo:

- Cadastro de usuários
- Controle de Roles
- Login utilizando JWT
- Estrutura para OAuth2 Resource Server
- Arquitetura em camadas
- DTOs
- Mappers utilizando MapStruct
- Tratamento de exceções
- Testes Unitários
- Testes de Integração
- Configuração de Logs
- Base para futuros projetos

---

# Estrutura do Projeto

```
src
├── config
├── controller
├── dto
├── mapper
├── model
├── repository
├── service
├── security (em desenvolvimento)
├── exception
└── test
```

---

# Fluxo da aplicação

1. Cadastro do usuário
2. Atribuição das Roles
3. Login
4. Geração do JWT
5. Validação automática pelo OAuth2 Resource Server
6. População do SecurityContextHolder
7. Autorização baseada em Scopes

---

# Endpoints

## Pessoa

| Método | Endpoint | Descrição |
|---------|----------|-----------|
| POST | /pessoa | Cadastra uma pessoa |
| GET | /pessoa | Lista todas as pessoas |
| PUT | /pessoa/set-roles/{id} | Atualiza as Roles |

---

## Avisos

| Método | Endpoint | Descrição |
|---------|----------|-----------|
| POST | /aviso | Cadastra um aviso |
| GET | /aviso | Lista avisos |
| PATCH | /aviso/{id} | Marca aviso como lido |

---

## Autenticação

| Método | Endpoint |
|---------|----------|
| POST | /auth/login |

---

# Exemplo de Login

Request

```json
{
    "email":"igo@email.com",
    "senha":"1234"
}
```

Response

```json
{
    "accessToken":"eyJhbGciOi..."
}
```

---

# Login utilizando Bruno

> Exemplo realizando autenticação e recebendo um JWT.

![Login](docs/images/login.png)

---

# Modelo Relacional

O projeto utiliza um modelo simples para demonstrar autenticação baseada em usuários, roles e avisos.

![Diagrama](docs/images/modelo-relacional.png)

---

# Testes

O projeto segue uma separação entre testes unitários e testes de integração.

## Testes Unitários

Utilizando

- JUnit 5
- Mockito

Responsáveis por validar apenas a lógica da aplicação.

Exemplo:

```
PessoaServiceTest
AvisoServiceTest
RolesServiceTest
```

---

## Testes de Integração

Utilizando

- Spring Boot Test
- Testcontainers
- PostgreSQL

Responsáveis por validar:

- Persistência
- Mapeamentos JPA
- Repositories
- Services
- Transações

Exemplo:

```
PessoaServiceIT
AvisoServiceIT
```

---

# Logging

O projeto possui configuração de logging para facilitar depuração durante o desenvolvimento.

- Logs coloridos no console
- Arquivo de log
- Nível DEBUG apenas para a aplicação
- WARN para Spring e Hibernate

---

# Próximas Implementações

- [x] CRUD Pessoa
- [x] CRUD Aviso
- [x] Roles
- [x] Login
- [ ] JWT Nimbus JOSE
- [ ] OAuth2 Resource Server
- [ ] Refresh Token
- [ ] Testes de Segurança
- [ ] Docker
- [ ] Docker Compose
- [ ] GitHub Actions
- [ ] Documentação OpenAPI

---

# Autor

Igo Marcelino

Backend Java Developer

