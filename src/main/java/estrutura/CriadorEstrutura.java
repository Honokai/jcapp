/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura;

import com.consoleapp.BancoConexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author eff
 */
public class CriadorEstrutura {
    
    private BancoConexao bancoConexao = new BancoConexao();
    
    public String criarBanco() throws ClassNotFoundException, SQLException {
        try {
            System.out.println("Conectando ao banco...");
            Connection conexao = bancoConexao.conexao();
            System.out.println("Conexão efetuada com sucesso.");
            System.out.println("Criando banco...");
            String query = "CREATE DATABASE IF NOT EXISTS universo_supremo";
            Statement criar = conexao.createStatement();
            criar.executeUpdate(query);
            return "Banco UNIVERSIDADE criado com sucesso!";
        } catch(SQLException e) {
            return e.toString();
        }
        
    }
    
    public String criarTabelaCurso() throws ClassNotFoundException, SQLException {
        try {
            System.out.println("Conectando ao banco...");
            Connection conexao = bancoConexao.conexao();
            System.out.println("Conexão efetuada com sucesso.");
            System.out.println("Criando tabela...");
            String query = "CREATE TABLE `universo_supremo`.`curso` (`id` int(11) NOT NULL AUTO_INCREMENT,"
                    + " `curso` varchar(120) COLLATE utf8mb4_bin NOT NULL,"
                    + " PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=0"
                    + " DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";
            Statement criar = conexao.createStatement();
            criar.executeUpdate(query);
            return "Tabela CURSO criada com sucesso!";
        } catch(SQLException e) {
            return "Ocorreu o seguinte erro: "+e.toString();
        }
    }
    
    public String criarTabelaAluno()  throws ClassNotFoundException, SQLException {
        try {
            System.out.println("Conectando ao banco...");
            Connection conexao = bancoConexao.conexao();
            System.out.println("Conexão efetuada com sucesso.");
            System.out.println("Criando tabela...");
            String query = "CREATE TABLE IF NOT EXISTS `universo_supremo`.`aluno` (`id` int(11) NOT NULL AUTO_INCREMENT,"
                    + " `nome` varchar(120) COLLATE utf8mb4_bin NOT NULL, `curso_id` int(80) NOT NULL,"
                    +"`email` varchar(180) COLLATE utf8mb4_bin NOT NULL,"
                    +"`cpf` varchar(50) COLLATE utf8mb4_bin NOT NULL,"
                    +"`nascimento` date NOT NULL,"
                    + " PRIMARY KEY (`id`), KEY `curso_id` (`curso_id`), CONSTRAINT `aluno_ibfk_1`"
                    + " FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`) ON DELETE NO ACTION)"
                    + " ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT"
                    + " CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";
            Statement criar = conexao.createStatement();
            criar.executeUpdate(query);
            return "Tabela ALUNO criada com sucesso!";
        } catch(SQLException e) {
            return "Ocorreu o seguinte erro: "+e.toString();
        }
    }
    
    public String criarTabelaProfessor()  throws ClassNotFoundException, SQLException {
        try {
            System.out.println("Conectando ao banco...");
            Connection conexao = bancoConexao.conexao();
            System.out.println("Conexão efetuada com sucesso.");
            System.out.println("Criando tabela...");
            String query = "CREATE TABLE IF NOT EXISTS `universo_supremo`.`professor` ("
                    +"`id` int(11) NOT NULL AUTO_INCREMENT,"
                    +"`professor` varchar(120) COLLATE utf8mb4_bin NOT NULL,"
                    +"`email` varchar(180) COLLATE utf8mb4_bin NOT NULL,"
                    +"PRIMARY KEY (`id`)"
                    +") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin";
            Statement criar = conexao.createStatement();
            criar.executeUpdate(query);
            return "Tabela PROFESSOR criada com sucesso!";
        } catch(SQLException e) {
            return "Ocorreu o seguinte erro: "+e.toString();
        }
    }
    
    public String criarTabelaDisciplina()  throws ClassNotFoundException, SQLException {
        try {
            System.out.println("Conectando ao banco...");
            Connection conexao = bancoConexao.conexao();
            System.out.println("Conexão efetuada com sucesso.");
            System.out.println("Criando tabela...");
            String query = "CREATE TABLE `universo_supremo`.`disciplina` ("
                    + "`id` int(11) NOT NULL AUTO_INCREMENT,"
                    + "`disciplina` varchar(120) COLLATE utf8mb4_bin NOT NULL,"
                    + "`av1` double DEFAULT 0,"
                    + "`av2` double DEFAULT 0,"
                    + "`av3` double DEFAULT 0,"
                    + "`aps1` double DEFAULT 0,"
                    + "`aps2` double DEFAULT 0,"
                    + "`aluno_id` int(11) DEFAULT NULL,"
                    + "`professor_id` int(11) DEFAULT NULL,"
                    + "PRIMARY KEY (`id`),"
                    + "KEY `aluno_id` (`aluno_id`),"
                    + "KEY `professor_id` (`professor_id`),"
                    + "CONSTRAINT `disciplina_ibfk_1` FOREIGN KEY (`aluno_id`) REFERENCES `aluno` (`id`) ON DELETE CASCADE,"
                    + "CONSTRAINT `disciplina_ibfk_2` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`id`) ON DELETE CASCADE"
                    + ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin";
            Statement criar = conexao.createStatement();
            criar.executeUpdate(query);
            return "Tabela DISCIPLINA criada com sucesso!";
        } catch(SQLException e) {
            return "Ocorreu o seguinte erro: "+e.toString();
        }
    }
    
    public String criarTabelaMateria()  throws ClassNotFoundException, SQLException {
        try {
            System.out.println("Conectando ao banco...");
            Connection conexao = bancoConexao.conexao();
            System.out.println("Conexão efetuada com sucesso.");
            System.out.println("Criando tabela...");
            String query = "CREATE TABLE IF NOT EXISTS `universo_supremo`.`materias` ("
            +"`id` int(11) NOT NULL AUTO_INCREMENT,"
            + "`materia` varchar(150) COLLATE utf8mb4_bin DEFAULT NULL,"
            + " PRIMARY KEY (`id`),"
            +"UNIQUE KEY `materia` (`materia`)"
            +") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin";
            Statement criar = conexao.createStatement();
            criar.executeUpdate(query);
            return "Tabela Materia criada com sucesso!";
        } catch(SQLException e) {
            return "Ocorreu o seguinte erro: "+e.toString();
        }
    }
    
    public String destruirBanco() throws ClassNotFoundException, SQLException {
        try {
            System.out.println("Conectando ao banco...");
            Connection conexao = bancoConexao.conexao();
            System.out.println("Conexão efetuada com sucesso.");
            System.out.println("Destruindo banco...");
            String query = "DROP DATABASE IF EXISTS universo_supremo";
            Statement criar = conexao.createStatement();
            criar.executeUpdate(query);
            return "Banco universo_supremo destruido com sucesso!";
        } catch(SQLException e) {
            return e.toString();
        }
        
    }
    
}
