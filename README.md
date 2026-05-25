<h1 align="center">🛡️Capítulo 03 — Validation & Security with Spring Boot | OAuth2 | JWT</h1>

<p align="justify">
<em>
This chapter focuses on building secure, production-ready backend APIs using <strong>Spring Security</strong>, <strong>OAuth2</strong>, <strong>JWT</strong>, and <strong>Bean Validation</strong>, applying modern authentication, authorization, and data validation strategies aligned with real-world enterprise applications.
</em>
</p>

<p align="center">

<img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk&logoColor=white" />

<img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />

<img src="https://img.shields.io/badge/Security-Spring_Security-red?style=for-the-badge" />

<img src="https://img.shields.io/badge/Auth-OAuth2%20%7C%20JWT-critical?style=for-the-badge" />

<img src="https://img.shields.io/badge/Validation-Bean_Validation-blue?style=for-the-badge" />

<img src="https://img.shields.io/badge/Authorization-RBAC-success?style=for-the-badge" />

<img src="https://img.shields.io/badge/API_Security-Resource_Server-informational?style=for-the-badge" />

<img src="https://img.shields.io/badge/Architecture-Authorization_Server-black?style=for-the-badge" />

<img src="https://img.shields.io/badge/Testing-Spring_Security_Test-yellow?style=for-the-badge" />

<img src="https://img.shields.io/badge/Documentation-Swagger%20%7C%20OpenAPI-85EA2D?style=for-the-badge" />

<img src="https://img.shields.io/github/license/Albertinesilva/backend-engineering-journey-java-springboot?style=for-the-badge" />

<img src="https://img.shields.io/github/last-commit/Albertinesilva/backend-engineering-journey-java-springboot?style=for-the-badge" />

</p>

<p align="justify">
<em>
Neste capítulo, o projeto <strong>DSCatalog</strong> evolui para um cenário muito mais próximo de aplicações corporativas reais, incorporando mecanismos robustos de autenticação, autorização e validação de dados utilizando o ecossistema moderno do <strong>Spring Security 6</strong>.

Além da proteção de endpoints REST, foram aplicados conceitos fundamentais de segurança backend moderna, incluindo <strong>OAuth2 Authorization Server</strong>, <strong>Resource Server</strong>, autenticação stateless com <strong>JWT</strong>, controle de acesso baseado em roles, tratamento global de erros de validação e integração segura com Swagger/OpenAPI.
</em>

</p>

---

# 📚 Contexto do Projeto

Após a consolidação da arquitetura em camadas e da estratégia de testes automatizados nos capítulos anteriores, o projeto evolui para uma nova etapa focada em autenticação, autorização e validação robusta.

Neste capítulo, a API DSCatalog passa a incorporar mecanismos modernos de segurança utilizando Spring Security 6, OAuth2, JWT e Bean Validation, aproximando a aplicação de cenários reais utilizados em ambientes corporativos.

---

# 🎯 Objetivos do Capítulo

Este capítulo tem como objetivo transformar a API DSCatalog em uma aplicação backend preparada para cenários reais de autenticação e segurança corporativa.

---

## 🔹 1. Aplicar validação robusta com Bean Validation

A aplicação passou a utilizar validações declarativas para garantir integridade e previsibilidade dos dados recebidos pela API.

## 📌 Principais validações aplicadas

- `@NotBlank`
- `@NotNull`
- `@Size`
- `@Email`
- `@Positive`
- `@PastOrPresent`
- Validações customizadas
- Integração com banco de dados
- Mensagens personalizadas
- Tratamento global de erros

### 📌 Benefícios

- Integridade dos dados
- Contratos HTTP previsíveis
- Redução de inconsistências
- Segurança contra entradas inválidas
- Melhor experiência para consumidores da API

---

### ⚠️ Observações e recomendações — Validação

- **Centralizar mensagens:** mover todas as mensagens de validação para `ValidationMessages.properties` e usar chaves (`user.password.length`, `user.email.invalid`, etc.) nos validators/annotations em vez de strings hardcoded.
- **Remover duplicações:** alguns validadores e annotations (ex.: `StrongPassword`, `UniqueEmail`, `UserCreateValidator`) retornam mensagens em código; padronize para usar mensagens do properties para evitar inconsistências.
- **Padronizar chaves:** seguir um prefixo consistente (`user.`, `product.`, `category.`) e documentar os nomes das chaves para facilitar manutenção e i18n.
- **Internacionalização (i18n):** preparar arquivos `ValidationMessages_{locale}.properties` quando suportar múltiplos idiomas.
- **Testes de validação:** adicionar testes unitários para validators customizados (ex.: `StrongPasswordValidator`, `UserCreateValidator`, `ValidRolesValidator`) cobrindo casos limites.


## 🔹 2. Implementar autenticação moderna com OAuth2 e JWT

A autenticação da aplicação foi construída utilizando uma arquitetura baseada em:

- Spring Security 6
- OAuth2 Authorization Server
- JWT
- BCrypt Password Encoder

### 📌 Recursos implementados

- Geração segura de tokens JWT
- Assinatura de tokens
- Expiração configurável
- Login via OAuth2 Password Flow
- Registro de aplicações clientes
- Controle de acesso baseado em roles

### ⚠️ Observações e recomendações — Segurança

- **Reavaliar uso do Password Flow:** o fluxo Resource Owner Password Credentials (password grant) está implementado com custom provider (`CustomPasswordAuthenticationProvider`). Esse fluxo é legado para clientes públicos; prefira Authorization Code + PKCE para aplicações móveis/web públicas.
- **Gerenciamento de chaves:** evite gerar chaves RSA em memória na inicialização; utilize keystores ou serviços de segredo (Vault, Azure Key Vault) e não armazene chaves em repositório.
- **Persistência de autorizações:** o uso de `InMemoryOAuth2AuthorizationService` é adequado para desenvolvimento; para produção, persista autorizações/tokens em banco para suportar revogação e múltiplas instâncias.
- **Segurança do H2 Console e CORS:** mantenha o H2 Console somente em `dev/test` (já condicional) e restrinja CORS a origins conhecidos em `application-*.properties`.
- **Segredos em ambiente:** garanta que `security.client-secret` e variáveis sensíveis sejam fornecidas via variáveis de ambiente ou secret manager (evite valores padrão em código/repo).
- **Políticas adicionais:** adicionar proteção contra força bruta, política de bloqueio de contas, rotação de chaves e logging/auditoria para operações sensíveis.

## 🔹 3. Proteger endpoints da API

A API passou a possuir controle de acesso baseado em autenticação e autorização utilizando Spring Security e RBAC (Role-Based Access Control).

A estratégia aplicada combina:

- rotas públicas;
- rotas autenticadas;
- controle por roles;
- segurança em nível de método;
- autorização granular por endpoint.

---

### 📌 Estratégia de acesso aplicada

| Tipo de rota              | Acesso      |
| ------------------------- | ----------- |
| Swagger/OpenAPI           | Público     |
| Categorias (GET)          | Público     |
| Produtos (GET)            | Público     |
| Demais endpoints          | Autenticado |
| Endpoints administrativos | ROLE_ADMIN  |

---

### 📌 Segurança em nível de método

```java
@PreAuthorize("hasRole('ROLE_ADMIN')")

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
```

### 📌 Exemplo de configuração de autorização
```java
.requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
.requestMatchers(DOCUMENTATION_OPENAPI).permitAll()
.anyRequest().authenticated()
```

### 📌 Fluxo de autorização da requisição
1. Cliente envia Bearer Token
2. Resource Server intercepta a requisição
3. JWT é validado
4. Roles do usuário são verificadas
5. Endpoint é liberado ou bloqueado

---

## 🔹 4. Aplicar boas práticas modernas de segurança

Foram implementadas estratégias utilizadas em APIs REST modernas e ambientes corporativos.

### 📌 Práticas aplicadas

- Controle de acesso por roles
- Configuração de CORS
- Desabilitação controlada de CSRF
- Separação entre Authorization Server e Resource Server
- Configuração por perfis (`dev`, `test`, `prod`)
- Proteção da documentação Swagger
- Externalização de variáveis sensíveis

---

# 🧠 Conceitos Fundamentais Trabalhados

| Conceito             | Objetivo                                 |
| -------------------- | ---------------------------------------- |
| Authentication       | Verificar identidade do usuário          |
| Authorization        | Verificar permissões do usuário          |
| OAuth2               | Protocolo de autorização                 |
| JWT                  | Token seguro para autenticação stateless |
| Bean Validation      | Validação declarativa de dados           |
| Method Security      | Proteção em nível de métodos             |
| Resource Server      | Proteção dos recursos da API             |
| Authorization Server | Emissão e gerenciamento de tokens        |

---

## 🏛️ Arquitetura Geral de Segurança

A estratégia de segurança da aplicação foi construída utilizando uma arquitetura baseada em separação clara entre autenticação, autorização e proteção de recursos, seguindo padrões modernos utilizados em APIs REST corporativas.

A aplicação foi dividida em dois componentes principais:

- **Authorization Server**
- **Resource Server**

Essa separação permite maior desacoplamento, escalabilidade e aderência ao ecossistema OAuth2 moderno.

---

### 🔐 Fluxo Arquitetural da Segurança

```text
┌──────────────────────┐
│      Client App      │
│  Frontend / Postman  │
└──────────┬───────────┘
           │
           │ Login Request
           ▼
┌────────────────────────────┐
│   Authorization Server     │
│ OAuth2 + Spring Security   │
│ JWT Generation             │
└──────────┬─────────────────┘
           │
           │ JWT Token
           ▼
┌────────────────────────────┐
│      Resource Server       │
│ JWT Validation             │
│ Route Protection           │
│ Method Security            │
└──────────┬─────────────────┘
           │
           ▼
┌────────────────────────────┐
│     Protected Endpoints    │
│ Products | Categories      │
│ Users | Roles              │
└────────────────────────────┘
```

---

### 📌 Benefícios da Arquitetura

- Separação entre autenticação e autorização
- Maior modularidade
- Escalabilidade horizontal
- Melhor manutenção evolutiva
- Estrutura próxima de ambientes enterprise reais

---

## 🛠️ Tecnologias Utilizadas

## 🔐 Segurança

| Tecnologia                  | Função                                     |
| --------------------------- | ------------------------------------------ |
| Spring Security             | Framework principal de segurança           |
| OAuth2 Authorization Server | Emissão de tokens JWT                      |
| OAuth2 Resource Server      | Validação de tokens e proteção de recursos |
| JWT                         | Autenticação stateless baseada em token    |
| BCryptPasswordEncoder       | Criptografia segura de senhas              |

---

## 🧾 Validação

| Tecnologia          | Função                                         |
| ------------------- | ---------------------------------------------- |
| Bean Validation     | Validação declarativa                          |
| Hibernate Validator | Implementação da especificação Bean Validation |
| Jakarta Validation  | API padrão de validação                        |

---

## 📄 Documentação

| Tecnologia        | Função                      |
| ----------------- | --------------------------- |
| SpringDoc OpenAPI | Documentação automática     |
| Swagger UI        | Interface interativa da API |

---

## 🧪 Testes

| Tecnologia           | Função                             |
| -------------------- | ---------------------------------- |
| Spring Security Test | Testes de autenticação/autorização |
| MockMvc              | Testes de endpoints protegidos     |
| JUnit 5              | Testes automatizados               |
| Mockito              | Mocking de dependências            |

---

# 📦 Dependências Adicionadas

## 🔐 Spring Security

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

---

## 🎫 OAuth2 Authorization Server

```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-oauth2-authorization-server</artifactId>
</dependency>
```

## 🛡️ OAuth2 Resource Server

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```

---

## 🧪 Spring Security Test

```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```

---

# 👥 Modelo de Usuários e Perfis

O projeto passou a possuir um modelo de autenticação baseado em:

- Usuários
- Perfis (roles)
- Relacionamentos entre usuários e permissões

## 📌 Perfis utilizados

| Perfil        | Responsabilidade         |
| ------------- | ------------------------ |
| ROLE_ADMIN    | Controle total da API    |
| ROLE_OPERATOR | Operações intermediárias |

---

## 🖼️ Modelagem de Usuários e Perfis

<img src="https://raw.githubusercontent.com/Albertinesilva/backend-engineering-journey-java-springboot/chapter-03-validation-security/docs/assets/imgs/modelo-conceitual.png" width="80%">

## 🔐 Fluxo de Autenticação

### 📌 Processo de Login

1. Cliente envia credenciais
2. Authorization Server autentica usuário
3. Token JWT é gerado
4. Cliente recebe token
5. Requisições utilizam Bearer Token
6. Resource Server valida JWT
7. Spring Security verifica permissões
8. API libera ou bloqueia acesso

---

## 🔄 Requisição de Login

### Authorization

```text
Type: Basic Auth
Username: client-id
Password: client-secret
```

### Body (x-www-form-urlencoded)

```text
username=alex@gmail.com
password=123456
grant_type=password
```

---

## 🎫 Estrutura JWT

Os tokens JWT utilizados carregam informações importantes como:

- Usuário autenticado
- Authorities/Roles
- Tempo de expiração
- Issuer
- Audience

### 📄 Exemplo de Payload JWT

```json
{
  "sub": "alex@gmail.com",
  "scope": "ROLE_ADMIN ROLE_OPERATOR",
  "iat": 1716562000,
  "exp": 1716648400,
  "iss": "Spring Authorization Server"
}
```

### 📌 Significado dos campos

| Campo | Descrição |
|---|---|
| `sub` | Usuário autenticado |
| `scope` | Roles/permissões do usuário |
| `iat` | Data de emissão do token |
| `exp` | Data de expiração |
| `iss` | Emissor do token |

---

## ⚙️ Configurações da Aplicação

### 📌 Properties de Segurança

```properties
security.client-id=${CLIENT_ID:myclientid}
security.client-secret=${CLIENT_SECRET:myclientsecret}
security.jwt.duration=${JWT_DURATION:86400}
cors.origins=${CORS_ORIGINS:http://localhost:3000,http://localhost:5173}
```

---

## 🛡️ Authorization Server

O Authorization Server é responsável por:

- Autenticar usuários
- Gerar tokens JWT
- Assinar tokens
- Registrar aplicações clientes
- Gerenciar autenticação OAuth2

### 📌 Responsabilidades implementadas

- Habilitação do Authorization Server
- Configuração de assinatura JWT
- Configuração de Password Encoder
- Registro de client OAuth2
- Configuração de token JWT
- Definição de duração do token

---

## 🔐 Resource Server

O Resource Server é responsável por:

- Validar assinatura e expiração do JWT
- Converter claims em authorities do Spring Security
- Integrar autenticação stateless ao Security Filter Chain
- Aplicar políticas de autorização definidas no ResourceServerConfig

### 📌 Configurações aplicadas

- Controle de acesso por rota
- Configuração de CORS
- Configuração de CSRF
- Validação JWT
- Liberação do Swagger/OpenAPI
- Liberação do H2 Console em ambiente de teste

---

## 🔒 Exemplo de Requisição Autenticada

### 📌 Endpoint protegido

```http
GET /products
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

### 📌 Exemplo de resposta

```json
{
    "content": [
        {
            "id": 1,
            "name": "The Lord of the Rings",
            "description": "Classico da literatura de fantasia que narra a jornada épica na Terra Média.",
            "price": 90.5,
            "imgUrl": "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg",
            "date": "2020-07-13T20:50:07.123450Z",
            "categories": []
        },
        {
            "id": 2,
            "name": "Smart TV",
            "description": "Smart TV com alta resolução, acesso a streaming e conectividade Wi-Fi.",
            "price": 2190.0,
            "imgUrl": "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg",
            "date": "2020-07-14T10:00:00Z",
            "categories": []
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 2,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalElements": 25,
    "totalPages": 13,
    "last": false,
    "size": 2,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
}
```

---

### 📌 Fluxo da requisição

1. Cliente envia Bearer Token
2. Resource Server intercepta requisição
3. JWT é validado
4. Roles do usuário são verificadas
5. Endpoint é liberado ou negado

---

## 🧾 Bean Validation

A aplicação utiliza Bean Validation para garantir integridade, consistência e previsibilidade dos dados recebidos pela API.

A estratégia adotada combina validações declarativas, validações customizadas e integração com regras de negócio da aplicação, permitindo respostas padronizadas e maior segurança no processamento das requisições.

---

### 📌 Estratégia aplicada

- Validações declarativas em DTOs
- Integração com Hibernate Validator
- Validações customizadas por domínio
- Mensagens externalizadas
- Integração com banco de dados
- Tratamento global de exceções
- Respostas padronizadas para erros de validação

---

### 📌 Principais validações utilizadas

| Validação | Objetivo |
|---|---|
| `@NotBlank` | Garantir campos textuais obrigatórios |
| `@NotNull` | Impedir valores nulos |
| `@Size` | Restringir tamanho mínimo e máximo |
| `@Email` | Validar formato de email |
| `@Positive` | Garantir valores numéricos positivos |
| `@PastOrPresent` | Validar datas válidas |
| Custom Validators | Regras específicas da aplicação |

---

### 📌 Exemplos de validação

```java
@NotBlank(message = "Campo requerido")

@Email(message = "Email inválido")

@Size(min = 3, max = 80)

@Positive(message = "Valor deve ser positivo")
```

---

### 📌 Validações customizadas implementadas

Além das validações padrão da especificação Bean Validation, a aplicação também implementa validações customizadas integradas às regras de negócio.

### Exemplos

- `@StrongPassword`
- `@UniqueEmail`
- `@ValidRoles`
- `UserCreateValidator`

Essas validações permitem aplicar regras mais complexas, incluindo integração com banco de dados e validações contextuais da aplicação.

---

### 📌 Benefícios da abordagem

- Maior integridade dos dados
- Redução de inconsistências
- Contratos HTTP mais previsíveis
- Melhor experiência para consumidores da API
- Centralização das regras de validação
- Melhor rastreabilidade de erros

---

## 🔄 Tratamento Global de Validações

A aplicação possui um mecanismo centralizado para tratamento de exceções e erros de validação.

### 📌 Benefícios

- Padronização das respostas
- Melhor integração frontend/backend
- Clareza de erros
- Rastreabilidade
- Melhor experiência de consumo da API

---

### 📄 Exemplo de resposta de validação

```json
{
  "timestamp": "2026-05-24T19:40:35.468801500Z",
  "status": 422,
  "error": "Validation Error",
  "message": "One or more fields are invalid",
  "path": "/api/v1/users",
  "fieldErrors": [
    {
      "fieldName": "firstName",
      "message": "Primeiro nome deve ter entre 2 e 80 caracteres"
    },
    {
      "fieldName": "password",
      "message": "Senha contém padrões muito comuns e inseguros"
    },
    {
      "fieldName": "firstName",
      "message": "Primeiro nome é obrigatório"
    },
    {
      "fieldName": "password",
      "message": "Senha deve possuir ao menos 10 caracteres"
    },
    {
      "fieldName": "password",
      "message": "Senha deve conter ao menos uma letra minúscula"
    },
    {
      "fieldName": "email",
      "message": "Email já existente"
    },
    {
      "fieldName": "roleIds",
      "message": "Usuário deve possuir ao menos uma role"
    },
    {
      "fieldName": "lastName",
      "message": "Sobrenome deve ter entre 2 e 80 caracteres"
    },
    {
      "fieldName": "lastName",
      "message": "Sobrenome é obrigatório"
    },
    {
      "fieldName": "password",
      "message": "Senha deve conter ao menos uma letra maiúscula"
    },
    {
      "fieldName": "password",
      "message": "Senha deve conter ao menos um caractere especial"
    }
  ]
}
```

---

## 📂 Organização dos Packages

A estrutura da camada de segurança foi organizada seguindo princípios de separação de responsabilidades, modularidade e baixo acoplamento, permitindo maior clareza arquitetural, facilidade de manutenção e escalabilidade evolutiva.

O objetivo foi estruturar cada responsabilidade de segurança de forma isolada e coesa, aproximando a aplicação de arquiteturas utilizadas em projetos corporativos modernos com Spring Security.

---

## 🖼️ Organização da Camada de Segurança

```text
security
┣ config
┃ ┗ SecurityBeansConfig.java
┃
┣ oauth2
┃ ┣ authorization
┃ ┃ ┗ config
┃ ┃   ┗ AuthorizationServerConfig.java
┃ ┃
┃ ┣ grant_password
┃ ┃ ┣ CustomPasswordAuthenticationConverter.java
┃ ┃ ┣ CustomPasswordAuthenticationProvider.java
┃ ┃ ┗ CustomPasswordAuthenticationToken.java
┃ ┃
┃ ┗ resource
┃   ┗ ResourceServerConfig.java
┃
┣ userdetails
┃ ┗ AuthenticatedUser.java
┃
┣ service
┃ ┣ exception
┃ ┣ CategoryService.java
┃ ┣ ProductService.java
┃ ┗ UserService.java
┃
┣ validation
┃ ┣ category
┃ ┃ ┣ annotation
┃ ┃ ┗ validator
┃ ┃
┃ ┣ product
┃ ┃ ┣ annotation
┃ ┃ ┗ validator
┃ ┃
┃ ┣ role
┃ ┃ ┣ annotation
┃ ┃ ┗ validator
┃ ┃
┃ ┗ user
┃   ┣ annotation
┃   ┗ validator
┃
┗ web
  ┣ controller
  ┃ ┣ CategoryController.java
  ┃ ┣ ProductController.java
  ┃ ┗ UserController.java
  ┃
  ┣ exception
  ┃ ┣ enums
  ┃ ┣ ErrorType.java
  ┃ ┗ handler
  ┃   ┗ ControllerExceptionHandler.java
  ┃
  ┗ response
    ┣ FieldMessage.java
    ┣ ProblemDetails.java
    ┗ ValidationError.java
```

## 🛡️ Estrutura da Camada de Segurança

A camada de segurança foi projetada para centralizar autenticação, autorização, validação e proteção dos recursos da API, utilizando uma abordagem modular inspirada em arquiteturas enterprise modernas.

Cada package possui uma responsabilidade bem definida dentro do fluxo de autenticação e segurança da aplicação.

### 🔐 `security.config`

Responsável pelas configurações globais de segurança da aplicação.

### 📌 Responsabilidades

- Registro de beans de segurança
- Password Encoder
- Configurações compartilhadas
- Componentes reutilizáveis do Spring Security

### 📄 Classe principal

| Classe                | Responsabilidade                                       |
| --------------------- | ------------------------------------------------------ |
| `SecurityBeansConfig` | Configuração central de beans relacionados à segurança |

### 🎫 `security.oauth2.authorization`

Responsável pela configuração do `Authorization Server` da aplicação.

O **Authorization Server** é encarregado da autenticação dos usuários e emissão dos tokens `JWT`.

### 📌 Responsabilidades

- Configuração do OAuth2 Authorization Server
- Geração de JWT
- Assinatura de tokens
- Registro de clients OAuth2
- Configuração do fluxo de autenticação

### 📄 Classe principal

| Classe                      | Responsabilidade                                                        |
| --------------------------- | ----------------------------------------------------------------------- |
| `AuthorizationServerConfig` | Configuração do servidor OAuth2 responsável pela emissão dos tokens JWT |

### 🔑 security.oauth2.resource

Responsável pela configuração do `Resource Server`.

O **Resource Server** protege os endpoints da API e valida os `tokens JWT` recebidos nas requisições.

### 📌 Responsabilidades

- Proteção de endpoints REST
- Validação de JWT
- Configuração de autorização
- Controle de acesso por roles
- Configuração de CORS e CSRF

### 📄 Classe principal

| Classe                 | Responsabilidade                                            |
| ---------------------- | ----------------------------------------------------------- |
| `ResourceServerConfig` | Configuração de segurança da API e validação dos tokens JWT |

### 👤 security.oauth2.grant_password

Implementa o fluxo customizado de autenticação utilizando Password Grant.

Embora versões modernas do OAuth2 desencorajem esse fluxo em cenários públicos, ele foi utilizado neste projeto com fins educacionais e compreensão profunda do processo de autenticação.

### 📌 Responsabilidades

- Conversão das credenciais
- Processamento da autenticação
- Criação de tokens autenticados
- Integração com Spring Security

### 📄 Classes principais

| Classe                                  | Responsabilidade                                             |
| --------------------------------------- | ------------------------------------------------------------ |
| `CustomPasswordAuthenticationConverter` | Converte credenciais da requisição em objeto de autenticação |
| `CustomPasswordAuthenticationProvider`  | Processa autenticação do usuário                             |
| `CustomPasswordAuthenticationToken`     | Representa token autenticado durante o fluxo                 |

### 👥 security.userdetails

Responsável por centralizar informações relacionadas ao usuário autenticado dentro do contexto de segurança da aplicação.

### 📌 Responsabilidades

- Recuperar usuário autenticado
- Centralizar contexto de autenticação
- Facilitar acesso ao usuário logado

### 📄 Classe principal

| Classe              | Responsabilidade                                              |
| ------------------- | ------------------------------------------------------------- |
| `AuthenticatedUser` | Representação do usuário autenticado no contexto da aplicação |

## 🧾 Camada de Validação

A estrutura de validação foi organizada separando anotações customizadas e validadores específicos por domínio da aplicação.

Essa abordagem melhora:

- coesão;
- reutilização;
- clareza arquitetural;
- manutenção evolutiva.

### 📂 Estrutura

```text
validation
┣ category
┣ product
┣ role
┗ user
```

Cada domínio possui:
| Estrutura | Responsabilidade |
| ------------ | ------------------------------- |
| `annotation` | Define annotations customizadas |
| `validator` | Implementa regras de validação |

### 📌 Benefícios da abordagem

- Regras isoladas por domínio
- Fácil manutenção
- Reutilização de validações
- Maior legibilidade
- Melhor organização arquitetural

## 🌐 Camada Web

A camada web concentra os pontos de entrada da aplicação e o tratamento padronizado das respostas HTTP.

### 📂 `web.controller`

Responsável pelos endpoints REST da aplicação.

### 📌 Controllers implementados

| Controller           | Responsabilidade                               |
| -------------------- | ---------------------------------------------- |
| `CategoryController` | Endpoints de categorias                        |
| `ProductController`  | Endpoints de produtos                          |
| `UserController`     | Endpoints relacionados a usuários/autenticação |

### ⚠️ web.exception

Responsável pelo tratamento global de exceções da aplicação.

### 📌 Objetivos

- Padronizar respostas HTTP
- Centralizar tratamento de erros
- Melhorar rastreabilidade
- Facilitar integração frontend/backend

### 📄 Estruturas principais

| Estrutura                    | Responsabilidade                            |
| ---------------------------- | ------------------------------------------- |
| `ControllerExceptionHandler` | Intercepta exceções globalmente             |
| `ErrorType`                  | Enumeração de tipos de erro                 |
| `ProblemDetails`             | Estrutura padronizada da resposta           |
| `FieldMessage`               | Representa erros específicos de validação   |
| `ValidationError`            | Resposta específica para erros de validação |

---

## 🏛️ Estratégia Arquitetural Aplicada

A organização da camada de segurança segue princípios importantes de engenharia de software:

| Princípio                             | Aplicação                                                   |
| ------------------------------------- | ----------------------------------------------------------- |
| SRP (Single Responsibility Principle) | Cada package possui responsabilidade única                  |
| Desacoplamento                        | Separação clara entre autenticação, autorização e validação |
| Modularidade                          | Estrutura preparada para evolução                           |
| Escalabilidade                        | Facilidade para adicionar novos fluxos                      |
| Manutenibilidade                      | Código mais organizado e sustentável                        |

---

## 📊 Integração com Swagger/OpenAPI

A documentação da API foi integrada com autenticação JWT.

## 📌 Recursos implementados

- Autorização via Bearer Token
- Teste de endpoints protegidos
- Documentação automática
- Exploração segura da API

---

### 🔗 Acesso

```text
http://localhost:8080/swagger-ui.html
```

ou

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 🧪 Testes de Segurança

A aplicação evolui também em termos de testes automatizados de autenticação e autorização.

### 📌 Cenários testados

- Usuário autenticado
- Usuário não autenticado
- Usuário sem permissão
- Acesso por role
- Respostas HTTP de segurança
- Endpoints protegidos

---

### 📌 Ferramentas utilizadas

| Ferramenta           | Finalidade                |
| -------------------- | ------------------------- |
| MockMvc              | Teste de endpoints        |
| Spring Security Test | Simulação de autenticação |
| Mockito              | Mock de serviços          |
| JUnit 5              | Estrutura de testes       |

---

## 🧱 Boas Práticas Aplicadas

- JWT stateless authentication
- Externalização de secrets
- Segurança baseada em environment profiles
- Princípio do menor privilégio
- Separation of Concerns
- Centralização de exception handling
- DTO validation boundary

---

## 🚀 Evolução Arquitetural do Projeto

Com este capítulo, o projeto DSCatalog deixa de representar apenas uma API CRUD tradicional e passa a incorporar fundamentos essenciais de aplicações enterprise modernas:

- Segurança robusta
- APIs protegidas
- Controle de acesso profissional
- Autenticação moderna
- Validação consistente
- Estrutura preparada para produção

---

## 🚧 Principais Desafios Encontrados

Durante a implementação deste capítulo, diversos desafios técnicos relacionados à segurança e autenticação moderna foram enfrentados.

### 📌 Principais desafios

- Configuração do Spring Security 6
- Estruturação do OAuth2 Authorization Server
- Integração entre Authorization Server e Resource Server
- Geração e assinatura de JWT
- Implementação do fluxo Password Grant customizado
- Controle de acesso baseado em roles
- Configuração de CORS e CSRF
- Segurança stateless
- Integração do Swagger com autenticação JWT
- Estruturação desacoplada da camada de segurança
- Criação de validações customizadas integradas ao banco de dados

---

## 🧠 Aprendizados Consolidados

- Estruturação de autenticação e autorização com Spring Security 6
- Configuração de OAuth2 Authorization Server e Resource Server
- Implementação de autenticação stateless com JWT
- Aplicação de RBAC com Method Security
- Criação de validações customizadas com Bean Validation
- Padronização de respostas de erro com Exception Handling
- Organização modular da camada de segurança

---

## 🚧 Melhorias Futuras

- Refresh Token
- Revogação de tokens
- Login social (Google/GitHub)
- MFA (autenticação em dois fatores)
- Rate Limiting
- Auditoria de segurança
- Observabilidade com métricas
- Deploy seguro com HTTPS
- Integração com Keycloak

---

## 💼 Competências Demonstradas

Este capítulo consolida competências importantes relacionadas ao desenvolvimento backend moderno com Java e Spring Boot.

### 🔐 Segurança e autenticação

- Spring Security 6
- OAuth2
- JWT Authentication
- Authorization Server
- Resource Server
- Role-Based Access Control (RBAC)
- Method Security
- Stateless Authentication

---

### 🧾 Validação e tratamento de erros

- Bean Validation
- Hibernate Validator
- Validações customizadas
- Exception Handling
- Problem Details Pattern
- Validação integrada ao banco de dados

---

### 🏛️ Arquitetura backend

- Arquitetura em camadas
- DTO Pattern
- Separação de responsabilidades
- Modularização da segurança
- Organização enterprise de packages
- API REST segura

---

### 🧪 Testes e qualidade

- Spring Security Test
- MockMvc
- JUnit 5
- Mockito
- Testes de autorização
- Testes de autenticação

---

### ⚙️ Dev Practices

- Externalização de configurações
- Configuração por profiles
- Segurança baseada em ambiente
- Documentação técnica avançada
- Swagger/OpenAPI
- Estrutura preparada para produção

---

## 🎓 Conclusão

Este capítulo marca uma evolução significativa na maturidade arquitetural do projeto DSCatalog.

Mais do que apenas proteger endpoints, a aplicação passa a incorporar conceitos fundamentais utilizados em sistemas corporativos modernos:

- autenticação segura;
- autorização baseada em perfis;
- validação robusta;
- controle de acesso profissional;
- arquitetura preparada para produção.

A implementação de OAuth2, JWT e Spring Security consolida competências extremamente relevantes para o desenvolvimento backend moderno com Java e Spring Boot.

> [!IMPORTANT] Este capítulo representa um passo importante rumo à construção de APIs seguras, escaláveis e alinhadas às práticas utilizadas no mercado profissional.

---

## 📚 Referências Técnicas

### 🔹 Bean Validation

- https://beanvalidation.org/
- https://docs.jboss.org/hibernate/beanvalidation/spec/2.0/api/overview-summary.html
- https://www.baeldung.com/java-bean-validation-not-null-empty-blank
- https://www.baeldung.com/spring-custom-validation-message-source

---

### 🔹 Segurança e JWT

- https://jwt.io
- https://oauth.net/2/
- https://spring.io/projects/spring-security
- https://docs.spring.io/spring-security/reference/

---

### 🔹 Regex e validações

- https://regexr.com/
- https://regexlib.com/

---

## 👨‍💻 Autor

**Albert Silva de Jesus**  
Desenvolvedor Backend Java | Spring Boot

---

## 📎 Contato

[![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/albert-backend-java-spring-boot/)

[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:albertinesilva.17@gmail.com)
