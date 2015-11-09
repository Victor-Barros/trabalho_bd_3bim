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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Endereco;

/**
 *
 * @author victor_barros
 */
public class EnderecoDAO {
    public Endereco findById(int id) {
        Connection conexao = new Conexao().getConexao();
        Endereco endereco = null;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM enderecos WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            endereco = new Endereco(resultSet.getInt("id"), resultSet.getString("cidade"), resultSet.getString("estado"), resultSet.getString("rua"), resultSet.getInt("numero"), resultSet.getString("complemento"), resultSet.getString("bairro"), resultSet.getInt("cep"));
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return endereco;
    }

    public void insert(Endereco endereco) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO enderecos(cep, estado, cidade, bairro, rua, numero, complemento) VALUES (?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, endereco.getCep());
            preparedStatement.setString(2, endereco.getEstado());
            preparedStatement.setString(3, endereco.getCidade());
            preparedStatement.setString(4, endereco.getBairro());
            preparedStatement.setString(5, endereco.getRua());
            preparedStatement.setInt(6, endereco.getNumero());
            preparedStatement.setString(7, endereco.getComplemento());
            preparedStatement.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Endereco endereco) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE enderecos SET cep=?, estado=?, cidade=?, bairro=?, rua=?, numero=?, complemento=? WHERE id = ?;");
            preparedStatement.setInt(1, endereco.getCep());
            preparedStatement.setString(2, endereco.getEstado());
            preparedStatement.setString(3, endereco.getCidade());
            preparedStatement.setString(4, endereco.getBairro());
            preparedStatement.setString(5, endereco.getRua());
            preparedStatement.setInt(6, endereco.getNumero());
            preparedStatement.setString(7, endereco.getComplemento());
            preparedStatement.setInt(8, endereco.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        }   catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Endereco endereco) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("DELETE enderecos WHERE id = ?;");
            preparedStatement.setInt(1, endereco.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        }   catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
