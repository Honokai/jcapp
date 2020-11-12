# Aplicação Java Console

## Desenvolvimento
Tarefa desenvolvida utilizando o **NetBeans**.

## Explicando o projeto
Este projeto contém 4 pastas em **src/main**, sendo elas:
1. [com](#com)
2. [daos](#daos)
3. [entidades](#entidades)
4. [estrutura](#estrutura)

### COM
Contém somente uma pasta chamada **consoleapp**, que nela está contida o arquivo de configuração para o banco, edite os campos de login e senha de acordo com o necessário no arquivo `BancoConexão.java`.  
O arquivo `Principal.java` é de onde você deve partir a execução, pois o mesmo tem a função **main(String[] args)** definido no mesmo, e a partir dele os demais itens se tornam disponíveis.
```java
 
    public BancoConexao() {
        this.servidor = "127.0.0.1";
        this.porta = "3306";
        this.banco = "universidade"; //este é o nome do banco
        this.login = "coloque o usuario aqui"; 
        this.senha = "coloque a senha aqui"; 
    }
    
```
### DAOS
Contém as classes responsáveis por fazerem as consultas, atualizações, exclusões no banco de dados.

### ENTIDADES
Contém as classes que representam a tabela no banco.

### ESTRUTURA
Contém uma única classe que contém métodos para criação das tabelas e destruição do banco deste projeto.


## SQL
Execute este sql para criação do banco, o restante da estrutura como tabelas e triggers a aplicação no console fará, basta que informe a opção.
```sql
CREATE DATABASE IF NOT EXISTS universidade;
```

## Integrantes 
Nome: **Emerson F. Fernandes**  
M: 2017xxx458