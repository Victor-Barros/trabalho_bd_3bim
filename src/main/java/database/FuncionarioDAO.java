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
import model.Funcionario;

/**
 *
 * @author victor_barros
 */
public class FuncionarioDAO {
    public ArrayList<Funcionario> list() {
        ArrayList<Funcionario> funcionarios = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM funcionarios ORDER BY id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                funcionarios.add(new Funcionario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("sobrenome"), resultSet.getString("cpf"), resultSet.getString("telefone"), new EnderecoDAO().findById(resultSet.getInt("endereco_id")), resultSet.getString("email"), resultSet.getDate("nascimento"), resultSet.getDouble("salario"), resultSet.getBoolean("gerente"), resultSet.getString("senha"), resultSet.getBoolean("status"), new Date(resultSet.getTimestamp("created").getTime()), new Date(resultSet.getTimestamp("edited").getTime())));
            }
            preparedStatement.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return funcionarios;
    }
    
    public Funcionario findById(int id) {
        Connection conexao = new Conexao().getConexao();
        Funcionario funcionario = null;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM funcionarios INNER JOIN enderecos on (endereco_id = enderecos.id) WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            funcionario = new Funcionario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("sobrenome"), resultSet.getString("cpf"), resultSet.getString("telefone"), new EnderecoDAO().findById(resultSet.getInt("endereco_id")), resultSet.getString("email"), resultSet.getDate("nascimento"), resultSet.getDouble("salario"), resultSet.getBoolean("gerente"), resultSet.getString("senha"), resultSet.getBoolean("status"), new Date(resultSet.getTimestamp("created").getTime()), new Date(resultSet.getTimestamp("edited").getTime()));
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionario;
    }

    public void insert(Funcionario funcionario) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO funcionarios(nome, sobrenome, telefone, endereco_id, salario, email, cpf, nascimento, senha, gerente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, funcionario.getNome());            
            preparedStatement.setString(2, funcionario.getSobrenome());
            preparedStatement.setString(3, funcionario.getTelefone());
            preparedStatement.setInt(4, funcionario.getEndereco().getId());
            preparedStatement.setDouble(5, funcionario.getSalario());
            preparedStatement.setString(6, funcionario.getEmail());            
            preparedStatement.setString(7, funcionario.getCpf());
            preparedStatement.setDate(8, funcionario.getNascimento());
            preparedStatement.setString(9, funcionario.getSenha());
            preparedStatement.setBoolean(10, funcionario.isGerente());
            preparedStatement.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Funcionario funcionario) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE funcionarios SET nome=?, sobrenome=?, telefone=?, endereco_id=?, salario=?, email=?, cpf=?, nascimento=?, edited=now(), senha=?, gerente=? WHERE id = ?;");
            preparedStatement.setString(1, funcionario.getNome());            
            preparedStatement.setString(2, funcionario.getSobrenome());
            preparedStatement.setString(3, funcionario.getTelefone());
            preparedStatement.setInt(4, funcionario.getEndereco().getId());
            preparedStatement.setDouble(5, funcionario.getSalario());
            preparedStatement.setString(6, funcionario.getEmail());            
            preparedStatement.setString(7, funcionario.getCpf());
            preparedStatement.setDate(8, funcionario.getNascimento());
            preparedStatement.setString(9, funcionario.getSenha());
            preparedStatement.setBoolean(10, funcionario.isGerente());
            preparedStatement.setInt(11, funcionario.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        }   catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Funcionario funcionario) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE funcionarios SET status=false WHERE id = ?;");
            preparedStatement.setInt(1, funcionario.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        }   catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
