/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import model.Anotacao;
import database.TrashDAO;

/**
 *
 * @author victor_barros
 */
public class ListTrashCommand extends Command {

    public ListTrashCommand(Request request, Response response) {
        super(request, response);
        ArrayList<Anotacao> anotacoes;
        anotacoes = new TrashDAO().list();
        if (anotacoes.size() > 0) {
            map.put("anotacoes", anotacoes);
        }
    }

}
