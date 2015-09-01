
package command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import model.Anotacao;
import database.AnotacaoDAO;

/**
 *
 * @author victor_barros
 */
public class ScreenUpdateCommand extends Command {

    public ScreenUpdateCommand(Request request, Response response) {
        super(request, response);
         Anotacao anotacao;
        try {
            anotacao = new AnotacaoDAO().findById(Integer.parseInt(request.params(":id")));
            map.put("titulo", anotacao.getTitulo());
            map.put("descricao", anotacao.getDescricao());
            map.put("cor", anotacao.getCor());
            map.put("created", anotacao.getCreated());
            map.put("edited", anotacao.getEdited());

        } catch (SQLException sqle) {
            Logger.getLogger(ScreenUpdateCommand.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

}
