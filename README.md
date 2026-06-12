<h1 align="center">📨 Capítulo 04 — Account Management, ORM & Business Use Cases</h1>

<p align="justify">
<em>..............</em>
</p>

<p align="center">

Badges

</p>

<p align="justify">
<em>
............
</em>

</p>

---

# 📚 Contexto do Capítulo

Após a implementação da infraestrutura de autenticação e autorização utilizando Spring Security, OAuth2 e JWT, o projeto DSCatalog evolui para uma camada mais próxima dos requisitos encontrados em aplicações corporativas reais.
Neste capítulo foram implementados casos de uso completos relacionados ao ciclo de vida da conta do usuário, além da evolução da camada de persistência utilizando JPA/Hibernate, consultas otimizadas e integração com serviços de e-mail.
O foco principal foi construir fluxos de negócio completos, desacoplados e alinhados com boas práticas de arquitetura backend.

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
