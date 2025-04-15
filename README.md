# Sistema de Votação de Pautas

## Funcionalidades
1. Cadastro de uma nova paura
2. Abertura de sessão para uma pauta cadastrada
3. Votação em uma pauta com sessão aberta

## Pré-requisitos
 - Java 21
 - Maven 3.9.5
 - Docker (para o banco de dados)

## Rodando via Docker Compose

```bash
docker-compose up --build
```

A aplicação será acessível via:
- API `http://localhost:8080`
- PostgreSQL: `localhost:5432`

## Rodando localmente
1. Suba um banco de PostgreSQL dom docker
```bash
docker run --name votacao-db -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=votacaodb -p 5432:5432 -d postgres
``` 
2. Faça o clone do projeto em: 
   - https://github.com/JadersonCardoso/desafio-votacao.git 
3. Dentro da pasta do projeto Compile e Execute
```bash
mvn spring-boot:run
``` 
## Decisões de Projeto e Tecnologias Utilizadas

### Arquitetura
A aplicação foi desenvolvida utilizando **DDD(Domain-Driven Design)** com foco na separação das responsabilidades por domínio de negócio.

### Tecnologias Utilizadas
- **Spring Boot**: estrutura principal da aplciação, facilitando configuração e produtividade.
- **Lombok**: reduz boilerplate em classes de modelo e DTOs (getters, setters, construtores etc).
- **MapStruct**: para mapeamento eficiente e seguro entre entidades e DTOs.
- **Feign Client**: para abstratção da integração baseadas em REST.
- **Rest-Assured**: para testes de integração baseados em REST.
- **Testcontainers**: utilizado em testes para simular o ambiente real com banco PostgreSQL em containers.
- **JaCoCo**: análise de cobertura de testes

### Outras Escolhas
- Uso de `docker-compose` para facilitar a execução do ambiente de testes com persistência real em PostgreSQL.

## Documentação da API
A aplicação utliza **OpenAPI (Swagger)** para documentar e testar os endpoints de forma interativa.

A interface Swagger está disponível após o start da aplicação no seguinte endereço:

**[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)**

Você pode utlizar essa interface para:
- Visualiazar todos os endpoints disponíveis (como cadastrar uma pauta, abrir uma sessão, e votar).
- Testar requisições diretamente pelo navegador.
- Ver os modelos de entrada e saída das APIs em formato JSON

A documentação é gerada automaticamente com a dependência do **springdoc-openapi** e baseada nos controladores da aplicação.

# desafio-votacao
