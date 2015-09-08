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
import java.awt.Color;

/**
 *
 * @author victor_barros
 */
public class UpdateCommand extends Command {

    public UpdateCommand(Request request, Response response) {
        super(request, response);
        Anotacao anotacao = new Anotacao();
        anotacao.setId(Integer.parseInt(request.queryParams("id")));
        anotacao.setTitulo(request.queryParams("titulo").trim());
        anotacao.setDescricao(request.queryParams("descricao").trim());
        anotacao.setCor(Color.decode(request.queryParams("cor")));
        if (anotacao.getTitulo() == "") {
            map.put("message", "Anotações sem título não são permitidas");
        } else {
            new AnotacaoDAO().update(anotacao);
            response.redirect("/");
        }
    }
}
