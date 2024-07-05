## Esse projeto está relacionado ao desafio proposto em: https://gitsrv.ntconsult.com.br/desafios-ntconsult/desafio-java-senior

    O banco de dados escolhido foi o Postgres e os principais pontos que contribuiram para isso foram: transações ACID,
    possibilidade de escalonamento horizontal através do particionamento de tabelas, capacidade de armazenar dados em 
    formato JSON e desconhecimento do modelo de um sistema de gerencimento de hotel. Quando em comparação com bancos de dados
    NoSQL o que mais levei em conta foi a simplicidade de implementação em relação ao tempo.

## Ideias que gostaria de ter implementado:
* Contruir o sistema utilizando a arquitetura baseada em microserviços.
* Separar serviço de notificação e realizando a comunicação entre serviços.
* Implementar testes de integração.
* Containerizar a aplicação e orquestrar os containeres da aplicação e do banco de dados com o docker-compose a fim de buscar simplificar seu uso.
* Realizar compilação AOT com GraalVM buscando adequar mais a aplicação ao contexto de Cloud Native.
* Estratégia de logs.
* Realizar testes de carga.
* Conteinerizar a aplicação e orquestrar pelo Docker Compose.
* Melhorar configurações do perfil de teste

##  Tecnologias utilizadas:
* Spring Boot
* Spring Data
* Spring Web
* Spring Boot Validation
* Spring Boot Actuator
* Spring Boot Admin
* JobRunr
* Flyway
* Hibernate/JPAModelGen
* Lombok
* JUnit
* Test containers
* Banco de dados PostgreSQL

## Endpoints:
```curl
curl --location 'http://localhost:8080/hotels?dateCheckIn=2024-07-07&dateCheckOut=2024-07-10&numberOfRooms=1&numberOfGuests=1'
curl --location 'http://localhost:8080/hotels/1'
curl --location 'http://localhost:8080/reservations' \
--header 'Content-Type: application/json' \
--data '{
    "roomsId":[
        1
    ],
    "name":"Joao",
    "contact":"11987878787",
    "checkIn":"2024-07-15",
    "checkOut":"2024-07-30",
    "cardNumber":"516516518897984984"
}'
curl --location --request PATCH 'http://localhost:8080/reservations/check-in/5'
curl --location --request PATCH 'http://localhost:8080/reservations/check-out/5'
```

## Rotas
* http://localhost:8080/applications -> Spring Boot Admin
* http://localhost:8000/dashboard -> JobRunr