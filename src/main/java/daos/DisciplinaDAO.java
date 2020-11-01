/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.consoleapp.BancoConexao;
import entidades.Disciplina;
import entidades.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author eff
 */
public class DisciplinaDAO {
    
    Connection banco;
    
    public DisciplinaDAO() throws ClassNotFoundException, SQLException {
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
     * @param disciplina
     * @return Integer
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Integer inserir(Disciplina disciplina) throws ClassNotFoundException, SQLException {
        try{
            PreparedStatement stmt = banco.prepareStatement("INSERT INTO "
                + "`disciplina`(`disciplina`,`av1`,`av2`,`av3`,`aps1`,`aps2`,`aluno_id`,`professor_id`) "
                + "VALUES"
                + " (?,?,?,?,?,?,?,?)");
            stmt.setString(1, disciplina.getDisciplina());
            stmt.setFloat(2, disciplina.getAv1());
            stmt.setFloat(3, disciplina.getAv2());
            stmt.setFloat(4, disciplina.getAv3());
            stmt.setFloat(5, disciplina.getAps1());
            stmt.setFloat(6, disciplina.getAps2());
            stmt.setInt(7, disciplina.getAluno_id());
            stmt.setInt(8, disciplina.getProfessor_id());
            
            return stmt.executeUpdate();
        } catch(SQLException e) {
            if(e.getMessage().contains("Cannot add or update a child row: a foreign key constraint fails")){
             System.out.println("Parece que você tentou inserir a disciplina com um ID de aluno ou professor inexistente, informe um ID válido.\n");   
            }
            return 0;
        }
        
    }
    
    /**
     *
     * @param disciplina
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Integer apagarPeloNome(Disciplina disciplina) throws ClassNotFoundException, SQLException {
        PreparedStatement stmt = banco.prepareStatement("DELETE FROM disciplina WHERE disciplina like ? and aluno_id=? and professor_id=?");
        stmt.setString(1, "%"+disciplina.getDisciplina()+"%");
        stmt.setInt(2, disciplina.getAluno_id());
        stmt.setInt(3, disciplina.getProfessor_id());
        return stmt.executeUpdate();
    }
    
    /**
     *
     * @param disciplina
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Integer atualizarInfo(Disciplina disciplina) throws ClassNotFoundException, SQLException  {
        PreparedStatement stmt = banco.prepareStatement("UPDATE disciplina set av1=?, av2=?, av3=?, aps1=?, aps2=?"
                + " WHERE id=? and aluno_id=?");
        stmt.setFloat(1, disciplina.getAv1());
        stmt.setFloat(2, disciplina.getAv2());
        stmt.setFloat(3, disciplina.getAv3());
        stmt.setFloat(4, disciplina.getAps1());
        stmt.setFloat(5, disciplina.getAps2());
        stmt.setInt(6, disciplina.getId());
        stmt.setInt(7, disciplina.getAluno_id());
        return stmt.executeUpdate();
    }

    public ArrayList listar() throws SQLException {
        PreparedStatement stmt = banco.prepareStatement("SELECT * FROM disciplina");
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        ResultSet resultado = stmt.executeQuery();
        Disciplina disciplina;
        while(resultado.next()) {
            disciplina = new Disciplina();
            disciplina.setDisciplina(resultado.getString("disciplina"));
            disciplina.setAv1(Float.parseFloat(resultado.getString("Av1")));
            disciplina.setAv2(Float.parseFloat(resultado.getString("Av2")));
            disciplina.setAv3(Float.parseFloat(resultado.getString("Av3")));
            disciplina.setAps1(Float.parseFloat(resultado.getString("Aps1")));
            disciplina.setAps2(Float.parseFloat(resultado.getString("Aps2")));
            disciplina.setId(Integer.parseInt(resultado.getString("id")));
            disciplina.setAluno_id(Integer.parseInt(resultado.getString("aluno_id")));
            disciplina.setProfessor_id(Integer.parseInt(resultado.getString("professor_id")));
            disciplinas.add(disciplina);
        }
        return disciplinas;
    }
    
    public ArrayList joinDisciplinaAluno(Integer id) throws SQLException {
        PreparedStatement stmt = banco.prepareStatement("SELECT * FROM disciplina d join aluno a on d.aluno_id = a.id where a.id=?");
        stmt.setInt(1, id);
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        ResultSet resultado = stmt.executeQuery();
        Disciplina disciplina;
        Aluno aluno;
        while(resultado.next()) {
            disciplina = new Disciplina();
            aluno = new Aluno();
            aluno.setNome(resultado.getString("nome"));
            disciplina.setDisciplina(resultado.getString("disciplina"));
            disciplina.setAv1(Float.parseFloat(resultado.getString("Av1")));
            disciplina.setAv2(Float.parseFloat(resultado.getString("Av2")));
            disciplina.setAv3(Float.parseFloat(resultado.getString("Av3")));
            disciplina.setAps1(Float.parseFloat(resultado.getString("Aps1")));
            disciplina.setAps2(Float.parseFloat(resultado.getString("Aps2")));
            disciplina.setId(Integer.parseInt(resultado.getString("id")));
            disciplina.setAluno(aluno);
            disciplina.setAluno_id(Integer.parseInt(resultado.getString("aluno_id")));
            disciplina.setProfessor_id(Integer.parseInt(resultado.getString("professor_id")));
            disciplinas.add(disciplina);
        }
        return disciplinas;
    }
}
