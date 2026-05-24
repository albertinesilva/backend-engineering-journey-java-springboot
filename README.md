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

Após a consolidação da arquitetura em camadas e da estratégia de testes automatizados nos capítulos anteriores, o projeto evolui para uma nova etapa focada em segurança, autenticação e validação robusta.

Nesta fase, a aplicação passa a incorporar:

- 🔐 Segurança de APIs REST
- 🎫 Autenticação baseada em JWT
- 👥 Controle de acesso por roles
- 🛡️ Proteção de endpoints
- ⚙️ OAuth2 Authorization Server
- 🔑 OAuth2 Resource Server
- 🧾 Bean Validation
- 🌐 Configuração de CORS e CSRF
- 📄 Integração segura com Swagger/OpenAPI
- 🧪 Testes de segurança
- 📦 Estrutura profissional de autenticação/autorização

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

## 🔹 2. Implementar autenticação moderna com OAuth2 e JWT

A autenticação da aplicação foi construída utilizando uma arquitetura baseada em:

- Spring Security 6
- OAuth2 Authorization Server
- OAuth2 Resource Server
- JWT (JSON Web Token)
- BCrypt Password Encoder
- Controle de autenticação Stateless Authentication

### 📌 Recursos implementados

- Geração segura de tokens JWT
- Assinatura de tokens
- Expiração configurável
- Login via OAuth2 Password Flow
- Registro de aplicações clientes
- Controle de acesso baseado em roles

---

## 🔹 3. Proteger endpoints da API

A API passou a possuir controle de acesso com autenticação e autorização baseada em perfis.

- Rotas públicas
- Rotas autenticadas
- Rotas restritas por perfil
- Segurança em nível de método
- Controle granular de autorização

## 📌 Estratégia aplicada

| Tipo de rota               | Acesso              |
| -------------------------- | ------------------- |
| Swagger/OpenAPI            | Público             |
| GET de produtos/categorias | Público             |
| Endpoints autenticados     | Usuário autenticado |
| Endpoints administrativos  | `ROLE_ADMIN`        |

### 📌 Segurança em nível de método, Anotações utilizadas

```java
@PreAuthorize("hasRole('ROLE_ADMIN')")

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
```

---

## 🔹 4. Aplicar boas práticas modernas de segurança

Foram implementadas estratégias utilizadas em APIs REST modernas e ambientes corporativos.

### 📌 Práticas aplicadas

- Stateless Authentication
- JWT assinado
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

# 🛠️ Tecnologias Utilizadas

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

### 🛡️ OAuth2 Resource Server

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

### 📌 Benefícios do JWT

- Stateless
- Escalável
- Seguro
- Amplamente utilizado no mercado
- Ideal para APIs REST

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

- Validar tokens JWT
- Controlar acesso aos endpoints
- Aplicar regras de autorização
- Proteger recursos da API

### 📌 Configurações aplicadas

- Controle de acesso por rota
- Configuração de CORS
- Configuração de CSRF
- Validação JWT
- Liberação do Swagger/OpenAPI
- Liberação do H2 Console em ambiente de teste

---

## 🌐 Segurança de Rotas

### 📌 Estratégia aplicada

| Tipo de rota              | Acesso      |
| ------------------------- | ----------- |
| Swagger/OpenAPI           | Público     |
| Categorias (GET)          | Público     |
| Produtos (GET)            | Público     |
| Demais endpoints          | Autenticado |
| Endpoints administrativos | ROLE_ADMIN  |

---

## 🔒 Exemplo de configuração

```java
.requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
.requestMatchers(DOCUMENTATION_OPENAPI).permitAll()
.anyRequest().authenticated()
```

---

## 🧾 Bean Validation

### 📌 Validações implementadas

### Exemplos

```java
@NotBlank(message = "Campo requerido")

@Email(message = "Email inválido")

@Size(min = 3, max = 80)

@Positive(message = "Valor deve ser positivo")
```

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
┣ routes
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
    ┗ ProblemDetails.java
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

### 👥 security.routes

Responsável por armazenar informações relacionadas ao usuário autenticado.

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

- Separação entre autenticação e autorização
- Uso de JWT stateless
- Configuração baseada em perfis
- Segurança em nível de método
- Tratamento global de exceções
- DTOs desacoplados das entidades
- Proteção da documentação Swagger
- Controle de acesso centralizado
- Password encoding seguro
- Configuração externalizada

---

## 🔄 Fluxo Geral de Segurança

```text
Cliente
   ↓
Authorization Server
   ↓
Geração JWT
   ↓
Cliente recebe token
   ↓
Requisição com Bearer Token
   ↓
Resource Server valida JWT
   ↓
Spring Security verifica roles
   ↓
Controller protegido
```

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

## 🧠 Aprendizados Consolidados

Durante este capítulo foram consolidados conhecimentos fundamentais sobre:

- Spring Security 6
- OAuth2
- JWT
- Authorization Server
- Resource Server
- Bean Validation
- Segurança de APIs REST
- Controle de acesso por roles
- Configuração de filtros de segurança
- CORS e CSRF
- Method Security
- Tratamento de exceções de validação

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

## 🎓 Conclusão

Este capítulo marca uma evolução significativa na maturidade arquitetural do projeto DSCatalog.

Mais do que apenas proteger endpoints, a aplicação passa a incorporar conceitos fundamentais utilizados em sistemas corporativos modernos:

- autenticação segura;
- autorização baseada em perfis;
- validação robusta;
- segurança stateless;
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
