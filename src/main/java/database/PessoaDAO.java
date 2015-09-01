package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pessoa;

/**
 *
 * @author iapereira
 */
public class PessoaDAO {

    public PessoaDAO() {
    }

    public void delete(int id) throws SQLException {
        Connection conexao = new Conexao().getConexao();
        try (PreparedStatement preparedStatement = conexao.prepareStatement("DELETE FROM pessoa WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        conexao.close();
    }

    public void insert(Pessoa pessoa) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;
        try {
            conexao = new Conexao().getConexao();
            preparedStatement = conexao.prepareStatement("INSERT INTO pessoa (nome, sobrenome) VALUES (?, ?);");
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getSobrenome());
            System.out.println("LinhasAfetadas:" + preparedStatement.executeUpdate()); // LINHAS AFETADAS
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            if (preparedStatement != null) {
                try {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conexao != null) {
                try {
                    if (!conexao.isClosed()) {
                        conexao.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    /*
     static abstract class Command {
     void execute(Connection c) {
     exec
     }
     }    
     static void execute(Command com) {
     abrir
     comando
     fechar
     }*/

    // e assim sucessivamente...
    public ArrayList<Pessoa> select() throws SQLException {
        ArrayList<Pessoa> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM pessoa;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            //System.out.println(resultSet.getString("nome") + "," + resultSet.getString("sobrenome"));
            vet.add(new Pessoa(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("sobrenome")));
        }
        preparedStatement.close();
        //System.out.println("LinhasAfetadas:" + preparedStatement.executeUpdate()); // LINHAS AFETADAS
        conexao.close();
        return vet;
    }

    public Pessoa selectById(int id) throws SQLException {
        Pessoa pessoa;
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM pessoa WHERE id = ?;")) {
            System.out.println("id:"+id);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            pessoa = new Pessoa();
            if (resultSet.next()) {
                System.out.println("Sim");
                pessoa = new Pessoa(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("sobrenome"));
            }else {
                System.out.println("nao");
            }
        }
        return pessoa;
    }

    public void update(Pessoa pessoa) throws SQLException {
         //Pessoa pessoa;
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE pessoa SET nome = ? , sobrenome = ? WHERE id = ?;")) {
            preparedStatement.setString(1, pessoa.getNome() );
            preparedStatement.setString(2, pessoa.getSobrenome() );
            preparedStatement.setInt(3, pessoa.getId() );
            preparedStatement.executeUpdate();
            //pessoa = new Pessoa();
            //if (resultSet.next()) {
              //  pessoa = new Pessoa(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("sobrenome"));
            //}
        }
        //return pessoa;
        
    }
}
