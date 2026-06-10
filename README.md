# Sistema de Reserva de Salas de Estudo
>**Disciplina:** Engenharia de Software II  
>**Atividade:** Trabalho Prático em Equipe (Sprint 1).

---

## 1. Identificação da Equipe e Papéis
Conforme a organização do framework Scrum para o projeto, a equipe está dividida nos seguintes papéis:

**Product Owner (PO):** [Nome do Aluno] — Responsável pela visão do produto e priorização do backlog.
**Scrum Master:** Renato Paiva — Responsável por organizar as sprints e acompanhar impedimentos.
**Time de Desenvolvimento:** Alisson Gabriel, [Nome do Aluno 2] — Responsáveis pela análise, projeto e codificação.

---

## 2. Visão Geral do Produto e Problema
**O Problema:** A dificuldade de estudantes em encontrar e reservar salas de estudo disponíveis de forma rápida, gerando conflitos de horários e falta de controle sobre o uso dos espaços.
**A Solução:** Um pequeno sistema de software focado no gerenciamento e reserva de salas, permitindo que os alunos consultem a disponibilidade e os administradores controlem o uso do ambiente de forma justa e transparente.

---

## 3. Funcionalidades Principais (Escopo)
O sistema conta com 3 funcionalidades principais distribuídas em 5 histórias de usuário:

1. **Gerenciar Salas:** Cadastro, edição, consulta e inativação de salas de estudo.
2. **Solicitar Reserva:** Permite ao usuário solicitar uma sala informando data e horário.
3. **Gerenciar Situação da Reserva:** Permite aprovar, rejeitar ou cancelar os pedidos.

---

## 4. Pré-requisitos e Tecnologias
O projeto foi estruturado utilizando as seguintes tecnologias de mercado, atendendo aos requisitos mínimos da Sprint 1:

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.2.5
* **Gerenciador de Dependências:** Maven
* **Persistência de Dados:** Spring Data JPA
* **Banco de Dados:** H2 Database (Banco em memória para desenvolvimento ágil)

---

## 5. Como Executar o Projeto
Para clonar e rodar esta base do projeto em sua máquina local, siga os passos abaixo no terminal:

```bash
# 1. Clone o repositório
git clone https://github.com/renPaiva-dev/StockMaster

# 2. Entre na pasta do projeto
cd meu-projeto-es2

# 3. Compile o projeto e baixe as dependências do pom.xml
mvn clean install

# 4. Execute a aplicação Spring Boot
mvn spring-boot:run
