/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Anotacao;

/**
 *
 * @author victor_barros
 */
public class AnotacaoDAO {

    public ArrayList<Anotacao> list() {
        ArrayList<Anotacao> anotacoes = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM anotacao ORDER BY created;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                anotacoes.add(new Anotacao(resultSet.getInt("id"), resultSet.getString("titulo"), resultSet.getString("descricao"), Color.decode(resultSet.getString("cor")), resultSet.getDate("created"), resultSet.getDate("edited")));
            }
            preparedStatement.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnotacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return anotacoes;
    }

    public void insert(Anotacao anotacao) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO anotacao (titulo, descricao, created, cor) VALUES (?, ?, now(), ?);");
            preparedStatement.setString(1, anotacao.getTitulo());
            preparedStatement.setString(2, anotacao.getDescricao());
            preparedStatement.setString(3, anotacao.getCorHex());
            preparedStatement.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnotacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Anotacao anotacao) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("DELETE FROM anotacao WHERE id = ?");
            preparedStatement.setInt(1, anotacao.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnotacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Anotacao findById(int id) {
        Connection conexao = new Conexao().getConexao();
        Anotacao anotacao = null;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM anotacao WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            anotacao = new Anotacao(resultSet.getInt("id"), resultSet.getString("titulo"), resultSet.getString("descricao"), Color.decode(resultSet.getString("cor")), resultSet.getDate("created"), resultSet.getDate("edited"));
        } catch (SQLException ex) {
            Logger.getLogger(AnotacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return anotacao;
    }

    public void update(Anotacao anotacao) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE anotacao SET titulo = ?, descricao = ?, cor = ?, edited = now() WHERE id = ?;");
            preparedStatement.setString(1, anotacao.getTitulo());
            preparedStatement.setString(2, anotacao.getDescricao());
            preparedStatement.setString(3, anotacao.getCorHex());
            preparedStatement.setInt(4, anotacao.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        }   catch (SQLException ex) {
            Logger.getLogger(AnotacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
