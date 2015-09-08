/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import database.AnotacaoDAO;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Anotacao;

/**
 *
 * @author victor_barros
 */
public class CopyCommand extends Command {

    public CopyCommand(Request request, Response response) {
        super(request, response);
        request.splat();
        Anotacao anotacao = new Anotacao();
        anotacao.setId(Integer.parseInt(request.params(":id")));
        anotacao = new AnotacaoDAO().findById(anotacao.getId());
        new AnotacaoDAO().insert(anotacao);
        response.redirect("/");
    }
}
