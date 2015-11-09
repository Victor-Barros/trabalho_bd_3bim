/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;

/**
 *
 * @author victor_barros
 */
public class ClienteDAO {
    public ArrayList<Cliente> list() {
        ArrayList<Cliente> clientes = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM clientes ORDER BY id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clientes.add(new Cliente(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("sobrenome"), resultSet.getString("cpf"), resultSet.getString("telefone"), new EnderecoDAO().findById(resultSet.getInt("endereco_id")), resultSet.getString("email"), resultSet.getDate("nascimento"), resultSet.getBoolean("status"), new Date(resultSet.getTimestamp("created").getTime()), new Date(resultSet.getTimestamp("edited").getTime())));
            }
            preparedStatement.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return clientes;
    }
    
    public Cliente findById(int id) {
        Connection conexao = new Conexao().getConexao();
        Cliente cliente = null;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM clientes INNER JOIN enderecos on (endereco_id = enderecos.id) WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            cliente = new Cliente(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("sobrenome"), resultSet.getString("cpf"), resultSet.getString("telefone"), new EnderecoDAO().findById(resultSet.getInt("endereco_id")), resultSet.getString("email"), resultSet.getDate("nascimento"), resultSet.getBoolean("status"), new Date(resultSet.getTimestamp("created").getTime()), new Date(resultSet.getTimestamp("edited").getTime()));
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public void insert(Cliente cliente) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO clientes(nome, telefone, endereco_id, email, nascimento, sobrenome, cpf) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getTelefone());
            preparedStatement.setInt(3, cliente.getEndereco().getId());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setDate(5, cliente.getNascimento());
            preparedStatement.setString(6, cliente.getSobrenome());
            preparedStatement.setString(7, cliente.getCpf());
            preparedStatement.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Cliente cliente) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE clientes SET nome=?, telefone=?, endereco_id=?, email=?, nascimento=?, sobrenome=?, edited=now(), cpf=? WHERE id = ?;");
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getTelefone());
            preparedStatement.setInt(3, cliente.getEndereco().getId());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setDate(5, cliente.getNascimento());
            preparedStatement.setString(6, cliente.getSobrenome());
            preparedStatement.setString(7, cliente.getCpf());
            preparedStatement.setInt(8, cliente.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        }   catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Cliente cliente) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE clientes SET status=false WHERE id = ?;");
            preparedStatement.setInt(1, cliente.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        }   catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
