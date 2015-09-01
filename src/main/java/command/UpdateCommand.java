package command;

import database.PessoaDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pessoa;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class UpdateCommand extends Command {

    public UpdateCommand(Request request, Response response) {
        super(request, response);
        try {
            new PessoaDAO().update(new Pessoa(Integer.parseInt(request.queryParams("id")), request.queryParams("nome"), request.queryParams("sobrenome")));
            map.put("message", "Voce acaba de editar o usuario: " + request.queryParams("id"));
        } catch (SQLException ex) {
            Logger.getLogger(UpdateCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
