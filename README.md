---
---

# 🛡️ Capítulo 03 — Validation & Security with Spring Boot (OAuth2 | JWT)

Resumo curto

- Objetivo: documentar de forma clara e concisa as decisões, localização e uso das funcionalidades de validação (Bean Validation) e segurança (OAuth2/JWT) implementadas no projeto DSCatalog.
- Público: desenvolvedores que irão manter, testar ou implantar a aplicação.

## ✅ Quick Start

Pré-requisitos: Java 17, Maven

Rodar em modo local (profile `dev` recomendado):

```bash
./mvnw -f backend spring-boot:run -Dspring-boot.run.profiles=dev
```

Executar testes:

```bash
./mvnw -f backend test
```

## **Visão Geral**

O capítulo integra duas frentes complementares:

- Validação: regras declarativas e customizadas com Bean Validation (Jakarta Validation + Hibernate Validator) e mensagens centralizadas em `src/main/resources/ValidationMessages.properties`.
- Segurança: arquitetura OAuth2 com Authorization Server e Resource Server; tokens JWT assinados; autenticação stateless; controle por roles e segurança em nível de método.

## **Estrutura relevante**

- `backend/src/main/java/com/albertsilva/dev/dscatalog/security` — configurações e componentes de segurança.
- `backend/src/main/java/com/albertsilva/dev/dscatalog/validation` — annotations e validators customizados por domínio (`user`, `role`, `product`, `category`).
- `backend/src/main/resources/ValidationMessages.properties` — mensagens de validação centralizadas.
- `backend/src/main/resources/application-*.properties` — propriedades por profile (CORS, secrets, etc.).

**Recomendações de leitura rápida:**

- Authorization Server: `security/oauth2/authorization/config/AuthorizationServerConfig.java`
- Resource Server: `security/oauth2/resource/config/ResourceServerConfig.java`
- Validators de senha/email: `validation/user/validator/StrongPasswordValidator.java`, `ValidEmailValidator.java`
- Validador de criação de usuário: `validation/user/validator/UserCreateValidator.java`

## **Validação (Bean Validation)**

Visão:

- Usa validations padrão (`@NotBlank`, `@Size`, etc.) e anotações customizadas (`@StrongPassword`, `@UniqueEmail`, `@ValidRoles`, `@UserCreateValid`).

Mensagens:

- Centralizadas em `ValidationMessages.properties`. Ex.:
  - `user.password.length` — senha mínima
  - `user.password.common` — senhas comuns bloqueadas
  - `user.email.invalid` / `user.email.unique`

Boas práticas aplicadas e faltantes (recomendado):

- Centralizar todas as mensagens no `ValidationMessages.properties` (evitar strings hardcoded nos validators).
- Padronizar chaves com prefixos por domínio: `user.*`, `product.*`, `category.*`.
- Criar testes unitários para cada validator customizado (cobertura de casos limite).
- Preparar arquivos `ValidationMessages_pt_BR.properties` e versões futuras para i18n.

Exemplo de uso em DTO (`UserCreateRequest`):

```java
@UserCreateValid
public record UserCreateRequest(
  @NotBlank(message = "{user.firstName.notBlank}") @Size(...)
  String firstName,
  @StrongPassword
  String password, ...)
```

### Tratamento de respostas de validação

O `ControllerExceptionHandler` formata respostas padronizadas com `ValidationError` e array `fieldErrors` no formato:

```json
{
  "status": 422,
  "path": "/api/v1/users",
  "fieldErrors": [{ "fieldName": "password", "message": "Senha inválida" }]
}
```

Isso facilita o consumo no frontend e testes automatizados.

## **Segurança (OAuth2 + JWT)**

Arquitetura implementada

- Authorization Server (em `security.oauth2.authorization`) — emite JWTs assinados (RSA) e registra clients.
- Resource Server (em `security.oauth2.resource`) — valida JWTs, extrai claims e authorities.
- Fluxo customizado: **Password Grant** com `CustomPasswordAuthenticationConverter` e `CustomPasswordAuthenticationProvider` (usado para fins didáticos).

Como obter token (exemplo — password grant)

```bash
curl -u "${CLIENT_ID}:${CLIENT_SECRET}" \
  -d "grant_type=password&username=alex@gmail.com&password=Secret123!" \
  http://localhost:8080/oauth2/token
```

Resposta esperada: JSON com `access_token` e `expires_in`. Para acessar recursos:

```http
Authorization: Bearer <access_token>
```

Observações importantes e recomendações de produção

- O fluxo ROPC (password grant) não é recomendado para clientes públicos; para apps web/mobile, prefira **Authorization Code + PKCE**.
- Não gere chaves RSA em memória para produção; utilize keystores (JKS/PKCS12) ou secret managers (Vault, Azure Key Vault) e configure via variáveis/secret store.
- `InMemoryOAuth2AuthorizationService` é adequado para dev/test — em produção persista autorizações/tokens (JDBC/DB) para revogação e replicação entre instâncias.
- Restrinja o H2 Console a ambientes de desenvolvimento (`spring.h2.console.enabled=false` em prod) e limite CORS a origins confiáveis.
- Forneça `security.client-secret` e demais segredos via variáveis de ambiente ou secret manager — não comite segredos no repositório.
- Habilite monitoramento/audit logging, rotacionamento de chaves, proteção contra brute-force e políticas de bloqueio de conta.

## **Configurações importantes**

- `security.client-id` — client id (ex.: via env `CLIENT_ID`)
- `security.client-secret` — client secret (ex.: via env `CLIENT_SECRET`)
- `security.jwt.duration` — duração do token (segundos)
- `cors.origins` — origins permitidos para CORS

Definir em `application-*.properties` por ambiente.

## **Testes e validação contínua**

- Adicionar testes unitários para: `StrongPasswordValidator`, `UserCreateValidator`, `ValidRolesValidator`, `UniqueEmailValidator`.
- Testes de integração de segurança: usar `spring-security-test` e `MockMvc` para validar fluxos de autenticação/autorização e endpoints protegidos.
- Testes de contrato: validar formato de respostas de erro para facilitar integração frontend.

## **Como contribuir / tarefas recomendadas**

1. Centralizar todas as mensagens de validação em `ValidationMessages.properties` (criar chaves faltantes como `user.password.containsPersonalData`).
2. Refatorar validators para usar chaves de mensagem em vez de strings literais.
3. Adicionar testes unitários para validators customizados.
4. Substituir `InMemoryOAuth2AuthorizationService` por persistência (JDBC) quando preparar deploy multi-instance.
5. Documentar processo de provisionamento de keystore/secret manager para produção.

## Referências e leitura adicional

- Bean Validation: https://beanvalidation.org/
- Spring Authorization Server: https://docs.spring.io/spring-authorization-server/
- OWASP: Authorization and Authentication best practices

---

Se quiser, eu faço o próximo passo: 1) refatoro validators para usar `ValidationMessages.properties`; 2) adiciono chaves faltantes no arquivo de mensagens; 3) crio testes unitários para os validators — escolha uma opção e eu inicio a implementação.

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
