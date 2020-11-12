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
    
    public String criarUserDev() throws ClassNotFoundException, SQLException {
        try {
            System.out.println("Conectando ao banco...");
            Connection conexao = bancoConexao.conexao();
            System.out.println("Conexão efetuada com sucesso.");
            System.out.println("Criando usuário...");
            String query = "CREATE USER 'dev'@localhost IDENTIFIED BY 'dev'";
            Statement criar = conexao.createStatement();
            criar.executeUpdate(query);
            System.out.println("Usuário dev criado com sucesso. A senha é dev");
            query = "GRANT SELECT, INSERT, UPDATE, DELETE on universidade.* to 'dev'@localhost";
            criar = conexao.createStatement();
            criar.executeUpdate(query);
            System.out.println("Ações permitidas: SELECT, INSERT, UPDATE, DELETE para o usuário DEV.");
            query = "FLUSH PRIVILEGES";
            criar = conexao.createStatement();
            criar.executeUpdate(query);
            return "As permissões foram atualizadas! Caso queira utilizar o mesmo, altere em BancoConexao.";
        } catch(SQLException e) {
            return e.toString();
        }
        
    }
    
    public String criarUserDba() throws ClassNotFoundException, SQLException {
        try {
            System.out.println("Conectando ao banco...");
            Connection conexao = bancoConexao.conexao();
            System.out.println("Conexão efetuada com sucesso.");
            System.out.println("Criando usuário...");
            String query = "CREATE USER 'dba'@localhost IDENTIFIED BY 'dba'";
            Statement criar = conexao.createStatement();
            criar.executeUpdate(query);
            System.out.println("Usuário dba criado com sucesso. A senha é dba");
            query = "GRANT ALL ON universidade.* to 'dba'@localhost";
            criar = conexao.createStatement();
            criar.executeUpdate(query);
            query = "FLUSH PRIVILEGES";
            criar = conexao.createStatement();
            criar.executeUpdate(query);
            return "As permissões foram atualizadas! Caso queira utilizar o mesmo, altere em BancoConexao.";
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
            String query = "CREATE TABLE `curso` (`id` int(11) NOT NULL AUTO_INCREMENT,"
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
            String query = "CREATE TABLE IF NOT EXISTS `aluno` (`id` int(11) NOT NULL AUTO_INCREMENT,"
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
            String query = "CREATE TABLE IF NOT EXISTS `professor` ("
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
            String query = "CREATE TABLE `disciplina` ("
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
            String query = "CREATE TABLE IF NOT EXISTS `materias` ("
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
            String query = "DROP DATABASE IF EXISTS universidade";
            Statement criar = conexao.createStatement();
            criar.executeUpdate(query);
            return "Banco universidade destruido com sucesso!";
        } catch(SQLException e) {
            return e.toString();
        }
    }
    
    public void criarTriggers() throws ClassNotFoundException, ClassNotFoundException{
        try {
            System.out.println("Conectando ao banco...");
            Connection conexao = bancoConexao.conexao();
            System.out.println("Conexão efetuada com sucesso.");
            System.out.println("Criando triggers");
            String query = "create trigger valor_maximo_insert\n" +
            "    before insert\n" +
            "    on universidade.disciplina\n" +
            "    for each row\n" +
            "    BEGIN\n" +
            "        if NEW.av1 > 7 or NEW.av2 > 8 or NEW.av3 > 10 or NEW.aps1 > 3 or NEW.aps2 > 2 then\n" +
            "                signal sqlstate '41200' set message_text ='Erro: Valores informados acima do permitido.'\n;" +
            "        end if;\n" +
            "        if NEW.av1 < 0 or NEW.av2 < 0 or NEW.av3 < 0 or NEW.aps1 < 0 or NEW.aps2 < 0 then\n" +
            "                signal sqlstate '41200' set message_text ='Erro: Valores informados abaixo do permitido.';\n" +
            "        end if;\n" +
            "    end";
            Statement criar = conexao.createStatement();
            criar.executeUpdate(query);
            query = "create trigger valor_maximo_update\n" +
            "    before update\n" +
            "    on universidade.disciplina\n" +
            "    for each row\n" +
            "BEGIN\n" +
            "    if NEW.av1 > 7 or NEW.av2 > 8 or NEW.av3 > 10 or NEW.aps1 > 3 or NEW.aps2 > 2 then\n" +
            "            signal sqlstate '41200' set message_text ='Erro: Valores informados acima do permitido.';\n" +
            "    end if;\n" +
            "    if NEW.av1 < 0 or NEW.av2 < 0 or NEW.av3 < 0 or NEW.aps1 < 0 or NEW.aps2 < 0 then\n" +
            "            signal sqlstate '41200' set message_text ='Erro: Valores informados abaixo do permitido.';\n" +
            "    end if;\n" +
            "end";
            criar = conexao.createStatement();
            criar.executeUpdate(query);
           System.out.print("Triggers criados.\n\n");
        } catch(SQLException e) {
            System.out.println(e.toString());
        }
    }
}
