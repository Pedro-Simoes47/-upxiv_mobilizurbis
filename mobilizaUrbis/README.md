# MobilizaUrbis - API Back-end


Este repositório contém a API back-end do projeto **MobilizaUrbis**, uma plataforma de participação cidadã desenvolvida para o 4º semestre do curso de Análise e Desenvolvimento de Sistemas.

A aplicação permite que cidadãos reportem anonimamente problemas de infraestrutura urbana (como buracos, problemas de iluminação, etc.) e fornece um painel de gerenciamento seguro para que a prefeitura possa visualizar, filtrar e atualizar o status desses relatos.

## 🚀 Funcionalidades Principais

* **Envio de Relatos Anônimos:** Permite que o cidadão envie fotos e descrições de problemas urbanos diretamente para a prefeitura, sem a necessidade de criar uma conta.
* **Geolocalização Automática:** O sistema está preparado para receber e armazenar as coordenadas de GPS (latitude e longitude) do local da ocorrência.
* **Painel de Administrador Seguro:** Uma área de gerenciamento protegida por login e senha (Spring Security) para uso exclusivo da prefeitura.
* **Visualização de Relatos:** O administrador pode visualizar um feed com todos os relatos que foram enviados pelos cidadãos.
* **Filtro de Relatos:** Permite ao administrador filtrar a lista de relatos por categoria (ex: "Lixo Acumulado", "Sinalização danificada").
* **Mudança de Status:** O administrador pode gerenciar o ciclo de vida de um relato, mudando seu status para "Aberto", "Em Andamento" ou "Concluído".

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias:

* **Java 17+:** Linguagem principal do projeto.
* **Spring Boot:** Framework principal para a criação da API RESTful.
* **Spring Security:** Para a camada de autenticação (Basic Auth) e autorização (proteção de endpoints de admin).
* **Spring Data JPA (Hibernate):** Para a persistência de dados e comunicação com o banco de dados.
* **PostgreSQL:** Banco de dados relacional para armazenamento dos dados.
* **Maven:** Gerenciador de dependências e build do projeto.
* **Lombok:** Para reduzir o código boilerplate (getters, setters, etc.).

---

## ⚙️ Configuração e Execução Local

Siga os passos abaixo para rodar o projeto em sua máquina local.

### 1. Pré-requisitos

* [Java (JDK) 17](https://www.oracle.com/java/technologies/downloads/#java17) ou superior.
* [Apache Maven](https://maven.apache.org/download.cgi)
* [PostgreSQL](https://www.postgresql.org/download/)
* Uma IDE de sua preferência (ex: IntelliJ IDEA, VS Code).

### 2. Configuração do Banco de Dados

1.  Inicie seu serviço do PostgreSQL.
2.  Crie um novo banco de dados. O nome padrão usado no projeto é `MobilizaUrbis`.
    ```sql
    CREATE DATABASE MobilizaUrbis;
    ```
3.  **Não é necessário criar as tabelas.** O Spring Data JPA (`ddl-auto=update`) fará isso automaticamente na primeira inicialização.

### 3. Configuração da Aplicação

1.  Clone este repositório:
    ```bash
    git clone [https://github.com/seu-usuario/mobilizaurbis-backend.git](https://github.com/seu-usuario/mobilizaurbis-backend.git)
    cd mobilizaurbis-backend
    ```
2.  Abra o arquivo `src/main/resources/application.properties`.
3.  Atualize as seguintes linhas com as suas credenciais do PostgreSQL:

    ```properties
    # Endereço do seu banco
    spring.datasource.url=jdbc:postgresql://localhost:5432/MobilizaUrbis
    
    # Seu usuário e senha do Postgres
    spring.datasource.username=postgres
    spring.datasource.password=sua-senha-aqui
    
    # Porta em que a aplicação vai rodar
    server.port=8081
    ```

### 4. Execução

1.  Abra o projeto na sua IDE.
2.  Aguarde o Maven baixar todas as dependências.
3.  Encontre a classe principal `MobilizaUrbisApplication.java` e execute-a.
4.  O servidor será iniciado e estará disponível em `http://localhost:8081`.

---

## 📖 Documentação da API (Endpoints)

A API está dividida em duas partes: endpoints públicos (para o cidadão) e endpoints de admin (para a prefeitura).

### 🔑 Autenticação

Todos os endpoints de `ADMIN` são protegidos. Você deve enviar as credenciais (`admin` e a senha definida no `SecurityConfig`) via **Basic Auth** no cabeçalho `Authorization`.

### 1. Endpoints Públicos (Cidadão)

#### `POST /api/relatos`

Cria um novo relato. Esta rota é pública e não requer autenticação.

**Request Body (Exemplo):**
```json
{
  "titulo": "Poste sem luz na rua principal",
  "descricao": "O poste em frente à padaria está apagado.",
  "localizacao": "Avenida Dom Aguirre, 1500",
  "latitude": -23.5034,
  "longitude": -47.4522,
  "categoria": {
    "id": 2
  }
}
```
 **Response (Sucesso 200 OK):** Retorna o objeto do relato que foi criado, agora com id, status ("Aberto") e dataCriacao.

```JSON

{
    "id": 1,
    "titulo": "Poste sem luz na rua principal",
    "descricao": "O poste em frente à padaria está apagado.",
    "localizacao": "Avenida Dom Aguirre, 1500",
    "latitude": -23.5034,
    "longitude": -47.4522,
    "dataCriacao": "2025-11-09T18:30:00.000000",
    "fotoURL": null,
    "status": "Aberto",
    "categoria": {
        "id": 2,
        "nome": "Problema de iluminação publica."
    }
}
```
### 2. Endpoints de Administração (Prefeitura)
**Requer Autenticação:** ```ADMIN```

```GET /api/relatos```

Lista todos os relatos enviados.

- **Sem filtro:** Retorna todos os relatos.

    - ```http://localhost:8081/api/relatos```
  

- **Com filtro:** Retorna apenas relatos de uma categoria específica.

    - ```http://localhost:8081/api/relatos?categoriaId=2```

**Response (Sucesso 200 OK):** Uma lista (array) de objetos de relato, como o visto acima.

```PATCH /api/relatos/{id}```

Atualiza o status de um relato específico (ex: para "Em Andamento").

```http://localhost:8081/api/relatos/1```

**Request Body (Exemplo):**

```JSON

{
    "status": "Em Andamento"
}
```
**Response (Sucesso 200 OK):** Retorna o objeto de relato completo, agora com o status atualizado.