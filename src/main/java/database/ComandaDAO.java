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
import model.Comanda;
import model.Pedido;

/**
 *
 * @author victor_barros
 */
public class ComandaDAO {

    private int recursive;

    /**
     * Recursive: -1 -> sem n:n 0 -> n:n parcial 1 -> n:n total
     */
    public ComandaDAO(int recursive) {
        if (recursive > 1 || recursive < -1) {
            recursive = 0;
        }
        this.recursive = recursive;
    }

    public ComandaDAO() {
        recursive = 0;
    }

    public ArrayList<Comanda> list() {
        ArrayList<Comanda> comandas = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM comandas ORDER BY id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (recursive == -1) {
                while (resultSet.next()) {
                    comandas.add(new Comanda(resultSet.getInt("id"), resultSet.getBoolean("status"), new MesaDAO().findById(resultSet.getInt("mesa_id")), new ClienteDAO().findById(resultSet.getInt("cliente_id")), 0));
                }
            } else {
                while (resultSet.next()) {
                    comandas.add(new Comanda(resultSet.getInt("id"), resultSet.getBoolean("status"), new MesaDAO().findById(resultSet.getInt("mesa_id")), new ClienteDAO().findById(resultSet.getInt("cliente_id")), 0, findPedidosByComanda(resultSet.getInt("id"))));
                }
            }
            preparedStatement.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comandas;
    }

    public Comanda findById(int id) {
        Connection conexao = new Conexao().getConexao();
        Comanda comanda = null;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM comandas WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (recursive == -1) {
                comanda = new Comanda(resultSet.getInt("id"), resultSet.getBoolean("status"), new MesaDAO().findById(resultSet.getInt("mesa_id")), new ClienteDAO().findById(resultSet.getInt("cliente_id")), 0);
            } else {
                comanda = new Comanda(resultSet.getInt("id"), resultSet.getBoolean("status"), new MesaDAO().findById(resultSet.getInt("mesa_id")), new ClienteDAO().findById(resultSet.getInt("cliente_id")), 0, findPedidosByComanda(resultSet.getInt("id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comanda;
    }

    public void insert(Comanda comanda) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO comandas(cliente_id, mesa_id) VALUES (?, ?);");
            preparedStatement.setInt(1, comanda.getCliente().getId());
            preparedStatement.setInt(2, comanda.getMesa().getId());
            preparedStatement.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Comanda comanda) {
        Connection conexao = new Conexao().getConexao();
        int res;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE comandas SET cliente_id=?, mesa_id=? WHERE id = ?;");
            preparedStatement.setInt(1, comanda.getCliente().getId());
            preparedStatement.setInt(2, comanda.getMesa().getId());
            preparedStatement.setInt(3, comanda.getId());
            res = preparedStatement.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Comanda comanda) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE comandas SET status=false WHERE id = ?;");
            preparedStatement.setInt(1, comanda.getId());
            preparedStatement.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Pedido> findPedidosByComanda(int comanda_id) {
        ArrayList<Pedido> pedidos = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM comandas_itens WHERE comanda_id = ?;");
            preparedStatement.setInt(1, comanda_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (recursive == 0) {
                while (resultSet.next()) {
                    pedidos.add(new Pedido(resultSet.getInt("id"), new ItemDAO().findById(resultSet.getInt("item_id")), new FuncionarioDAO().findById(resultSet.getInt("funcionario_id")), resultSet.getInt("quantidade")));
                }
            } else if (recursive == 1) {
                while (resultSet.next()) {
                    pedidos.add(new Pedido(resultSet.getInt("id"), new ItemDAO().findById(resultSet.getInt("item_id")), new ComandaDAO().findById(resultSet.getInt("comanda_id")), new FuncionarioDAO().findById(resultSet.getInt("funcionario_id")), resultSet.getInt("quantidade")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;
    }

    public void updatePedidosByComanda(Comanda comanda) {
        Connection conexao = new Conexao().getConexao();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("DELETE FROM comandas_itens WHERE comanda_id = ?;");
            preparedStatement.setInt(1, comanda.getId());
            preparedStatement.execute();
            preparedStatement = conexao.prepareStatement("UPDATE OR INSERT INTO comandas_itens(item_id, comanda_id, funcionario_id, quantidade) VALUES (?, ?, ?, ?) MATCHING (item_id, comanda_id);");
            for (int i = 0; i < comanda.getPedidos().size(); i++) {
                preparedStatement.setInt(1, comanda.getPedidos().get(i).getItem().getId());
                preparedStatement.setInt(2, comanda.getId());
                preparedStatement.setInt(3, comanda.getPedidos().get(i).getFuncionario().getId());
                preparedStatement.setInt(4, comanda.getPedidos().get(i).getQuantidade());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getRecursive() {
        return recursive;
    }

    public void setRecursive(int recursive) {
        if (recursive > 1 || recursive < -1) {
            recursive = 0;
        }
        this.recursive = recursive;
    }

}
