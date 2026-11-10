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
import model.bean.Usuario;

/**
 *
 * @author Aluno
 */
public class UsuarioDAO {
    public void create(Usuario e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "INSERT INTO usuario(nickname, email) VALUES(?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, e.getNickname());
            stmt.setString(2, e.getEmail());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar usuario. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
    
    public ArrayList<Usuario> read(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Usuario> listaUsuarios = new ArrayList();
        
        try{
            String query = "SELECT * FROM usuario";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Usuario e = new Usuario();
                e.setId(rs.getInt("id_usuario"));
                e.setNickname(rs.getString("nickname"));
                e.setEmail(rs.getString("email"));
                
                listaUsuarios.add(e);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao buscar usuarios. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt, rs);
                    
        }
            
        return listaUsuarios;
    }
    
    public Usuario read(int id){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        try{
            String query = "SELECT * FROM usuario WHERE id_usuario = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                Usuario e = new Usuario();
                e.setId(rs.getInt("id_usuario"));
                e.setNickname(rs.getString("nickname"));
                e.setEmail(rs.getString("email"));
                
                return e;
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao buscar usuario. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt, rs);
                    
        }
            
        return null;
    }
    
    public void update(Usuario e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "UPDATE usuario SET nickname = ?, email = ? WHERE id_usuario = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, e.getNickname());
            stmt.setString(2, e.getEmail());
            stmt.setInt(4, e.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao editar usuario. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
    
    public void destroy(Usuario e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "DELETE FROM usuario WHERE id_usuario = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, e.getId());  
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario deletado com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao deletar usuario. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
    
}
