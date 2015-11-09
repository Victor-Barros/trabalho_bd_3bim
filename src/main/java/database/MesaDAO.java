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
import model.Mesa;

/**
 *
 * @author victor_barros
 */
public class MesaDAO {
    public ArrayList<Mesa> list() {
        ArrayList<Mesa> mesas = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM mesas ORDER BY id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mesas.add(new Mesa(resultSet.getInt("id"), resultSet.getInt("numero"), resultSet.getInt("nlugares"), resultSet.getBoolean("status")));
            }
            preparedStatement.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(MesaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return mesas;
    }
    
    public Mesa findById(int id) {
        Connection conexao = new Conexao().getConexao();
        Mesa mesa = null;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM mesas WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            mesa = new Mesa(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("descricao"), resultSet.getDouble("valor"), resultSet.getBoolean("status"));
        } catch (SQLException ex) {
            Logger.getLogger(MesaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mesa;
    }

    public void insert(Mesa mesa) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO mesas(nome, descricao, valor) VALUES (?, ?, ?);");
            preparedStatement.setString(1, mesa.getNome());
            preparedStatement.setString(2, mesa.getDescricao());
            preparedStatement.setDouble(3, mesa.getValor());
            preparedStatement.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(MesaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Mesa mesa) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE mesas SET nome=?, descricao=?, valor=? WHERE id = ?;");
            preparedStatement.setString(1, mesa.getNome());
            preparedStatement.setString(2, mesa.getDescricao());
            preparedStatement.setDouble(3, mesa.getValor());
            preparedStatement.setInt(4, mesa.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        }   catch (SQLException ex) {
            Logger.getLogger(MesaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Mesa mesa) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE mesas SET status=false WHERE id = ?;");
            preparedStatement.setInt(1, mesa.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        }   catch (SQLException ex) {
            Logger.getLogger(MesaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
