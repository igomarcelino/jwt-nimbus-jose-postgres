<p align="center">

⬅️ <a href="06-login.md">Anterior</a> •
🏠 <a href="../README.md">Início</a> •
➡️<a href="08-testes.md"> Proximo</a>

</p>
# Capítulo 7 - O SecurityContext e a Proteção dos Endpoints

> Até este momento nossa aplicação já é capaz de autenticar usuários e gerar Tokens JWT. Neste capítulo veremos como o Spring Security utiliza esse Token para identificar automaticamente o usuário em todas as requisições protegidas.

---

# O que você aprenderá

Ao final deste capítulo você será capaz de:

- Criar o `AuthController`;
- Criar um endpoint de Login;
- Compreender como o JWT é enviado pelo cliente;
- Entender como o OAuth2 Resource Server valida automaticamente o Token;
- Descobrir como o `SecurityContextHolder` é criado;
- Recuperar o usuário autenticado em qualquer Controller.

---

# O ciclo de vida do JWT

Depois que o Login é realizado, o cliente recebe um JWT.

```json
{
    "accessToken":"eyJhbGciOiJSUzI1NiJ9..."
}
```

Nas próximas requisições esse Token deverá ser enviado através do Header HTTP:

```http
Authorization: Bearer eyJhbGciOiJSUzI1NiJ9...
```

A partir desse momento toda requisição seguirá um fluxo diferente.

---

# Fluxo da autenticação

```mermaid
flowchart LR

A[Cliente]

-->B[Authorization Bearer]

-->C[Security Filter Chain]

-->D[OAuth2 Resource Server]

-->E[JwtDecoder]

-->F{Token válido?}

F -->|Não| G[401 Unauthorized]

F -->|Sim| H[Authentication]

H --> I[SecurityContextHolder]

I --> J[Controller]

J --> K[Service]

K --> L[(PostgreSQL)]
```

Observe que nenhuma requisição chega diretamente ao Controller.

Primeiro ela passa pela cadeia de filtros do Spring Security.

---

# Criando o AuthController

Agora criaremos o Controller responsável pelo Login.

Estrutura:

```text
controller
└── AuthController.java
```

```java
@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        var usuario = loginService.autenticaUsuario(request);
        return ResponseEntity.ok(usuario);
    }
}
```

Esse endpoint será responsável por receber as credenciais do usuário e devolver um Token JWT.

---

# O endpoint de Login

Nossa aplicação agora possui o endpoint:

```
POST /auth/login
```

Requisição:

```json
{
    "email":"igo@email.com",
    "senha":"123456"
}
```

Resposta:

```json
{
    "accessToken":"eyJhbGc..."
}
```

---

# Utilizando o Token

Depois de autenticado, basta enviar o Token em qualquer requisição protegida.

Exemplo:

```http
GET /pessoa

Authorization: Bearer eyJhbGc...
```

Nenhuma informação adicional será necessária.

O próprio Spring Security identificará o usuário.

---

# Como o Spring valida o Token?

Quando uma requisição chega à aplicação, o OAuth2 Resource Server executa automaticamente as seguintes etapas:

1. Localiza o Header Authorization;
2. Extrai o Bearer Token;
3. Utiliza o `JwtDecoder`;
4. Valida a assinatura utilizando a chave pública;
5. Verifica a data de expiração;
6. Extrai todos os Claims;
7. Cria um objeto `Authentication`;
8. Armazena esse objeto no `SecurityContextHolder`.

Todo esse processo acontece antes da execução do Controller.

---

# O que é o SecurityContextHolder?

O `SecurityContextHolder` é uma estrutura mantida pelo Spring Security que armazena as informações do usuário autenticado durante o processamento da requisição.

Depois que o JWT é validado, esse objeto passa a conter:

- usuário autenticado;
- authorities;
- permissões;
- status da autenticação.

Sempre que precisarmos descobrir quem está autenticado, consultaremos o `SecurityContextHolder`.

---

# Recuperando o usuário autenticado

Existem diversas maneiras de acessar o usuário logado.

A mais simples é utilizando o parâmetro `Authentication`.

```java
@GetMapping("/me")
public Authentication usuario(Authentication authentication){

    return authentication;

}
```

O Spring injeta automaticamente o objeto criado durante a autenticação.

---

Também podemos acessar apenas o nome do usuário.

```java
@GetMapping("/email")
public String email(Authentication authentication){

    return authentication.getName();

}
```

Resultado:

```
igo@email.com
```

---

# Recuperando o usuário completo

Caso desejemos acessar nossa entidade `Pessoa`, podemos utilizar:

```java
@GetMapping("/perfil")
public Pessoa perfil(Authentication authentication){

    return (Pessoa) authentication.getPrincipal();

}
```

Como nossa entidade implementa `UserDetails`, ela pode ser recuperada diretamente.

---

# Fluxo completo da requisição

```mermaid
sequenceDiagram

participant Cliente

participant SecurityFilterChain

participant JwtDecoder

participant SecurityContextHolder

participant Controller

Cliente->>SecurityFilterChain: Authorization Bearer

SecurityFilterChain->>JwtDecoder: Validar JWT

JwtDecoder-->>SecurityFilterChain: JWT válido

SecurityFilterChain->>SecurityContextHolder: Cria Authentication

SecurityContextHolder-->>Controller: Usuário autenticado

Controller-->>Cliente: Resposta
```

---

# O que construímos neste capítulo?

Nossa aplicação agora é capaz de:

- Receber Tokens JWT;
- Validar automaticamente a assinatura;
- Criar o `SecurityContextHolder`;
- Identificar o usuário autenticado;
- Acessar endpoints protegidos.

Todo esse processo acontece automaticamente graças ao Spring Security.

---

# Próximo capítulo

Agora que toda a infraestrutura de autenticação está concluída, chegou o momento de validar seu funcionamento através de testes utilizando o Bruno.

Também iremos verificar:

- Login;
- Geração do JWT;
- Acesso aos endpoints protegidos;
- Respostas `401 Unauthorized`;
- Respostas `403 Forbidden`;
- Controle de permissões baseado em Roles.

➡️ 

<p align="center">

⬅️ <a href="06-login.md">Anterior</a> •
🏠 <a href="../README.md">Início</a> •
➡️<a href="08-testes.md"> **Capítulo 8 — Testando a Aplicação com Bruno**</a>

</p>