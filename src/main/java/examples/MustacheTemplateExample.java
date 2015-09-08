package examples;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Anotacao;
import database.AnotacaoDAO;

import spark.ModelAndView;

import static spark.Spark.get;
import spark.template.mustache.MustacheTemplateEngine;

public class MustacheTemplateExample {

    public static void main(String[] args) throws SQLException {
        
        Map map = new HashMap();
        
       ArrayList<Anotacao> anotacoes = new AnotacaoDAO().list();   
       map.put("anotacoes", anotacoes);
        
               
    //    get("/mustache", (rq, rs) -> new ModelAndView(map, "mustache.html"), new MustacheTemplateEngine());
    }
}
