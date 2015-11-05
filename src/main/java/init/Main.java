package init;

import controller.ItemController;
import java.sql.SQLException;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import static spark.Spark.*;
import spark.SparkBase;
import spark.TemplateViewRoute;
import spark.template.mustache.MustacheTemplateEngine;

/**
 * *
 *
 * @author victor_barros
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        
        
        staticFileLocation("/html"); 
        
        // itens
        
        get("/itens", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ItemController(request, response).list(), "cardapio.html");
            }
        }, new MustacheTemplateEngine());
        
        // delete
        /*get("/delete/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new DeleteClassificadoCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());

        // insert          
        get("/screen_insert", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Command(request, response), "screen_insert.html");
            }
        }, new MustacheTemplateEngine());

        post("/insert/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new InsertClassificadoCommand(request, response).getMap(), "");
            }
        }, new MustacheTemplateEngine());
        */
    }
}
