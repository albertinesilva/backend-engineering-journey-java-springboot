# 🤝 Contribuindo para o Projeto

Antes de tudo, obrigado pelo interesse em contribuir com este repositório 🚀

Este projeto foi criado como parte de uma jornada prática de aprendizado em desenvolvimento backend com Java e Spring Boot, documentando evolução técnica, boas práticas e implementação progressiva de aplicações reais.

A proposta não é apenas compartilhar código, mas também incentivar aprendizado colaborativo, troca de conhecimento e construção de um ambiente saudável para desenvolvedores em evolução.

---

# 🎯 Objetivo das Contribuições

As contribuições devem ajudar a:

- Melhorar qualidade de código
- Evoluir arquitetura e organização
- Corrigir bugs
- Aprimorar documentação
- Compartilhar boas práticas
- Tornar os projetos mais didáticos
- Simular padrões utilizados no mercado

---

# 📚 Estrutura do Projeto

O repositório está organizado por capítulos/branches, representando a evolução incremental da jornada:

| Branch | Tema |
|---|---|
| `chapter-01-crud` | CRUD + Arquitetura em Camadas |
| `chapter-02-tests` | Testes Automatizados |
| `chapter-03-validation-security` | Validação e Segurança |
| `chapter-04-domain` | Modelagem de Domínio |
| `chapter-05-queries` | Consultas e Performance |
| `chapter-06-docker` | Docker, Deploy e CI/CD |

A branch `main` funciona como hub central de documentação e navegação.

---

# 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Spring Security
- JWT / OAuth2
- PostgreSQL
- H2 Database
- JUnit 5
- Mockito
- MockMvc
- Maven
- Docker

---

# 🚀 Como Contribuir

## 1. Faça um Fork

Crie um fork deste repositório para sua conta GitHub.

---

## 2. Clone o Projeto

```bash
git clone https://github.com/SEU-USUARIO/jornada-java-springboot-bootcamp.git
```

---

## 3. Crie uma Branch

Utilize nomes claros e descritivos:

```bash
git checkout -b feature/nome-da-feature
```

Exemplos:

```bash
feature/improve-validation
fix/category-endpoint
docs/update-readme
test/product-service-tests
```

---

## 4. Realize as Alterações

Boas práticas esperadas:

- Código limpo e organizado
- Responsabilidade única
- Nomenclatura clara
- Baixo acoplamento
- Coesão entre camadas
- Seguir arquitetura existente

---

# 🧪 Testes

Sempre que possível:

- Adicione testes automatizados
- Garanta que os testes existentes continuem funcionando
- Evite quebrar contratos da API

Executar testes:

```bash
./mvnw test
```

---

# 📌 Padrões Utilizados

## Arquitetura

O projeto segue arquitetura em camadas:

```text
Controller → Service → Repository
```

---

## DTOs

- Evitar exposição direta de entidades
- Utilizar DTOs para requests/responses
- Manter separação clara entre domínio e API

---

## Tratamento de Exceções

- Utilizar exceções customizadas
- Padronizar respostas HTTP
- Centralizar tratamento no Exception Handler

---

## Commits

Prefira commits pequenos e objetivos.

Exemplos:

```text
feat: adiciona validação de categoria
fix: corrige paginação de produtos
test: adiciona testes do ProductService
docs: atualiza README do capítulo 02
```

---

# 📥 Pull Requests

Antes de abrir um Pull Request:

- Verifique se o projeto compila
- Execute os testes
- Revise o código
- Atualize documentação se necessário

---

# 💬 Discussões e Sugestões

Sugestões de melhoria são muito bem-vindas:

- Organização de projeto
- Arquitetura
- Testes
- Performance
- Segurança
- Documentação
- DevOps
- Observabilidade

---

# 🎓 Filosofia do Projeto

Este repositório prioriza:

- Aprendizado contínuo
- Evolução incremental
- Clareza arquitetural
- Fundamentos sólidos
- Qualidade de código
- Engenharia de software aplicada

Mais importante do que apenas “fazer funcionar” é entender profundamente o que está sendo construído.

---

# 🚀 Obrigado por contribuir

Toda contribuição — seja código, documentação, correção ou sugestão — ajuda a fortalecer este projeto e a jornada de aprendizado compartilhada aqui.
