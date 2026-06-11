# StockMaster - Sistema de Controle de Estoque e Vendas

> **Disciplina:** Engenharia de Software II
> **Atividade:** Trabalho Prático em Equipe (Sprint 1)

---

## 1. Identificação da Equipe e Papéis

Conforme a organização do framework Scrum para o projeto, a equipe está dividida nos seguintes papéis:

**Product Owner (PO):** Samuel Alves — Responsável pela visão do produto e priorização do backlog.

**Scrum Master:** Renato Paiva — Responsável por organizar as sprints, acompanhar impedimentos e garantir a aplicação das práticas ágeis.

**Time de Desenvolvimento:** Alisson Gabriel, Luiz Felipe — Responsáveis pela análise, modelagem, implementação e testes do sistema.

---

## 2. Visão Geral do Produto e Problema

### O Problema

Muitas pequenas empresas realizam o controle de estoque e vendas de forma manual, utilizando planilhas ou anotações em papel. Isso pode causar:

* Divergências entre estoque físico e registrado;
* Falhas no registro de vendas;
* Dificuldade para acompanhar entradas e saídas de mercadorias;
* Lentidão na obtenção de informações para tomada de decisão.

### A Solução

O StockMaster é um sistema de controle de estoque e vendas que permite cadastrar produtos, registrar movimentações de estoque, controlar vendas e gerar relatórios gerenciais, garantindo maior organização, confiabilidade e agilidade na gestão.

---

## 3. Funcionalidades Principais (Escopo)

O sistema conta com as seguintes funcionalidades principais:

### 1. Gerenciar Produtos

* Cadastro de produtos;
* Consulta e listagem de produtos;
* Atualização de informações;
* Controle de quantidade disponível.

### 2. Controlar Movimentações de Estoque

* Registro de entradas de mercadorias;
* Registro de saídas de mercadorias;
* Atualização automática do saldo disponível.

### 3. Realizar Vendas

* Registro de vendas;
* Baixa automática no estoque;
* Histórico de transações realizadas.

### 4. Gerar Relatórios

* Relatórios de movimentações;
* Relatórios de vendas;
* Produtos com estoque baixo.

---

## 4. Tecnologias Utilizadas

O projeto foi estruturado utilizando as seguintes tecnologias:

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.x
* **Gerenciador de Dependências:** Maven
* **Persistência de Dados:** Spring Data JPA
* **Banco de Dados:** PostgreSQL
* **Validação:** Jakarta Validation
* **Boilerplate Reduction:** Lombok

---

## 5. Arquitetura do Projeto

O sistema segue uma arquitetura em camadas:

```text
src/main/java/com/stockmaster
│
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
└── config
```

### Responsabilidades

* **Controller:** Recebe requisições HTTP.
* **Service:** Implementa as regras de negócio.
* **Repository:** Realiza o acesso ao banco de dados.
* **Entity:** Representa as entidades persistidas.
* **DTO:** Transporte de dados entre camadas.
* **Exception:** Tratamento centralizado de erros.

---

## 6. Como Executar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/renPaiva-dev/StockMaster.git
```

### 2. Entrar na pasta do projeto

```bash
cd StockMaster
```

### 3. Configurar o banco PostgreSQL

Crie um banco de dados chamado:

```sql
CREATE DATABASE stockmaster;
```

Configure usuário e senha no arquivo:

```properties
src/main/resources/application.properties
```

### 4. Instalar dependências e compilar

```bash
mvn clean install
```

### 5. Executar a aplicação

```bash
mvn spring-boot:run
```

### 6. Acessar a aplicação

Por padrão, a API estará disponível em:

```text
http://localhost:8080
```

---

## 7. Status da Sprint 1

* ✔ Estrutura inicial do projeto criada
* ✔ Configuração do Spring Boot
* ✔ Configuração do PostgreSQL
* ✔ Definição da arquitetura em camadas
* ✔ Criação da entidade Produto
* ✔ Implementação inicial da US01 (Cadastro de Produtos)
* ✔ Configuração do repositório GitHub

---


