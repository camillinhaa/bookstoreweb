/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.camilla.bookstoreweb.model.dao;

import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.camilla.bookstoreweb.model.bean.PojoUser;

/**
 *
 * @author devsys-a
 */
public class UserDAO {
    private static final String SQL_INSERT = "INSERT INTO user (" 
            + "email, password, fullname) "
            +"VALUES (?, ?, ?)";
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM user";
    private static final String SQL_SELECT_ID = "SELECT * FROM user "
            +"WHERE id = ?";
    
    private static final String SQL_UPDATE = "UPDATE user SET email = ?,"
            +"password = ?, fullname = ?"
            +"WHERE id = ?";
    
    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ?"; 
    
    private static final String SQL_SELECT_AUTHENTIC = "SELECT fullname FROM user WHERE email = ? AND password = ?";

 public void create(PojoUser u){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null; 
        
        try{
            stmt = conn.prepareCall(SQL_INSERT);
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getFullname());
        
            
           /**
            * Executa a query
            */
            
           int auxRetorno = stmt.executeUpdate();
           
           Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null,
                   "Inclusão: " + auxRetorno);
           
        }catch(SQLException sQlException){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, sQlException);
            
        }finally{
            /**
             * Encerra a conexão com o banco e o statement.
             */     
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    /**
     * Retorna todos os regsros da tabela produto
     * @return
     */
    
    public List<PojoUser> getResults(){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PojoUser u = null;
        List<PojoUser> listaPojoUser = null; 
        
        try{
            /**
             * Prepara a string de SELECT e executa a query.
             */
            
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            u.setId(rs.getInt("id"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setFullname(rs.getString("fullname"));
            rs = stmt.executeQuery();
            
            /**
             * Carrega os dados do ResultSet rs, converte em Produto e
             * adiciona na lista de retorno.
             */ 
            
            listaPojoUser = new ArrayList<>();
            
            while (rs.next()){
                u = new PojoUser();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
             
                listaPojoUser.add(u);
            }
        }catch (SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPojoUser;
    }
    
    /**
     * Retorna um produto da tabela produto.
     * @param id IDentificação do prduto
     * @return
     */
    
    public PojoUser getResultById(int id){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PojoUser b = null;
        
        try{
          stmt = conn.prepareStatement(SQL_SELECT_ID);
          stmt.setInt(1, id);
          rs = stmt.executeQuery();
        
            if (rs.next()){
                b = new PojoUser();
                b.setId(rs.getInt("id"));
                b.setEmail(rs.getString("email"));
                b.setPassword(rs.getString("password"));
                b.setFullname(rs.getString("fullname"));
                
            }
        }catch(SQLException sQLException){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }finally{
            /**
             * Encerra a conxão com o banco e o statement.
             */
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        
        return b;
    } 
    
    /**
     * Atualiza um registro na tbela produto.
     * @param b PojoUser a ser atualizado.
     */
    
    public void update (PojoUser b){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, b.getId());
            stmt.setString(2, b.getEmail());
            stmt.setString(3, b.getPassword());
            stmt.setString(4, b.getFullname());
            
            
            /**
             * Executa a query
             */
            
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null, "Update: " + auxRetorno);
            
        }catch (SQLException sQLException){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, sQLException);
            
        }finally{
            /**
             * Encerra a conexão com o banco e o statement.
             */
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    /**
     * Exclui um produto com base do ID fornecido.
     * @param id IDentificação do produto.
     */
    
    public void delete(int id){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            
            /**
             * Executa a query
             */
            
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null, "Delete: " + auxRetorno);
            
        }catch(SQLException sQLException){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, sQLException);
            
        }finally{
        /**
         * Encerra a conexão com o banco e o statement.
         */
        MySQLConnection.closeConnection(conn, stmt);
        }
        
    } 
    
      public PojoUser checkLogin (String email, String password){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PojoUser u = null;
        
        try {
            stmt = conn.prepareStatement(SQL_SELECT_AUTHENTIC);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
        
            
            if (rs.next()) {
               u = new PojoUser();
               u.setFullname(rs.getString("fullname"));
               u.setEmail(rs.getString("email"));
               u.setPassword(rs.getString("password"));                
            }
            
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return u;
    } 
    
  
}