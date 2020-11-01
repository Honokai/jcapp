/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.consoleapp.BancoConexao;
import entidades.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author eff
 */
public class AlunoDAO {
    
    Connection banco;
    
    public AlunoDAO() throws ClassNotFoundException, SQLException {
        BancoConexao bancoConexao = new BancoConexao();
        banco =  bancoConexao.conexao();
    }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws SQLException, Throwable {
        try{
            banco.close();
        } finally {
               super.finalize(); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    /**
     *
     * @param Aluno aluno
     * @return Integer
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Integer inserir(Aluno aluno) throws ClassNotFoundException, SQLException {
        try{
            PreparedStatement stmt = banco.prepareStatement("INSERT INTO "
                + "`aluno`(`nome`,`curso_id`,`email`,`cpf`, `nascimento`) "
                + "VALUES"
                + " (?,?,?,?,?)");
            java.util.Date data =  aluno.getNascimento();
            java.sql.Date databanco = new java.sql.Date(data.getTime());
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getCurso_id());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getCpf());
            stmt.setDate(5, databanco);

            return stmt.executeUpdate();
        } catch(SQLException e) {
            if(e.getMessage().contains("Cannot add or update a child row: a foreign key constraint fails")){
             System.out.println("Parece que você tentou inserir o aluno com um ID de curso inexistente, informe um ID válido.");   
            }
            return 0;
        }
        
    }
    
    /**
     *
     * @param aluno
     * @return Integer
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Integer apagarPeloNome(Aluno aluno) throws ClassNotFoundException, SQLException {
        PreparedStatement stmt = banco.prepareStatement("DELETE FROM aluno WHERE nome like ?");
        stmt.setString(1, "%"+aluno.getNome()+"%");
        return stmt.executeUpdate();
    }
    
    /**
     *
     * @param aluno
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Integer atualizarInfo(Aluno aluno) throws ClassNotFoundException, SQLException  {
        try{
            if(aluno.getEmail().equals("") == false && aluno.getCurso_id().equals(0) == false) {
                PreparedStatement stmt = banco.prepareStatement("Update aluno set email=?, curso_id=? WHERE cpf = ?");
                stmt.setString(1, aluno.getEmail());
                stmt.setInt(2, aluno.getCurso_id());
                stmt.setString(3, aluno.getCpf());
                return stmt.executeUpdate();
            } else{
                if(aluno.getEmail().equals("") == false) {
                    PreparedStatement stmt = banco.prepareStatement("Update aluno set email=? WHERE cpf = ?");
                    stmt.setString(1, aluno.getEmail());
                    stmt.setString(2, aluno.getCpf());
                    return stmt.executeUpdate();
                } else {
                    PreparedStatement stmt = banco.prepareStatement("Update aluno set curso_id=? WHERE cpf = ?");
                    stmt.setInt(1, aluno.getCurso_id());
                    stmt.setString(2, aluno.getCpf());
                    return stmt.executeUpdate();
                }
            }
        } catch(SQLException e) {
            if(e.getMessage().contains("Cannot add or update a child row: a foreign key constraint fails")){
                System.out.println("Parece que você tentou atualizar o aluno com um ID de curso inexistente, informe um ID válido.\n");   
            }
            return -1;
        }
        
    }

    public ArrayList listar() throws SQLException {
        PreparedStatement stmt = banco.prepareStatement("SELECT * FROM aluno");
        ArrayList<Aluno> alunos = new ArrayList<>();
        ResultSet resultado = stmt.executeQuery();
        Aluno aluno;
        while(resultado.next()) {
            aluno = new Aluno();
            aluno.setNome(resultado.getString("nome"));
            aluno.setCpf(resultado.getString("cpf"));
            aluno.setEmail(resultado.getString("email"));
            aluno.setNascimento(new java.util.Date(resultado.getDate("nascimento").getTime()));
            aluno.setId(Integer.parseInt(resultado.getString("id")));
            alunos.add(aluno);
        }
        return alunos;
    }
    
}
