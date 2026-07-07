# Desafio: CRUD de Clientes 🚀

Este projeto é uma API REST desenvolvida em Java com Spring Boot, construída como parte da **Formação Java Spring Professional** pela **DevSuperior**.

O objetivo principal é implementar um sistema completo de gerenciamento de clientes com tratamento de exceções customizado e validações de dados.

---

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java
* **Framework:** Spring Boot
* **Gerenciador de Dependências:** Maven
* **Banco de Dados:** H2 (Ambiente de Testes)
* **ORM:** JPA / Hibernate

---

## 🗂️ Modelo de Domínio (Entidade Client)
A especificação da classe `Client` segue rigorosamente o diagrama proposto:

* **id:** `Long` (Chave Primária)
* **name:** `String`
* **cpf:** `String`
* **income:** `Double`
* **birthDate:** `LocalDate`
* **children:** `Integer`

> ⚠️ **Aviso de Mapeamento:** Conforme o padrão JPA, o atributo `birthDate` em *camelCase* é traduzido automaticamente para `birth_date` em *snake_case* no banco de dados H2. O arquivo de *seed* SQL (`import.sql`) segue estritamente essa regra.

---

## ⚙️ Funcionalidades e Endpoints

A API expõe o recurso `/clients` oferecendo as 5 operações básicas do CRUD:

### 1. Busca Paginada de Recursos
* **Endpoint:** `GET /clients`
* **Parâmetros aceitos:** `page`, `size`, `sort`
* **Exemplo de URL:** `/clients?page=0&size=6&sort=name`

### 2. Busca por ID
* **Endpoint:** `GET /clients/{id}`
* **Exemplo:** `GET /clients/1`

### 3. Inserir Novo Recurso
* **Endpoint:** `POST /clients`
* **Payload Exemplo:**
  ```json
  {
    "name": "Maria Silva",
    "cpf": "12345678901",
    "income": 6500.0,
    "birthDate": "1994-07-20",
    "children": 2
  }

### 4. Atualizar Recurso
* **Endpoint:** `PUT /clients/{id}`
* **Exemplo:** `PUT /clients/1`

### 5. Deletar Recurso
* **Endpoint:** `DELETE /clients/{id}`
* **Exemplo:** `DELETE /clients/1`

## 🛡️ Validações e Tratamento de Exceções
O projeto gerencia de forma customizada os seguintes cenários de erro:

### 1. Recurso Não Encontrado (Código `404 Not Found`)
* Disparado quando um id informado nas requisições GET, PUT ou DELETE não existe na base de dados.

### 2. Erro de Validação (Código `422 Unprocessable Entity`)
* Retorna um JSON contendo uma lista estruturada de erros com mensagens personalizadas para cada campo inválido.

* **Regras de Negócio Validadas:**
* `name`: Não pode ser vazio ou em branco.
* `birthDate`: Não pode ser uma data futura (validado via @PastOrPresent).

## 💾 Carga Inicial de Dados (Seed)
Ao iniciar a aplicação, o banco de dados H2 é automaticamente populado com pelo menos 10 registros significativos de clientes para facilitar a validação e os testes das buscas paginadas de forma imediata.

## 🚀 Como Executar o Projeto
**1. Certifique-se de ter o Java (versão correspondente ao projeto) e o Maven instalados na sua máquina.**

**2. Clone este repositório:**
`git clone https://github.com/jfrancodardengo/crud-clientes.git`

**3. Navegue até a pasta raiz do projeto e execute:**
```bash
mvn spring-boot:run
```
**4. A aplicação estará disponível no endereço padrão: `http://localhost:8080`.**

**5. O console de gerenciamento do banco H2 pode ser acessado via `http://localhost:8080/h2-console` caso precise inspecionar as tabelas geradas.**
