<h1 align="center">🏛️ Capítulo 04 — Domain Modeling, ORM, Business Use Cases & Data Access</h1>

<p align="justify">
<em>
This chapter focuses on designing a robust domain model, implementing real business use cases, optimizing database access strategies, and applying advanced JPA/Hibernate techniques to build enterprise-grade backend applications aligned with real-world business requirements.
</em>
</p>

<p align="center">

<img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk&logoColor=white" />

<img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />

<img src="https://img.shields.io/badge/Persistence-Spring_Data_JPA-success?style=for-the-badge" />

<img src="https://img.shields.io/badge/ORM-Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white" />

<img src="https://img.shields.io/badge/Database-PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white" />

<img src="https://img.shields.io/badge/Queries-JPQL%20%7C%20Native_SQL-blue?style=for-the-badge" />

<img src="https://img.shields.io/badge/Pagination-Spring_Data-informational?style=for-the-badge" />

<img src="https://img.shields.io/badge/Performance-N%2B1_Select-red?style=for-the-badge" />

<img src="https://img.shields.io/badge/Architecture-Domain_Driven_Design-purple?style=for-the-badge" />

<img src="https://img.shields.io/badge/Business_Logic-Use_Cases-critical?style=for-the-badge" />

<img src="https://img.shields.io/badge/Account_Management-Sign_Up%20%7C%20Password_Recovery-success?style=for-the-badge" />

<img src="https://img.shields.io/badge/Email-Spring_Mail-yellow?style=for-the-badge" />

<img src="https://img.shields.io/badge/Tokens-Activation%20%7C%20Recovery-orange?style=for-the-badge" />

<img src="https://img.shields.io/badge/Security-RBAC-red?style=for-the-badge" />

<img src="https://img.shields.io/badge/Authentication-OAuth2%20%7C%20JWT-black?style=for-the-badge" />

<img src="https://img.shields.io/badge/Documentation-Swagger%20%7C%20OpenAPI-85EA2D?style=for-the-badge" />

<img src="https://img.shields.io/github/license/Albertinesilva/backend-engineering-journey-java-springboot?style=for-the-badge" />

<img src="https://img.shields.io/github/last-commit/Albertinesilva/backend-engineering-journey-java-springboot?style=for-the-badge" />

</p>

<p align="justify">
<em>
Neste capítulo, o projeto <strong>DSCatalog</strong> evolui significativamente além dos cenários tradicionais de CRUD, incorporando fluxos de negócio completos encontrados em aplicações corporativas reais.

Foram implementados casos de uso relacionados ao ciclo de vida da conta do usuário, incluindo cadastro, ativação de conta, recuperação de senha, redefinição de credenciais e obtenção do usuário autenticado, utilizando uma arquitetura baseada em regras de negócio explícitas, entidades ricas e serviços especializados.

Além da evolução funcional, a camada de persistência foi aprimorada com consultas otimizadas utilizando <strong>Spring Data JPA</strong>, <strong>JPQL</strong>, consultas nativas, paginação, filtros dinâmicos e estratégias para eliminação do problema <strong>N+1 Select</strong>, garantindo melhor desempenho e escalabilidade.

O capítulo também introduz integração com serviços externos através do envio de e-mails transacionais, gerenciamento de tokens de negócio e aplicação de conceitos inspirados em <strong>Domain-Driven Design (DDD)</strong>, aproximando o projeto dos padrões encontrados em sistemas corporativos modernos.
</em>
</p>

---

# 📚 Contexto do Capítulo

Após a implementação da infraestrutura de autenticação e autorização utilizando Spring Security, OAuth2 e JWT, o projeto DSCatalog evolui para uma camada mais próxima dos requisitos encontrados em aplicações corporativas reais.
Neste capítulo foram implementados casos de uso completos relacionados ao ciclo de vida da conta do usuário, além da evolução da camada de persistência utilizando JPA/Hibernate, consultas otimizadas e integração com serviços de e-mail.
O foco principal foi construir fluxos de negócio completos, desacoplados e alinhados com boas práticas de arquitetura backend.

---

# 🎯 Objetivos do Capítulo

Este capítulo possui os seguintes objetivos:

- Evoluir a modelagem ORM da aplicação.
- Implementar casos de uso completos relacionados à gestão de contas de usuário.
- Aplicar conceitos de Domain-Driven Design na modelagem de negócio.
- Implementar mecanismos de ativação e recuperação de acesso.
- Resolver problemas de performance relacionados ao carregamento de entidades.
- Utilizar JPQL, consultas nativas e projeções para otimização de consultas.
- Implementar paginação e filtros dinâmicos.
- Integrar a aplicação com serviços de envio de e-mails transacionais.
- Centralizar regras de negócio em serviços e entidades quando apropriado.
- Melhorar a experiência de autenticação e gerenciamento de contas.
- Aplicar estratégias utilizadas em aplicações corporativas para escalabilidade, manutenção e segurança.

---

## 📂 Organização dos Packages

A estrutura da camada de segurança foi organizada seguindo princípios de separação de responsabilidades, modularidade e baixo acoplamento, permitindo maior clareza arquitetural, facilidade de manutenção e escalabilidade evolutiva.

O objetivo foi estruturar cada responsabilidade de segurança de forma isolada e coesa, aproximando a aplicação de arquiteturas desacopladas utilizadas em projetos modernos com Spring Security.

---

## 🖼️ Organização da Camada de Segurança

```text
📦 com.albertsilva.dev.dscatalog
┣ 📂 config
┃ ┗ 📄 SpringDocOpenApiConfig.java
┃
┣ 📂 dto
┃ ┣ 📂 category
┃ ┃ ┣ 📂 request
┃ ┃ ┃ ┣ 📄 CategoryCreateRequest.java
┃ ┃ ┃ ┗ 📄 CategoryUpdateRequest.java
┃ ┃ ┗ 📂 response
┃ ┃ ┃ ┗ 📄 CategoryResponse.java
┃ ┃
┃ ┣ 📂 product
┃ ┃ ┣ 📂 request
┃ ┃ ┃ ┣ 📄 ProductCreateRequest.java
┃ ┃ ┃ ┗ 📄 ProductUpdateRequest.java
┃ ┃ ┗ 📂 response
┃ ┃ ┃ ┣ 📄 ProductDetailsResponse.java
┃ ┃ ┃ ┗ 📄 ProductResponse.java
┃ ┃
┃ ┗ 📂 user
┃   ┣ 📂 request
┃   ┃ ┣ 📄 UserCreateRequest.java
┃   ┃ ┗ 📄 UserUpdateRequest.java
┃   ┗ 📂 response
┃     ┗ 📄 UserResponse.java
┃
┣ 📂 entity
┃ ┣ 📄 Category.java
┃ ┣ 📄 Product.java
┃ ┣ 📄 Role.java
┃ ┗ 📄 User.java
┃
┣ 📂 mapper
┃ ┣ 📂 category
┃ ┃ ┗ 📄 CategoryMapper.java
┃ ┣ 📂 product
┃ ┃ ┗ 📄 ProductMapper.java
┃ ┗ 📂 user
┃   ┗ 📄 UserMapper.java
┃
┣ 📂 repository
┃ ┣ 📄 CategoryRepository.java
┃ ┣ 📄 ProductRepository.java
┃ ┣ 📄 RoleRepository.java
┃ ┗ 📄 UserRepository.java
┃
┣ 📂 security
┃ ┣ 📂 config
┃ ┃ ┗ 📄 SecurityBeansConfig.java
┃ ┃
┃ ┣ 📂 oauth2
┃ ┃ ┣ 📂 authorization
┃ ┃ ┃ ┗ 📂 config
┃ ┃ ┃   ┗ 📄 AuthorizationServerConfig.java
┃ ┃ ┃
┃ ┃ ┣ 📂 grant_password
┃ ┃ ┃ ┣ 📄 CustomPasswordAuthenticationConverter.java
┃ ┃ ┃ ┣ 📄 CustomPasswordAuthenticationProvider.java
┃ ┃ ┃ ┗ 📄 CustomPasswordAuthenticationToken.java
┃ ┃ ┃
┃ ┃ ┗ 📂 resource
┃ ┃   ┗ 📄 ResourceServerConfig.java
┃ ┃
┃ ┗ 📂 userdetails
┃   ┗ 📄 AuthenticatedUser.java
┃
┣ 📂 service
┃ ┣ 📂 exceptions
┃ ┃ ┣ 📄 DatabaseException.java
┃ ┃ ┗ 📄 ResourceNotFoundException.java
┃ ┣ 📄 CategoryService.java
┃ ┣ 📄 ProductService.java
┃ ┗ 📄 UserService.java
┃
┣ 📂 validation
┃ ┣ 📂 category
┃ ┃ ┣ 📂 annotation
┃ ┃ ┗ 📂 validator
┃ ┃
┃ ┣ 📂 product
┃ ┃ ┣ 📂 annotation
┃ ┃ ┗ 📂 validator
┃ ┃
┃ ┣ 📂 role
┃ ┃ ┣ 📂 annotation
┃ ┃ ┗ 📂 validator
┃ ┃
┃ ┗ 📂 user
┃   ┣ 📂 annotation
┃   ┗ 📂 validator
┃
┣ 📂 web
┃ ┣ 📂 controller
┃ ┃ ┣ 📄 CategoryController.java
┃ ┃ ┣ 📄 ProductController.java
┃ ┃ ┗ 📄 UserController.java
┃ ┃
┃ ┗ 📂 exception
┃   ┣ 📂 enums
┃   ┃ ┗ 📄 ErrorType.java
┃   ┣ 📂 handler
┃   ┃ ┗ 📄 ControllerExceptionHandler.java
┃   ┗ 📂 response
┃     ┣ 📄 FieldMessage.java
┃     ┣ 📄 ProblemDetails.java
┃     ┗ 📄 ValidationError.java
┃
┣ 📄 DscatalogApplication.java
┃
┗ 📂 resources
  ┣ 📂 db
  ┃ ┣ 📂 data
  ┃ ┣ 📂 migration
  ┃ ┗ 📂 schema
  ┣ 📂 static
  ┣ 📂 templates
  ┣ 📄 application-dev.properties
  ┣ 📄 application-prod.properties
  ┣ 📄 application-test.properties
  ┣ 📄 application.properties
  ┣ 📄 ValidationMessages.properties
  ┣ 📄 banner-dev.txt
  ┗ 📄 import.sql
```

---

## 👨‍💻 Autor

**Albert Silva de Jesus**  
Desenvolvedor Backend Java | Spring Boot

---
## 📎 Contato

[![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/albert-backend-java-spring-boot/)
[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:albertinesilva.17@gmail.com)
