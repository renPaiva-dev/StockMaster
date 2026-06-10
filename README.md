# Sistema de Reserva de Salas de Estudo
> [cite_start]**Disciplina:** Engenharia de Software II [cite: 3]  
> [cite_start]**Atividade:** Trabalho Prático em Equipe (Sprint 1) [cite: 4, 79]

---

## 1. Identificação da Equipe e Papéis
[cite_start]Conforme a organização do framework Scrum para o projeto, a equipe está dividida nos seguintes papéis[cite: 70]:

* [cite_start]**Product Owner (PO):** [Nome do Aluno] — Responsável pela visão do produto e priorização do backlog[cite: 71].
* [cite_start]**Scrum Master:** Renato Paiva — Responsável por organizar as sprints e acompanhar impedimentos[cite: 72].
* [cite_start]**Time de Desenvolvimento:** Alisson Gabriel, [Nome do Aluno 2] — Responsáveis pela análise, projeto e codificação[cite: 73].

---

## 2. Visão Geral do Produto e Problema
* [cite_start]**O Problema:** A dificuldade de estudantes em encontrar e reservar salas de estudo disponíveis de forma rápida, gerando conflitos de horários e falta de controle sobre o uso dos espaços[cite: 60].
* [cite_start]**A Solução:** Um pequeno sistema de software focado no gerenciamento e reserva de salas, permitindo que os alunos consultem a disponibilidade e os administradores controlem o uso do ambiente de forma justa e transparente[cite: 10, 60].

---

## 3. Funcionalidades Principais (Escopo)
[cite_start]O sistema conta com 3 funcionalidades principais distribuídas em 5 histórias de usuário[cite: 27]:

1. [cite_start]**Gerenciar Salas:** Cadastro, edição, consulta e inativação de salas de estudo[cite: 62].
2. [cite_start]**Solicitar Reserva:** Permite ao usuário solicitar uma sala informando data e horário[cite: 62].
3. [cite_start]**Gerenciar Situação da Reserva:** Permite aprovar, rejeitar ou cancelar os pedidos[cite: 62].

---

## 4. Pré-requisitos e Tecnologias
O projeto foi estruturado utilizando as seguintes tecnologias de mercado, atendendo aos requisitos mínimos da Sprint 1:

* **Linguagem:** Java 17
* [cite_start]**Framework:** Spring Boot 3.2.5 [cite: 38]
* **Gerenciador de Dependências:** Maven
* [cite_start]**Persistência de Dados:** Spring Data JPA [cite: 35]
* **Banco de Dados:** H2 Database (Banco em memória para desenvolvimento ágil)

---

## 5. Como Executar o Projeto
Para clonar e rodar esta base do projeto em sua máquina local, siga os passos abaixo no terminal:

```bash
# 1. Clone o repositório
git clone [https://github.com/renPaiva-dev/StockMaster do GitHub da equipe]

# 2. Entre na pasta do projeto
cd meu-projeto-es2

# 3. Compile o projeto e baixe as dependências do pom.xml
mvn clean install

# 4. Execute a aplicação Spring Boot
mvn spring-boot:run