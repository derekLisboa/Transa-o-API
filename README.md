
# Transação API

Este projeto é um desafio técnico para dev junior feito pelo ITAU. É uma API REST para gerenciar transações e calcular estatísticas das transações realizadas nos últimos 60 segundos. A API foi desenvolvida com Java e Spring Boot.


## Documentação da API

#### Receber Transações

```http
  POST /transacao
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `BigDecimal` | **Obrigatório**. O valor da transação 
| `dataHora` | `OffsetDateTime` | **Obrigatório**. O horário que a transação ocorreu

#### Limpar Transações

```http
  DELETE /transacao
```

#### Calcular Estatísticas

```http
  GET /estatistica
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `intervaloSegundos` | `integer` | **Não Obrigatório** O padrão default é 60s  |
