/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.PessoaDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Modelo;
import model.Pessoa;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class InsertCommand extends Command {

    public InsertCommand(Request request, Response response) {
        super(request, response);     
        request.splat();
        //QueryParamsMap q = request.queryMap("pessoa");
        //q.get("nome").
        new PessoaDAO().insert(new Pessoa(request.queryParams("nome"), request.queryParams("sobrenome")));
        map.put("message", "Voce acaba de inserir o usuario: " + request.queryParams("nome") + " " + request.queryParams("sobrenome"));
    }    
    

}
