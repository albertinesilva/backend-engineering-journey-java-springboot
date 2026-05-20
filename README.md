<h1 align="center">🛡️Capítulo 03 — Validação e Segurança com Spring Boot OAuth2 | JWT</h1>

<p align="center">
<em>Implementing secure REST APIs with Spring Security, OAuth2, JWT authentication, Bean Validation, and role-based access control in modern Spring Boot applications.</em>
</p>

<p align="center">

<img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk&logoColor=white" />

<img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />

<img src="https://img.shields.io/badge/Security-Spring_Security-red?style=for-the-badge" />

<img src="https://img.shields.io/badge/Auth-OAuth2%20%7C%20JWT-darkred?style=for-the-badge" />

<img src="https://img.shields.io/badge/Validation-Bean_Validation-blue?style=for-the-badge" />

<img src="https://img.shields.io/badge/Authorization-Role_Based_Access-success?style=for-the-badge" />

<img src="https://img.shields.io/badge/Architecture-Authorization_Server%20%7C%20Resource_Server-black?style=for-the-badge" />

<img src="https://img.shields.io/badge/API-RESTful-success?style=for-the-badge" />

<img src="https://img.shields.io/badge/Documentation-Swagger%20%7C%20OpenAPI-85EA2D?style=for-the-badge" />

<img src="https://img.shields.io/badge/Testing-Spring_Security_Test%20%7C%20MockMvc-yellow?style=for-the-badge" />

<img src="https://img.shields.io/badge/Database-PostgreSQL%20%7C%20H2-336791?style=for-the-badge&logo=postgresql&logoColor=white" />

<img src="https://img.shields.io/badge/Focus-Authentication%20%7C%20Authorization-informational?style=for-the-badge" />

<img src="https://img.shields.io/github/license/Albertinesilva/backend-engineering-journey-java-springboot?style=for-the-badge" />

<img src="https://img.shields.io/github/last-commit/Albertinesilva/backend-engineering-journey-java-springboot?style=for-the-badge" />

</p>

<p align="justify">
<em>
Este capítulo representa a evolução do projeto <strong>DSCatalog</strong> para um cenário mais próximo de aplicações corporativas reais, incorporando mecanismos robustos de <strong>validação de dados</strong>, <strong>autenticação</strong>, <strong>autorização</strong> e <strong>segurança de APIs REST</strong> utilizando <code>Spring Security</code>, <code>OAuth2</code>, <code>JWT</code> e <code>Bean Validation</code>.
</em>
</p>

<p align="justify">
<em>
Além da implementação de controle de acesso baseado em perfis (<code>ROLE_ADMIN</code>, <code>ROLE_OPERATOR</code>), este capítulo também introduz conceitos fundamentais de segurança backend moderna, como configuração de <strong>Authorization Server</strong>, <strong>Resource Server</strong>, geração e validação de tokens JWT, proteção de rotas, tratamento customizado de erros de validação e integração segura com documentação Swagger/OpenAPI.
</em>
</p>

---

# 📚 Contexto do Projeto

Após a consolidação da arquitetura em camadas e da estratégia de testes automatizados nos capítulos anteriores, o projeto evolui para uma camada essencial em aplicações modernas:

- 🔐 Segurança de APIs REST
- 🧾 Validação robusta de dados
- 👥 Controle de acesso por perfis
- 🎫 Autenticação baseada em token JWT
- 🛡️ Proteção de endpoints
- ⚙️ Configuração de OAuth2 Authorization Server
- 🔑 Resource Server com JWT
- 📄 Integração segura com Swagger/OpenAPI
- 🌐 Configuração de CORS e CSRF
- 🧪 Testes de segurança com Spring Security Test
- 📦 Estruturação profissional de autenticação/autorização

---

# 🎯 Objetivos do Capítulo

Este capítulo tem como objetivo transformar a API DSCatalog em uma aplicação backend preparada para cenários reais de autenticação e segurança corporativa.

---

## 🔹 1. Aplicar validação robusta com Bean Validation

Implementação de validações declarativas utilizando:

- `@NotBlank`
- `@NotNull`
- `@Size`
- `@Email`
- `@Positive`
- `@PastOrPresent`
- Validações customizadas
- Integração com banco de dados
- Mensagens personalizadas
- Tratamento global de erros de validação

### 📌 Benefícios

- Integridade dos dados
- Segurança contra entradas inválidas
- Contratos HTTP previsíveis
- Melhor experiência para consumidores da API
- Redução de inconsistências

---

## 🔹 2. Implementar autenticação moderna com OAuth2 e JWT

A autenticação da API foi construída utilizando:

- Spring Security 6
- OAuth2 Authorization Server
- Resource Server
- JWT (JSON Web Token)
- Password Encoder
- Controle de autenticação stateless

### 📌 Recursos implementados

- Geração de token JWT
- Assinatura segura de tokens
- Expiração configurável
- Login via OAuth2 Password Flow
- Registro de aplicação cliente
- Controle de acesso baseado em roles

---

## 🔹 3. Proteger endpoints da API

A API passou a possuir:

- Rotas públicas
- Rotas autenticadas
- Rotas restritas por perfil
- Segurança em nível de método
- Controle granular de autorização

### 📌 Anotações utilizadas

```java
@PreAuthorize("hasRole('ROLE_ADMIN')")

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
```

---

## 🔹 4. Aplicar boas práticas modernas de segurança

Foram implementadas configurações importantes para APIs REST modernas:

- Stateless Authentication
- Configuração de CORS
- Desabilitação controlada de CSRF
- Separação entre Authorization Server e Resource Server
- JWT assinado
- Proteção de documentação Swagger
- Segurança baseada em roles
- Configuração por perfis (`dev`, `test`, `prod`)

---

# 🧠 Conceitos Fundamentais Trabalhados

| Conceito | Objetivo |
|---|---|
| Authentication | Verificar identidade do usuário |
| Authorization | Verificar permissões do usuário |
| OAuth2 | Protocolo de autorização |
| JWT | Token seguro para autenticação stateless |
| Bean Validation | Validação declarativa de dados |
| Method Security | Proteção em nível de métodos |
| Resource Server | Proteção dos recursos da API |
| Authorization Server | Emissão e gerenciamento de tokens |

---

# 🛠️ Tecnologias Utilizadas

## 🔐 Segurança

| Tecnologia | Função |
|---|---|
| Spring Security | Framework principal de segurança |
| OAuth2 Authorization Server | Emissão de tokens JWT |
| OAuth2 Resource Server | Validação de tokens e proteção de recursos |
| JWT | Autenticação stateless baseada em token |
| BCryptPasswordEncoder | Criptografia segura de senhas |

---

## 🧾 Validação

| Tecnologia | Função |
|---|---|
| Bean Validation | Validação declarativa |
| Hibernate Validator | Implementação da especificação Bean Validation |
| Jakarta Validation | API padrão de validação |

---

## 📄 Documentação

| Tecnologia | Função |
|---|---|
| SpringDoc OpenAPI | Documentação automática |
| Swagger UI | Interface interativa da API |

---

## 🧪 Testes

| Tecnologia | Função |
|---|---|
| Spring Security Test | Testes de autenticação/autorização |
| MockMvc | Testes de endpoints protegidos |
| JUnit 5 | Testes automatizados |
| Mockito | Mocking de dependências |

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

## 🧪 Testes de Segurança

```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
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

---

## 🛡️ OAuth2 Resource Server

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```

---

# 👥 Modelo de Usuários e Perfis

O projeto passou a possuir um modelo de autenticação baseado em:

- Usuários
- Perfis (roles)
- Relacionamentos entre usuários e permissões

## 📌 Perfis utilizados

| Perfil | Responsabilidade |
|---|---|
| ROLE_ADMIN | Controle total da API |
| ROLE_OPERATOR | Operações intermediárias |

---

# 🔐 Fluxo de Autenticação

## 📌 Processo de Login

1. Cliente envia credenciais
2. Authorization Server autentica usuário
3. Token JWT é gerado
4. Cliente utiliza token nas próximas requisições
5. Resource Server valida token
6. API libera ou bloqueia acesso conforme roles

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

# 🎫 Estrutura JWT

Os tokens JWT utilizados possuem informações como:

- Usuário autenticado
- Authorities/Roles
- Tempo de expiração
- Issuer
- Audience

## 📌 Benefícios do JWT

- Stateless
- Escalável
- Seguro
- Amplamente utilizado no mercado
- Ideal para APIs REST

---

# ⚙️ Configurações da Aplicação

## 📌 Properties de Segurança

```properties
security.client-id=${CLIENT_ID:myclientid}
security.client-secret=${CLIENT_SECRET:myclientsecret}
security.jwt.duration=${JWT_DURATION:86400}
cors.origins=${CORS_ORIGINS:http://localhost:3000,http://localhost:5173}
```

---

# 🛡️ Authorization Server

O projeto implementa um Authorization Server responsável por:

- Autenticar usuários
- Gerar tokens JWT
- Assinar tokens
- Registrar aplicações clientes
- Gerenciar autenticação OAuth2

## 📌 Responsabilidades implementadas

- Habilitação do Authorization Server
- Configuração de assinatura JWT
- Configuração de Password Encoder
- Registro de client OAuth2
- Configuração de token JWT
- Definição de duração do token

---

# 🔐 Resource Server

O Resource Server é responsável por:

- Validar tokens JWT
- Controlar acesso aos endpoints
- Aplicar regras de autorização
- Proteger recursos da API

## 📌 Configurações aplicadas

- Controle de acesso por rota
- Configuração de CORS
- Configuração de CSRF
- Validação JWT
- Liberação do Swagger/OpenAPI
- Liberação do H2 Console em ambiente de teste

---

# 🌐 Segurança de Rotas

## 📌 Estratégia aplicada

| Tipo de rota | Acesso |
|---|---|
| Swagger/OpenAPI | Público |
| Categorias (GET) | Público |
| Produtos (GET) | Público |
| Demais endpoints | Autenticado |
| Endpoints administrativos | ROLE_ADMIN |

---

## 🔒 Exemplo de configuração

```java
.requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
.requestMatchers(DOCUMENTATION_OPENAPI).permitAll()
.anyRequest().authenticated()
```

---

# 🧾 Bean Validation

## 📌 Validações implementadas

### Exemplos

```java
@NotBlank(message = "Campo requerido")

@Email(message = "Email inválido")

@Size(min = 3, max = 80)

@Positive(message = "Valor deve ser positivo")
```

---

# 🔄 Tratamento Global de Validações

A aplicação possui um mecanismo centralizado para tratamento de exceções e erros de validação.

## 📌 Benefícios

- Padronização das respostas
- Melhor experiência para frontend
- Clareza de erros
- Rastreabilidade
- Facilidade de integração

---

## 📄 Exemplo de resposta de validação

```json
{
  "timestamp": "2026-05-20T10:15:30Z",
  "status": 422,
  "error": "Validation exception",
  "errors": [
    {
      "field": "email",
      "message": "Email inválido"
    }
  ],
  "path": "/api/v1/users"
}
```

---

# 📂 Estrutura da Camada de Segurança

```text
config
┣ SecurityConfig.java
┣ AuthorizationServerConfig.java
┣ ResourceServerConfig.java
┗ SpringDocOpenApiConfig.java

entities
┣ User.java
┣ Role.java
┗ TokenData.java

services
┣ UserService.java
┣ AuthService.java
┗ TokenService.java

web
┣ controller
┣ exceptions
┗ security
```

---

# 📊 Integração com Swagger/OpenAPI

A documentação da API foi integrada com autenticação JWT.

## 📌 Recursos

- Autorização via Bearer Token
- Teste de endpoints protegidos
- Documentação automática
- Exploração segura da API

---

## 🔗 Acesso

```text
http://localhost:8080/swagger-ui.html
```

ou

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🧪 Testes de Segurança

A aplicação também evolui em termos de testes automatizados de segurança.

## 📌 Cenários testados

- Usuário autenticado
- Usuário não autenticado
- Usuário sem permissão
- Acesso por role
- Respostas HTTP de segurança
- Endpoints protegidos

---

## 📌 Ferramentas utilizadas

| Ferramenta | Finalidade |
|---|---|
| MockMvc | Teste de endpoints |
| Spring Security Test | Simulação de autenticação |
| Mockito | Mock de serviços |
| JUnit 5 | Estrutura de testes |

---

# 🧱 Boas Práticas Aplicadas

- Separação clara entre autenticação e autorização
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

# 🔄 Fluxo Geral de Segurança

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

# 🚀 Evolução Arquitetural do Projeto

Com este capítulo, o projeto DSCatalog deixa de representar apenas uma API CRUD tradicional e passa a incorporar fundamentos essenciais de aplicações enterprise modernas:

- Segurança robusta
- APIs protegidas
- Controle de acesso profissional
- Autenticação moderna
- Validação consistente
- Estrutura preparada para produção

---

# 🧠 Aprendizados Consolidados

Durante este capítulo foram consolidados conhecimentos fundamentais sobre:

- Spring Security 6
- OAuth2
- JWT
- Authorization Server
- Resource Server
- Bean Validation
- Segurança de APIs REST
- Controle de acesso por perfil
- Configuração de filtros de segurança
- CORS e CSRF
- Method Security
- Tratamento de exceções de validação

---

# 📚 Referências Técnicas

## 🔹 Bean Validation

- https://beanvalidation.org/
- https://docs.jboss.org/hibernate/beanvalidation/spec/2.0/api/overview-summary.html
- https://www.baeldung.com/java-bean-validation-not-null-empty-blank
- https://www.baeldung.com/spring-custom-validation-message-source

---

## 🔹 Segurança e JWT

- https://jwt.io
- https://oauth.net/2/
- https://spring.io/projects/spring-security
- https://docs.spring.io/spring-security/reference/

---

## 🔹 Regex e validações

- https://regexr.com/
- https://regexlib.com/

---

# 🚧 Melhorias Futuras

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

# 🎓 Conclusão

Este capítulo marca uma evolução significativa na maturidade arquitetural do projeto DSCatalog.

Mais do que apenas proteger endpoints, a aplicação passa a incorporar conceitos fundamentais utilizados em sistemas corporativos modernos:

- autenticação segura;
- autorização baseada em perfis;
- validação robusta;
- segurança stateless;
- controle de acesso profissional;
- arquitetura preparada para produção.

A implementação de OAuth2, JWT e Spring Security consolida competências extremamente relevantes para o desenvolvimento backend moderno com Java e Spring Boot.

> 🚀 Este capítulo representa um passo importante rumo à construção de APIs seguras, escaláveis e alinhadas às práticas utilizadas no mercado profissional.

---

# 👨‍💻 Autor

**Albert Silva de Jesus**  
Desenvolvedor Backend Java | Spring Boot

---

# 📎 Contato

[![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/albert-backend-java-spring-boot/)

[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:albertinesilva.17@gmail.com)
