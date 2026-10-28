/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Estudio;

/**
 *
 * @author Aluno
 */
public class EstudioDAO {
    public void create(Estudio e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "INSERT INTO estudio(nome, pais, ano_fundacao) VALUES(?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getPais());
            stmt.setInt(3, e.getAnoFundacao());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Estudio cadastrado com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar estudio. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
}
