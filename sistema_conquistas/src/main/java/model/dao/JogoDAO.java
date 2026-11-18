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
import model.bean.Jogo;
import model.bean.Estudio;

/**
 *
 * @author Aluno
 */
public class JogoDAO {
    public void create(Jogo e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "INSERT INTO jogo(titulo, genero, data_lancamento, id_estudio, id_distribuidora) VALUES(?,?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, e.getTitulo());
            stmt.setString(2, e.getGenero());
            stmt.setDate(3, e.getData_lancamento());
            stmt.setInt(4, e.getEstudio().getId());
            stmt.setInt(5, e.getDistribuidora().getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Jogo cadastrado com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar jogo. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
    
    public ArrayList<Jogo> read(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Jogo> listaJogos = new ArrayList();
        
        try{
            String query = "SELECT * FROM jogo";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Jogo e = new Jogo();
                e.setId(rs.getInt("id_jogo"));
                e.setTitulo(rs.getString("titulo"));
                e.setGenero(rs.getString("genero"));
                e.setData_lancamento(rs.getDate("data_lancamento"));
                
                EstudioDAO daoEstudio = new EstudioDAO();
                Estudio es = daoEstudio.read(rs.getInt("id_estudio"));
                e.setEstudio(es);
                
                e.setDistribuidora(new DistribuidoraDAO().read(rs.getInt("id_distribuidora")));
                
                
                listaJogos.add(e);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao buscar jogos. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt, rs);
                    
        }
            
        return listaJogos;
    }
    
    public Jogo read(int id){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        try{
            String query = "SELECT * FROM jogo WHERE id_jogo = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                Jogo e = new Jogo();
                e.setId(rs.getInt("id_jogo"));
                e.setTitulo(rs.getString("titulo"));
                e.setGenero(rs.getString("genero"));
                e.setData_lancamento(rs.getDate("data_lancamento"));
                
                return e;
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao buscar jogo. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt, rs);
                    
        }
            
        return null;
    }
    
    public void update(Jogo e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "UPDATE jogo SET titulo = ?, genero = ?, data_lancamento = ? WHERE id_jogo = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, e.getTitulo());
            stmt.setString(2, e.getGenero());
            stmt.setDate(3, e.getData_lancamento());
            stmt.setInt(4, e.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Jogo atualizado com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao editar jogo. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
    
    public void destroy(Jogo e){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "DELETE FROM jogo WHERE id_jogo = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, e.getId());  
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Jogo deletado com sucesso!");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha ao deletar jogo. Erro: " + ex.getMessage());
        }
        finally{
            Conexao.fecharConexao(con, stmt);
                    
        }
    }
    
}
