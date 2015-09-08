/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import database.AnotacaoDAO;
import model.Anotacao;

/**
 *
 * @author victor_barros
 */
public class DeleteCommand extends Command {

    public DeleteCommand(Request request, Response response) {
        super(request, response);
        Anotacao anotacao = new Anotacao();
        anotacao.setId(Integer.parseInt(request.params(":id")));
        new AnotacaoDAO().delete(anotacao);
        response.redirect("/");
    }

}
