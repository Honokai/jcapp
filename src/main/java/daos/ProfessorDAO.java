/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.consoleapp.BancoConexao;
import entidades.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eff
 */
public class ProfessorDAO {
    Connection banco;
    
    public ProfessorDAO() throws ClassNotFoundException, SQLException {
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
    
    public Integer inserir(Professor professor) throws ClassNotFoundException, SQLException {
        try{
            PreparedStatement stmt = banco.prepareStatement("INSERT INTO "
                + "`universo_supremo`.`professor`(`professor`,`email`) "
                + "VALUES"
                + " (?,?)");
            stmt.setString(1, professor.getProfessor());
            stmt.setString(2, professor.getEmail());
            return stmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    }
    
    public Integer apagarPeloNome(Professor professor) throws ClassNotFoundException, SQLException {
        PreparedStatement stmt = banco.prepareStatement("DELETE FROM professor WHERE professor like ?");
        stmt.setString(1, "%"+professor.getProfessor()+"%");
        return stmt.executeUpdate();
    }
    
    public Integer atualizarInfo(Professor professor) throws ClassNotFoundException, SQLException  {
        try {
            PreparedStatement stmt = banco.prepareStatement("Update professor set email=? WHERE professor like ? and id = ?");
            stmt.setString(1, professor.getEmail());
            stmt.setString(2,"%"+professor.getProfessor()+"%");
            stmt.setInt(3, professor.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Ocorreu o seguinte erro: "+e.toString()+" Você pode ter informado um valor que não existe no banco.");
            return -1;
        }
    }
    
    public ArrayList listar() throws SQLException {
        PreparedStatement stmt = banco.prepareStatement("SELECT * FROM professor");
        ArrayList<Professor> professores = new ArrayList<>();
        ResultSet resultado = stmt.executeQuery();
        Professor professor;
        while(resultado.next()) {
            professor = new Professor();
            professor.setProfessor(resultado.getString("professor"));
            professor.setEmail(resultado.getString("email"));
            professor.setId(Integer.parseInt(resultado.getString("id")));
            professores.add(professor);
        }
        return professores;
    }
    
}
