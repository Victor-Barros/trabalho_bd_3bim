package command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import model.Anotacao;
import database.TrashDAO;
import database.AnotacaoDAO;

/**
 *
 * @author victor_barros
 */
public class RestoreTrashCommand extends Command {

    public RestoreTrashCommand(Request request, Response response) {
        super(request, response);
        Anotacao anotacao = new Anotacao();
        anotacao = new TrashDAO().findById(Integer.parseInt(request.params(":id")));
        new AnotacaoDAO().insert(anotacao);
        new TrashDAO().delete(anotacao);
        response.redirect("/screen_trash");
    }
}