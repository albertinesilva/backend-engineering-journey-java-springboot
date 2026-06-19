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

## 📚 Contexto do Capítulo

Após a implementação da infraestrutura de autenticação e autorização utilizando Spring Security, OAuth2 e JWT, o projeto DSCatalog evolui para uma camada mais próxima dos requisitos encontrados em aplicações corporativas reais.
Neste capítulo foram implementados casos de uso completos relacionados ao ciclo de vida da conta do usuário, além da evolução da camada de persistência utilizando JPA/Hibernate, consultas otimizadas e integração com serviços de e-mail.
O foco principal foi construir fluxos de negócio completos, desacoplados e alinhados com boas práticas de arquitetura backend.

---

## 🎯 Objetivos do Capítulo

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

A evolução do DSCatalog exigiu uma reorganização estrutural da aplicação para suportar novos requisitos de negócio, mecanismos de segurança, integrações externas e estratégias avançadas de persistência.

A arquitetura foi organizada seguindo princípios de separação de responsabilidades, alta coesão e baixo acoplamento, permitindo que cada módulo possua uma responsabilidade clara dentro do sistema.

Além das tradicionais camadas de persistência e exposição de APIs REST, foram incorporados componentes especializados para autenticação OAuth2, gerenciamento de tokens de negócio, recuperação de acesso, envio de e-mails transacionais, validações customizadas, projeções para consultas otimizadas e implementação de casos de uso completos.

Essa organização facilita a manutenção, evolução e testabilidade da aplicação, aproximando o projeto de arquiteturas encontradas em sistemas corporativos modernos.

---

## 🏛️ Estrutura Arquitetural da Aplicação

A estrutura abaixo apresenta a organização completa dos principais módulos do backend, evidenciando a separação entre domínio, aplicação, infraestrutura, segurança e exposição dos recursos REST.

Cada package possui uma responsabilidade específica dentro da arquitetura, reduzindo dependências indevidas e favorecendo a evolução independente dos componentes.

```text
📦 com.albertsilva.dev.dscatalog
┃
┣ 📄 DscatalogApplication.java
┃
┣ 📂 config
┃ ┗ 📄 SpringDocOpenApiConfig.java
┃
┣ 📂 domain
┃ ┣ 📄 Identifiable.java
┃ ┃
┃ ┣ 📂 catalog
┃ ┃ ┣ 📄 Category.java
┃ ┃ ┗ 📄 Product.java
┃ ┃
┃ ┣ 📂 recovery
┃ ┃ ┣ 📄 Email.java
┃ ┃ ┣ 📄 Token.java
┃ ┃ ┃
┃ ┃ ┗ 📂 enums
┃ ┃   ┣ 📄 EmailStatus.java
┃ ┃   ┗ 📄 TokenType.java
┃ ┃
┃ ┗ 📂 user
┃   ┣ 📄 Role.java
┃   ┗ 📄 User.java
┃
┣ 📂 dto
┃ ┣ 📂 category
┃ ┃ ┣ 📂 request
┃ ┃ ┃ ┣ 📄 CategoryCreateRequest.java
┃ ┃ ┃ ┗ 📄 CategoryUpdateRequest.java
┃ ┃ ┃
┃ ┃ ┗ 📂 response
┃ ┃   ┣ 📄 CategoryDetailsResponse.java
┃ ┃   ┗ 📄 CategoryResponse.java
┃ ┃
┃ ┣ 📂 email
┃ ┃ ┗ 📂 request
┃ ┃   ┗ 📄 EmailRegisterRequest.java
┃ ┃
┃ ┣ 📂 product
┃ ┃ ┣ 📂 request
┃ ┃ ┃ ┣ 📄 ProductCreateRequest.java
┃ ┃ ┃ ┗ 📄 ProductUpdateRequest.java
┃ ┃ ┃
┃ ┃ ┗ 📂 response
┃ ┃   ┣ 📄 ProductDetailsResponse.java
┃ ┃   ┗ 📄 ProductResponse.java
┃ ┃
┃ ┣ 📂 role
┃ ┃ ┗ 📂 response
┃ ┃   ┗ 📄 RoleResponse.java
┃ ┃
┃ ┗ 📂 user
┃   ┣ 📂 request
┃   ┃ ┣ 📄 PasswordResetRequest.java
┃   ┃ ┣ 📄 UserCreateRequest.java
┃   ┃ ┣ 📄 UserEmailRequest.java
┃   ┃ ┣ 📄 UserRegisterRequest.java
┃   ┃ ┗ 📄 UserUpdateRequest.java
┃   ┃
┃   ┗ 📂 response
┃     ┣ 📄 UserDetailsResponse.java
┃     ┗ 📄 UserResponse.java
┃
┣ 📂 mapper
┃ ┣ 📂 category
┃ ┃ ┗ 📄 CategoryMapper.java
┃ ┣ 📂 product
┃ ┃ ┗ 📄 ProductMapper.java
┃ ┗ 📂 user
┃   ┗ 📄 UserMapper.java
┃
┣ 📂 projection
┃ ┣ 📄 ProductProjection.java
┃ ┗ 📄 UserDetailsProjection.java
┃
┣ 📂 repository
┃ ┣ 📄 CategoryRepository.java
┃ ┣ 📄 EmailRepository.java
┃ ┣ 📄 ProductRepository.java
┃ ┣ 📄 RoleRepository.java
┃ ┣ 📄 TokenRepository.java
┃ ┗ 📄 UserRepository.java
┃
┣ 📂 security
┃ ┣ 📂 auth
┃ ┃ ┗ 📄 AuthenticatedUserService.java
┃ ┃
┃ ┣ 📂 config
┃ ┃ ┗ 📄 SecurityBeansConfig.java
┃ ┃
┃ ┣ 📂 oauth2
┃ ┃ ┣ 📂 authorization
┃ ┃ ┃ ┗ 📂 config
┃ ┃ ┃   ┗ 📄 AuthorizationServerConfig.java
┃ ┃ ┃
┃ ┃ ┣ 📂 grant
┃ ┃ ┃ ┗ 📂 password
┃ ┃ ┃   ┣ 📄 CustomPasswordAuthenticationConverter.java
┃ ┃ ┃   ┣ 📄 CustomPasswordAuthenticationProvider.java
┃ ┃ ┃   ┗ 📄 CustomPasswordAuthenticationToken.java
┃ ┃ ┃
┃ ┃ ┗ 📂 resource
┃ ┃   ┗ 📂 config
┃ ┃     ┗ 📄 ResourceServerConfig.java
┃ ┃
┃ ┗ 📂 userdetails
┃   ┗ 📄 AuthenticatedUser.java
┃
┣ 📂 service
┃ ┣ 📄 AccountService.java
┃ ┣ 📄 CategoryService.java
┃ ┣ 📄 EmailService.java
┃ ┣ 📄 ProductService.java
┃ ┣ 📄 TokenService.java
┃ ┣ 📄 UserService.java
┃ ┃
┃ ┗ 📂 exception
┃   ┣ 📄 AuthenticatedUserNotFoundException.java
┃   ┣ 📄 DatabaseException.java
┃   ┣ 📄 InvalidTokenException.java
┃   ┗ 📄 ResourceNotFoundException.java
┃
┣ 📂 util
┃ ┗ 📄 IdentifiableUtils.java
┃
┣ 📂 validation
┃ ┣ 📂 category
┃ ┃ ┣ 📂 annotation
┃ ┃ ┃ ┣ 📄 CategoryCreateValid.java
┃ ┃ ┃ ┗ 📄 CategoryUpdateValid.java
┃ ┃ ┃
┃ ┃ ┗ 📂 validator
┃ ┃   ┣ 📄 CategoryCreateValidator.java
┃ ┃   ┗ 📄 CategoryUpdateValidator.java
┃ ┃
┃ ┣ 📂 product
┃ ┃ ┣ 📂 annotation
┃ ┃ ┃ ┣ 📄 ProductCreateValid.java
┃ ┃ ┃ ┗ 📄 ProductUpdateValid.java
┃ ┃ ┃
┃ ┃ ┗ 📂 validator
┃ ┃   ┣ 📄 ProductCreateValidator.java
┃ ┃   ┗ 📄 ProductUpdateValidator.java
┃ ┃
┃ ┣ 📂 role
┃ ┃ ┣ 📂 annotation
┃ ┃ ┃ ┗ 📄 ValidRoles.java
┃ ┃ ┃
┃ ┃ ┗ 📂 validator
┃ ┃   ┗ 📄 ValidRolesValidator.java
┃ ┃
┃ ┗ 📂 user
┃   ┣ 📂 annotation
┃   ┃ ┣ 📄 StrongPassword.java
┃   ┃ ┣ 📄 UniqueEmail.java
┃   ┃ ┣ 📄 UserCreateValid.java
┃   ┃ ┣ 📄 UserUpdateValid.java
┃   ┃ ┗ 📄 ValidEmail.java
┃   ┃
┃   ┗ 📂 validator
┃     ┣ 📄 StrongPasswordValidator.java
┃     ┣ 📄 UniqueEmailValidator.java
┃     ┣ 📄 UserCreateValidator.java
┃     ┣ 📄 UserUpdateValidator.java
┃     ┗ 📄 ValidEmailValidator.java
┃
┣ 📂 web
┃ ┣ 📂 controller
┃ ┃ ┣ 📄 AccountController.java
┃ ┃ ┣ 📄 CategoryController.java
┃ ┃ ┣ 📄 ProductController.java
┃ ┃ ┗ 📄 UserController.java
┃ ┃
┃ ┗ 📂 exception
┃   ┣ 📂 enums
┃   ┃ ┗ 📄 ErrorType.java
┃   ┃
┃   ┣ 📂 handler
┃   ┃ ┗ 📄 ControllerExceptionHandler.java
┃   ┃
┃   ┗ 📂 response
┃     ┣ 📄 FieldMessage.java
┃     ┣ 📄 ProblemDetails.java
┃     ┗ 📄 ValidationError.java
┃
┗ 📂 resources
  ┣ 📂 db
  ┃ ┗ 📂 migration
  ┃   ┣ 📂 data
  ┃   ┃ ┣ 📄 V100__insert_categories.sql
  ┃   ┃ ┣ 📄 V101__insert_products.sql
  ┃   ┃ ┣ 📄 V102__insert_product_category.sql
  ┃   ┃ ┣ 📄 V103__insert_user.sql
  ┃   ┃ ┣ 📄 V104__insert_role.sql
  ┃   ┃ ┗ 📄 V105__insert_user_role.sql
  ┃   ┃
  ┃   ┗ 📂 schema
  ┃     ┣ 📄 V001__create_table_category.sql
  ┃     ┣ 📄 V002__create_table_product.sql
  ┃     ┣ 📄 V003__create_table_product_category.sql
  ┃     ┣ 📄 V004__alter_table_product_category.sql
  ┃     ┣ 📄 V005__create_table_role.sql
  ┃     ┣ 📄 V006__create_table_user.sql
  ┃     ┣ 📄 V007__create_table_user_role.sql
  ┃     ┣ 📄 V008__alter_table_user_role.sql
  ┃     ┣ 📄 V009__create_table_token.sql
  ┃     ┣ 📄 V010__alter_table_token.sql
  ┃     ┗ 📄 V011__create_table_email.sql
  ┃
  ┣ 📂 META-INF
  ┃ ┗ 📄 additional-spring-configuration-metadata.json
  ┃
  ┣ 📂 static
  ┃ ┗ 📂 image
  ┃
  ┣ 📂 templates
  ┃ ┣ 📄 activate_user_by_email_template.html
  ┃ ┣ 📄 reactivate_user_by_email_template.html
  ┃ ┗ 📄 reset_password_email_template.html
  ┃
  ┣ 📄 application.properties
  ┣ 📄 application-dev.properties
  ┣ 📄 application-test.properties
  ┣ 📄 application-prod.properties
  ┣ 📄 ValidationMessages.properties
  ┣ 📄 banner-dev.txt
  ┗ 📄 import.sql
```

### Principais Responsabilidades

| Package      | Responsabilidade                                     |
| ------------ | ---------------------------------------------------- |
| `config`     | Configurações globais da aplicação                   |
| `domain`     | Objetos centrais do domínio e regras de negócio      |
| `dto`        | Contratos de entrada e saída da API                  |
| `mapper`     | Conversão entre entidades e DTOs                     |
| `projection` | Projeções utilizadas para consultas otimizadas       |
| `repository` | Acesso e persistência de dados                       |
| `security`   | Autenticação, autorização e infraestrutura OAuth2    |
| `service`    | Implementação dos casos de uso da aplicação          |
| `validation` | Validações customizadas e regras de consistência     |
| `web`        | Exposição dos endpoints REST                         |
| `resources`  | Configurações, templates e scripts de banco de dados |

<p>A adoção dessa estrutura permite que novas funcionalidades sejam adicionadas sem impactar diretamente os demais módulos, favorecendo escalabilidade e manutenibilidade a longo prazo.</p>

---
## 🏛️ Organização da Camada de Domínio

Uma das principais evoluções arquiteturais deste capítulo foi a transformação da antiga camada baseada apenas em entidades persistentes para uma camada efetivamente orientada ao domínio.

Nas primeiras versões do projeto, as classes eram organizadas em um package denominado `entity`, refletindo principalmente sua função de mapeamento para o banco de dados. Com o crescimento da aplicação e o surgimento de novos requisitos de negócio, essa abordagem passou a limitar a expressividade do modelo.

Para representar de forma mais adequada os conceitos centrais do sistema, o package foi evoluído para `domain`, refletindo uma visão mais próxima das práticas adotadas em arquiteturas orientadas ao domínio.

Essa mudança vai além de uma simples alteração de nomenclatura. Ela representa uma mudança de perspectiva: os objetos passaram a ser vistos como elementos do negócio e não apenas como registros persistidos no banco de dados.

### Estrutura Atual do Domínio

```text
📂 domain
┃
┣ 📄 Identifiable.java
┃
┣ 📂 catalog
┃ ┣ 📄 Category.java
┃ ┗ 📄 Product.java
┃
┣ 📂 user
┃ ┣ 📄 User.java
┃ ┗ 📄 Role.java
┃
┗ 📂 recovery
  ┣ 📄 Token.java
  ┣ 📄 Email.java
  ┗ 📂 enums
    ┣ 📄 TokenType.java
    ┗ 📄 EmailStatus.java
```

### Módulo Catalog
Responsável pelos conceitos relacionados ao catálogo de produtos da aplicação.

```package
catalog
├── Product
└── Category
```
Contém as entidades responsáveis pela representação dos produtos comercializados e suas respectivas categorias, além dos relacionamentos necessários para consultas e regras de negócio do catálogo.

### Módulo User

Centraliza os conceitos relacionados à identidade e autorização dos usuários.

```text
user
├── User
└── Role
```

Esse módulo é responsável por representar usuários autenticados, perfis de acesso e relacionamentos utilizados pelos mecanismos de autorização implementados com Spring Security.

### Módulo Recovery

Introduzido para suportar fluxos de negócio relacionados ao gerenciamento de acesso e comunicação com usuários.

```text
recovery
├── Token
├── Email
├── TokenType
└── EmailStatus
```

Esse módulo concentra os conceitos utilizados nos processos de:

- Ativação de conta
- Reenvio de ativação
- Recuperação de senha
- Redefinição de senha
- Controle de envio de e-mails transacionais

A criação desse módulo permitiu encapsular responsabilidades específicas de recuperação de acesso sem sobrecarregar as entidades relacionadas aos usuários.

## 👨‍💻 Autor

**Albert Silva de Jesus**  
Desenvolvedor Backend Java | Spring Boot

---
## 📎 Contato

[![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/albert-backend-java-spring-boot/)
[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:albertinesilva.17@gmail.com)
