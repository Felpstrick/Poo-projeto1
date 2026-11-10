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
import model.bean.Distribuidora;

/**
 *
 * @author Aluno
 */
public class DistribuidoraDAO {
    public void create(Distribuidora e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "INSERT INTO distribuidora(nome, pais, site) VALUES(?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getPais());
            stmt.setString(3, e.getSite());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Distribuidora cadastrada com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar distribuidora. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
    
    public ArrayList<Distribuidora> read(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Distribuidora> listaDistribuidoras = new ArrayList();
        
        try{
            String query = "SELECT * FROM distribuidora";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Distribuidora e = new Distribuidora();
                e.setId(rs.getInt("id_distribuidora"));
                e.setNome(rs.getString("nome"));
                e.setPais(rs.getString("pais"));
                e.setSite(rs.getString("site"));
                
                listaDistribuidoras.add(e);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao buscar distribuidoras. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt, rs);
                    
        }
            
        return listaDistribuidoras;
    }
    
    public Distribuidora read(int id){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        try{
            String query = "SELECT * FROM distribuidora WHERE id_distribuidora = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                Distribuidora e = new Distribuidora();
                e.setId(rs.getInt("id_distribuidora"));
                e.setNome(rs.getString("nome"));
                e.setPais(rs.getString("pais"));
                e.setSite(rs.getString("site"));
                
                return e;
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao buscar distribuidora. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt, rs);
                    
        }
            
        return null;
    }
    
    public void update(Distribuidora e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "UPDATE distribuidora SET nome = ?, pais = ?, site = ? WHERE id_distribuidora = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getPais());
            stmt.setString(3, e.getSite());
            stmt.setInt(4, e.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Distribuidora atualizado com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao editar distribuidora. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
    
    public void destroy(Distribuidora e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "DELETE FROM distribuidora WHERE id_distribuidora = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, e.getId());  
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Distribuidora deletado com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao deletar distribuidora. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
    
}
