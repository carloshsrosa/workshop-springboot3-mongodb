# Workshop Spring Boot 3 + MongoDB

Este repositório contém um projeto de exemplo para um workshop sobre desenvolvimento de aplicações utilizando **Spring Boot 3** e **MongoDB**.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data MongoDB**
- **MongoDB**
- **Lombok**
- **Postman ou Insomnia (para testes de API)**

## Como Executar o Projeto

### 1. Clonar o Repositório
```bash
git clone https://github.com/carloshsrosa/workshop-springboot3-mongodb.git
cd workshop-springboot3-mongodb
```

### 2. Configurar o MongoDB
Certifique-se de ter o MongoDB instalado e em execução na porta padrão (**27017**). Caso queira modificar a conexão, altere o arquivo `application.properties`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/workshop
```

### 3. Executar a Aplicação
```bash
./mvnw spring-boot:run
```
Ou, se estiver utilizando Maven instalado no sistema:
```bash
mvn spring-boot:run
```

A aplicação estará rodando em: `http://localhost:8080`

## Endpoints Disponíveis

A aplicação fornece os seguintes endpoints REST:

### Usuários
- **GET** `/users` - Lista todos os usuários
- **GET** `/users/{id}` - Busca um usuário por ID
- **POST** `/users` - Cria um novo usuário
- **PUT** `/users/{id}` - Atualiza um usuário
- **DELETE** `/users/{id}` - Remove um usuário

### Posts
- **GET** `/posts` - Lista todos os posts
- **GET** `/posts/{id}` - Busca um post por ID

## Estrutura do Projeto
```
workshop-springboot3-mongodb/
├── src/
│   ├── main/
│   │   ├── java/com/example/workshop/
│   │   │   ├── config/       # Configurações da aplicação
│   │   │   ├── controllers/  # Controllers REST
│   │   │   ├── entities/     # Modelos de dados
│   │   │   ├── repositories/ # Interfaces do Spring Data MongoDB
│   │   │   ├── services/     # Camada de serviços
│   ├── test/                 # Testes unitários e de integração
├── pom.xml                   # Dependências do Maven
├── README.md                 # Documentação do projeto
```

---
Desenvolvido por **Carlos Henrique Santos Rosa Viegas** 🚀

