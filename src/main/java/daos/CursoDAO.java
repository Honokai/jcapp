/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.consoleapp.BancoConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Curso;
import java.sql.ResultSet;

/**
 *
 * @author eff
 */
public class CursoDAO {
    
    Connection banco;
    
     public CursoDAO() throws ClassNotFoundException, SQLException {
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
     * @param curso
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Integer inserir(Curso curso) throws ClassNotFoundException, SQLException {
        PreparedStatement stmt = banco.prepareStatement("INSERT INTO "
                + "`universo_supremo`.`curso`(`curso`) "
                + "VALUES"
                + " (?)");
        stmt.setString(1, curso.getCurso());
        
        return stmt.executeUpdate();
    }
     
    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList listar() throws SQLException {
        PreparedStatement stmt = banco.prepareStatement("SELECT * FROM curso");
        ArrayList<Curso> cursos = new ArrayList<>();
        ResultSet resultado = stmt.executeQuery();
        Curso curso;
        while(resultado.next()) {
            curso = new Curso();
            curso.setCurso(resultado.getString("curso"));
            curso.setId(Integer.parseInt(resultado.getString("id")));
            cursos.add(curso);
        }
        return cursos;
   }  
   
   public Integer atualizarInfo(Curso curso) throws ClassNotFoundException, SQLException {
        PreparedStatement stmt = banco.prepareStatement("Update curso set curso=? WHERE id = ?");
        stmt.setString(1, curso.getCurso());
        stmt.setInt(2, curso.getId());
        return stmt.executeUpdate();
   }
   
   public Integer apagarPeloNome(Curso curso) throws ClassNotFoundException, SQLException {
        try {
            PreparedStatement stmt = banco.prepareStatement("DELETE FROM curso WHERE curso like ?");
            stmt.setString(1, "%"+curso.getCurso()+"%");
            return stmt.executeUpdate();
        } catch(SQLException e) {
            if(e.getMessage().contains("Cannot delete or update a parent row: a foreign key constraint fails")){
                System.out.println("\n\n-------------ALERTA----------------");
                System.out.println("Você tentou apagar um curso que há\nalunos matriculados, antes de excluí-lo,\nfaça a mudança de CURSO para então excluir.");
                System.out.println("-------------ALERTA----------------\n\n");
                return -1;
            }
            return 0;
        }
        
    }
   
}
