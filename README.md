Esse projeto está relacionado ao desafio proposto em: https://gitsrv.ntconsult.com.br/desafios-ntconsult/desafio-java-senior

Dentre os critérios de avaliação o que ficou faltando foi o termino da consulta pela pesquisa de hoteis e 
escrever os testes, assegurando o funcionamento completo do que fora implementado.

    O banco de dados escolhido foi o Postgres e os principais pontos que contribuiram para isso foram: transações ACID,
    possibilidade de escalonamento horizontal através do particionamento de tabelas, capacidade de armazenar dados em 
    formato JSON e desconhecimento do modelo de um sistema de gerencimento de hotel. Quando em comparação com os NoSQL,
    o que mais contou foi a simplicidade de implementação em relação ao tempo.

Próximos passos:    
* Terminar implementação de consulta de hotéis.
* Complementar configurações do test containers.
* Implementar cenários de testes.
* Implementar testes de componentes.
* Melhorar as configurações entre os profiles (pelo menos so default e o de teste).
* Realizar tratamento de mensagens de erro.
* Conteinerizar a aplicação.
* Permitir a orquestração dela com o docker-compose para facilitar validação do sistema.
* Analisar métricas no Spring Boot Admin e verificar se será necessário separar o serviço em serviços menores, visando 
* proporcionar uma maior escalabilidade do sistema.

Idéias que gostaria de ter implementado:
* Contruir o sistema utilizando a arquitetura baseada em microserviços.
