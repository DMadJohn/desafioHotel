# Desafio Hotel

## Primeiros passos
A configuracao de banco ficou no "application.properties" que se encontra na raiz do projeto.
Importante, as propriedades estão definidas para dropar e recriar as tabelas em toda inicialização.

## Requisitos
Se for rodar direto no IntelliJ é só rodar a aplicação direto, ele já tem os passos anteriores que garantem que tudo funcionara.
Caso não seja feito pelo IntelliJ será necessário rodar  "mvn process" e "mvn install" antes de rodar.

## Serviços implementados
 [Hospede] <br>
 (GET) | "/guests" | Retorna todos os hospedes registrados. <br>
 (POST) | "/guests/add" | Adiciona hospedes. <br>
 (POST) | "/guests/find-exact" | Traz o hospede com a exata informação passada no parâmetro "anything". Se chegar no fim sem encontrar o exato ele busca o mais próximo. <br>
 (POST) | "/guests/find-exact"  | Traz uma lista com todos os hospedes que tem alguma informação contendo a string passada no parâmetro "anything". <br> <br>
 
 [Estadia] <br>
 (GET) | "/stays" | Retorna todas as estadias registradas (sem a informação do valor gasto). <br>
 (GET) | "/stays/people-view/{?}" | Retorna uma as estadias com os cálculos de valor, "?" pode ser 1 (somente estadias validas no momento) ou pode ser 2 (somente estadias já concluídas). <br>
 (POST) | "/stays/add" | Adiciona estadias. <br>


A Implementação da estadia foi feita sem ligação direta com o hospede, ou seja, sao duas tabelas diferentes, fiz isso pois não consegui fazer as annotations CONVERT funcionarem.
