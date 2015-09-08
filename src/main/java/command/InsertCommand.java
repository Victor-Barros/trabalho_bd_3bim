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
public class InsertCommand extends Command {

    public InsertCommand(Request request, Response response) {
        super(request, response);
        request.splat();
        Anotacao anotacao = new Anotacao();
        anotacao.setTitulo(request.queryParams("titulo").trim());
        anotacao.setDescricao(request.queryParams("descricao").trim());
        anotacao.setCor(Color.decode(request.queryParams("cor")));
        if (anotacao.getTitulo() == "") {
            map.put("message", "Anotações sem título não são permitidas");
        } else {
            new AnotacaoDAO().insert(anotacao);
            response.redirect("/");
        }
    }
}
