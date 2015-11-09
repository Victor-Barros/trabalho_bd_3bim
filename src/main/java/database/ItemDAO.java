/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;

/**
 *
 * @author victor_barros
 */
public class ItemDAO {
    public ArrayList<Item> list() {
        ArrayList<Item> itens = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM itens ORDER BY id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                itens.add(new Item(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("descricao"), resultSet.getDouble("valor"), resultSet.getBoolean("status")));
            }
            preparedStatement.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return itens;
    }
    
    public Item findById(int id) {
        Connection conexao = new Conexao().getConexao();
        Item item = null;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM itens WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            item = new Item(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("descricao"), resultSet.getDouble("valor"), resultSet.getBoolean("status"));
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    public void insert(Item item) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO itens(nome, descricao, valor) VALUES (?, ?, ?);");
            preparedStatement.setString(1, item.getNome());
            preparedStatement.setString(2, item.getDescricao());
            preparedStatement.setDouble(3, item.getValor());
            preparedStatement.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Item item) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE itens SET nome=?, descricao=?, valor=? WHERE id = ?;");
            preparedStatement.setString(1, item.getNome());
            preparedStatement.setString(2, item.getDescricao());
            preparedStatement.setDouble(3, item.getValor());
            preparedStatement.setInt(4, item.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        }   catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Item item) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE itens SET status=false WHERE id = ?;");
            preparedStatement.setInt(1, item.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        }   catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
