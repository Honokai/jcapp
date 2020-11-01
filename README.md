# Aplicação Java Console

## Desenvolvimento
Tarefa desenvolvida utilizando o **NetBeans**.

## Explicando o projeto
Este projeto contém 4 pastas em **src/main**, sendo elas:
1. com
2. daos
3. entidades
4. estrutura

### COM
Contém somente uma pasta chamada **consoleapp**, que nela está contida o arquivo de configuração para o banco, edite os campos de login e senha de acordo com o necessário no arquivo `BancoConexão.java`.  
O arquivo `Principal.java` é de onde você deve partir a execução, pois o mesmo tem a função **main(String[] args)** definido no mesmo, e a partir dele os demais itens se tornam disponíveis.

### DAOS
Contém as classes responsáveis por fazerem as consultas, atualizações, exclusões no banco de dados.

### Entidades
Contém as classes que representam a tabela no banco.

### Estrutura
Contém uma única classe que contém métodos para criação das tabelas e destruição do banco deste projeto.


## SQL
Execute este sql para criação do banco.
```sql
CREATE DATABASE IF NOT EXISTS universidade;
```
este para criação dos usuários DBA e DEV:
```sql
/*criar usuario administrador do banco*/
CREATE USER 'dba'@localhost IDENTIFIED BY 'dba';
GRANT ALL ON universidade.* to 'dba'@localhost;
FLUSH PRIVILEGES;

/*criar usuario comum*/
CREATE USER 'dev'@localhost IDENTIFIED BY 'dev';
GRANT SELECT, INSERT, UPDATE, DELETE on universidade.* to 'dev'@localhost;
flush privileges;
```

## Integrantes 
Nome: **Emerson F. Fernandes**  
M: 2017xxx458