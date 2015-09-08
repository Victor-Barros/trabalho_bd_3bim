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
import java.sql.Date;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Anotacao;

/**
 *
 * @author victor_barros
 */
public class TrashDAO {

    public ArrayList<Anotacao> list() {
        ArrayList<Anotacao> anotacoes = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        try {
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM trash;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            anotacoes.add(new Anotacao(resultSet.getInt("id"), resultSet.getString("titulo"), resultSet.getString("descricao"), Color.decode(resultSet.getString("cor")), resultSet.getDate("created"), resultSet.getDate("edited")));
        }
        preparedStatement.close();
        conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrashDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return anotacoes;
    }

    public void empty() {
        Connection conexao = new Conexao().getConexao();
        try {
            Statement stmt = conexao.createStatement();
            stmt.executeUpdate("TRUNCATE trash");
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrashDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void delete(Anotacao anotacao) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("DELETE FROM trash WHERE id = ?");
            preparedStatement.setInt(1, anotacao.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrashDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Anotacao findById(int id) {
        Anotacao anotacao =  null;
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM trash WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            anotacao = new Anotacao(resultSet.getInt("id"), resultSet.getString("titulo"), resultSet.getString("descricao"), Color.decode(resultSet.getString("cor")), resultSet.getDate("created"), resultSet.getDate("edited"));
        } catch (SQLException ex) {
            Logger.getLogger(TrashDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return anotacao;
    }

}
