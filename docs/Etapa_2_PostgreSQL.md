# Etapa 2 - Conexao com o PostgreSQL

Esta continuacao utiliza o mesmo projeto do primeiro bimestre. Nesta etapa,
foram adicionadas apenas a configuracao de conexao e a instrucao SQL para criar
o banco. O modelo e o controller ainda nao foram adaptados para persistencia.

## 1. Criar o banco

No pgAdmin, conecte ao seu servidor PostgreSQL e abra o Query Tool do banco
`postgres`. Execute uma vez o arquivo `sql/criar_banco.sql`:

```sql
CREATE DATABASE trabalho2bi;
```

Esse comando cria o banco. A tabela de veiculos sera criada quando o modelo
`Veiculo` for mapeado como entidade JPA em uma etapa posterior.

## 2. Conferir a conexao

O arquivo `src/main/resources/application.properties` foi configurado assim:

```properties
spring.application.name=Trabalho1bi
spring.datasource.url=jdbc:postgresql://localhost:5432/trabalho2bi
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_DO_POSTGRESQL
spring.jpa.hibernate.ddl-auto=update
```

- `url`: conecta ao PostgreSQL no proprio computador, na porta `5432`, usando
  o banco `trabalho2bi`. Ajuste o endereco ou a porta se sua instalacao for diferente.
- `username`: usuario do PostgreSQL. Ajuste se voce usa outro usuario.
- `password`: substitua `SUA_SENHA_DO_POSTGRESQL` pela senha da sua instalacao,
  somente na sua copia local. O texto do exemplo nao e uma senha real.
- `ddl-auto=update`: permite criar ou atualizar as tabelas a partir das entidades
  JPA. Nao recria as tabelas a cada reinicio, permitindo manter os registros.

## Verificacao desta etapa

As propriedades e o nome do banco no SQL foram conferidos. A conexao real nao
foi testada neste ambiente, que nao possui PostgreSQL instalado. O download
de dependencias pelo Maven tambem ficou bloqueado pelo acesso ao Maven Central
na etapa anterior. Ainda nao ha demonstracao de CRUD ou persistencia.

## Proxima etapa

Mapear `Veiculo` com `@Entity`, `@Id` e `@GeneratedValue`, mantendo os campos
`id`, `nome`, `marca`, `modelo` e `ano`.

## Referencias

- Spring Boot: https://docs.spring.io/spring-boot/reference/data/sql.html
- PostgreSQL: https://www.postgresql.org/docs/current/sql-createdatabase.html
