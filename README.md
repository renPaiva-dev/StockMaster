# StockMaster

Sistema de controle de estoque e vendas desenvolvido para a disciplina de Engenharia de Software II. Permite o cadastro de produtos, registro de entradas e saídas de mercadorias, processamento de vendas e geração de relatórios gerenciais.

O sistema não possui interface gráfica. Toda a interação é feita via API REST, testável por Postman ou pela interface Swagger.

## Identificação da Equipe e Papéis

Conforme a organização do framework Scrum para o projeto, a equipe está dividida nos seguintes papéis:

# Sprint 01

**Product Owner (PO):** Samuel Alves — Responsável pela visão do produto e priorização do backlog.

**Scrum Master:** Renato Paiva — Responsável por organizar as sprints, acompanhar impedimentos e garantir a aplicação das práticas ágeis.

**Time de Desenvolvimento:** Alisson Gabriel, Luiz Felipe — Responsáveis pela análise, modelagem, implementação e testes do sistema.

# Sprint 02

**Product Owner (PO):** Alisson Gabriel — Responsável pela visão do produto e priorização do backlog.

**Scrum Master:** Samuel Alves — Responsável por organizar as sprints, acompanhar impedimentos e garantir a aplicação das práticas ágeis.

**Time de Desenvolvimento:** Renato de Paiva, Luiz Felipe — Responsáveis pela análise, modelagem, implementação e testes do sistema.

# Sprint 03
**Product Owner (PO):** Luiz Felipe — Responsável pela visão do produto e priorização do backlog.

**Scrum Master:** Alisson Gabriel — Responsável por organizar as sprints, acompanhar impedimentos e garantir a aplicação das práticas ágeis.

**Time de Desenvolvimento:** Samuel Alves, Renato de Paiva — Responsáveis pela análise, modelagem, implementação e testes do sistema.

## Pré-requisitos

- Java 17 ou superior
- Maven 3.8 ou superior
- Não é necessário instalar banco de dados — o projeto usa H2 em memória por padrão

## Versões utilizadas

- Java 17
- Spring Boot 3.5.15
- Spring Data JPA
- Banco H2 (desenvolvimento) — pode ser trocado para PostgreSQL em produção
- Maven

## Instalação

Clone o repositório e entre na pasta do projeto:

```bash
git clone https://github.com/renPaiva-dev/StockMaster.git
cd StockMaster
```

Instale as dependências:

```bash
mvn clean install
```

## Executando o sistema

Pela linha de comando:

```bash
mvn spring-boot:run
```

Ou, se estiver usando a IntelliJ IDEA, basta executar a classe `StockMasterApplication.java`.

A aplicação sobe por padrão na porta `8080`:

```
http://localhost:8080
```

## Configuração do banco de dados

O projeto usa o banco H2 em memória por padrão, sem necessidade de instalação. Os dados são reiniciados toda vez que a aplicação é encerrada.

As configurações estão em `src/main/resources/application.properties`. O console do H2 pode ser acessado em:

```
http://localhost:8080/h2-console
```

Use a seguinte URL de conexão ao abrir o console:

```
jdbc:h2:mem:stockmaster
```

Usuário: `SA`
Senha: (em branco)

## Documentação da API (Swagger/OpenAPI)

Após subir a aplicação, a documentação interativa está disponível em:

```
http://localhost:8080/swagger-ui.html
```

Lá é possível visualizar todos os endpoints disponíveis, os parâmetros esperados e testar as requisições diretamente pelo navegador.

## Endpoints disponíveis

### Produtos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/produtos` | Cadastra um novo produto |
| GET | `/produtos` | Lista todos os produtos |
| GET | `/produtos/{id}` | Busca um produto por ID |
| PUT | `/produtos/{id}` | Atualiza um produto existente |
| DELETE | `/produtos/{id}` | Remove um produto |

### Movimentações de estoque

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/movimentacoes/entrada` | Registra entrada de mercadoria |
| POST | `/movimentacoes/saida` | Registra saída de mercadoria |
| GET | `/movimentacoes/produto/{produtoId}` | Lista movimentações de um produto |

### Vendas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/vendas` | Registra uma venda com um ou mais itens |
| GET | `/vendas` | Lista todas as vendas |
| GET | `/vendas/{id}` | Busca uma venda por ID |

### Relatórios

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/relatorios/estoque-critico` | Lista produtos com estoque abaixo do mínimo |
| GET | `/relatorios/movimentacoes` | Histórico completo de movimentações |
| GET | `/relatorios/movimentacoes/periodo?inicio=...&fim=...` | Histórico de movimentações por período |
| GET | `/relatorios/vendas/resumo` | Resumo com total de vendas e valor acumulado |

## Exemplo de uso

Cadastrar um produto:

```bash
curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Arroz 5kg",
    "descricao": "Arroz branco tipo 1",
    "preco": 25.90,
    "quantidade": 100,
    "estoqueMinimo": 10
  }'
```

Registrar uma venda:

```bash
curl -X POST http://localhost:8080/vendas \
  -H "Content-Type: application/json" \
  -d '{
    "itens": [
      { "produtoId": 1, "quantidade": 5 }
    ]
  }'
```

## Dados de exemplo

Não há carga inicial de dados (`data.sql`). Para testar o sistema, cadastre produtos manualmente via `POST /produtos` antes de registrar movimentações ou vendas.

## Executando os testes

```bash
mvn test
```

## Arquitetura

O projeto segue arquitetura em camadas:

```
src/main/java/com/stockMaster/
├── controller/    → recebe requisições HTTP
├── service/       → regras de negócio
│   └── strategy/  → estratégias de cálculo de desconto
├── repository/    → acesso a dados (Spring Data JPA)
├── entity/        → entidades persistidas
├── dto/           → objetos de transferência de dados
├── enums/         → enumerações do domínio
├── event/         → eventos de domínio (padrão Observer)
├── exception/      → exceções customizadas e tratamento global de erros
└── config/        → configurações (OpenAPI, etc.)
```

## Principais limitações conhecidas

- Sem autenticação ou controle de acesso por perfil de usuário
- Banco H2 em memória: os dados são perdidos a cada reinicialização da aplicação
- Sem interface gráfica — testes feitos via Postman, curl ou Swagger UI

## Equipe

Projeto desenvolvido para a disciplina de Engenharia de Software II.
