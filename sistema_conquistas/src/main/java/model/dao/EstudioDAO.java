/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public ArrayList<Estudio> read(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Estudio> listaEstudios = new ArrayList();
        
        try{
            String query = "SELECT * FROM estudio";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Estudio e = new Estudio();
                e.setId(rs.getInt("id_estudio"));
                e.setNome(rs.getString("nome"));
                e.setPais(rs.getString("pais"));
                e.setAnoFundacao(rs.getInt("ano_fundacao"));
                
                listaEstudios.add(e);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao buscar estudios. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt, rs);
                    
        }
            
        return listaEstudios;
    }
    
    public Estudio read(int id){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        try{
            String query = "SELECT * FROM estudio WHERE id_estudio = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                Estudio e = new Estudio();
                e.setId(rs.getInt("id_estudio"));
                e.setNome(rs.getString("nome"));
                e.setPais(rs.getString("pais"));
                e.setAnoFundacao(rs.getInt("ano_fundacao"));
                
                return e;
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao buscar estudio. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt, rs);
                    
        }
            
        return null;
    }
    
    public void update(Estudio e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "UPDATE estudio SET nome = ?, pais = ?, ano_fundacao = ? WHERE id_estudio = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getPais());
            stmt.setInt(3, e.getAnoFundacao());
            stmt.setInt(4, e.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Estudio atualizado com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao editar estudio. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
    
    public void destroy(Estudio e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "DELETE FROM estudio WHERE id_estudio = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, e.getId());  
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Estudio deletado com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao deletar estudio. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
    
}
