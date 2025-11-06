# gRPC com Quarkus

**Curso ADJT - Fiap ON**

## Acesso

- **servidor** - conecta a um banco de dados postgres, e expõe duas interfaces:
  - REST (porta 8080)
  - gRPC (porta 9000)

- **cliente** - conecta ao **servidor gRPC** e expõe uma API REST (porta 8089)

## Collections

Em `workspace/LivrosCollection`, collection de chamadas para uso com o [Bruno](http://usebruno.com). Agrupadas em *Cliente* e *Servidor*, acessando os serviços como citado acima.

## Rodando

Rodar o postgres

`podman compose -f workspace/compose/postgres/compose.yml -d`

Rodar as aplicações `servidor` e `cliente`, uma em cada terminal, com o comando `quarkus:dev`
