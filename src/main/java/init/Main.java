package init;

import controller.ItemController;
import java.sql.SQLException;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
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
                return new ModelAndView(new ItemController(request, response).list(), "cardapio/index.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/itens/view/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ItemController(request, response).view(), "cardapio/view.html");
            }
        }, new MustacheTemplateEngine());

        get("/itens/insert_form", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ItemController(request, response), "cardapio/form_insert.html");
            }
        }, new MustacheTemplateEngine());

        post("/itens/insert/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                new ItemController(request, response).insert();
                return new ModelAndView(new ItemController(request, response), "");
            }
        }, new MustacheTemplateEngine());
        
        get("/itens/update_form/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ItemController(request, response).screenEdit(), "cardapio/form_update.html");
            }
        }, new MustacheTemplateEngine());

        post("/itens/update/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                new ItemController(request, response).edit();
                return new ModelAndView(new ItemController(request, response), "");
            }
        }, new MustacheTemplateEngine());
        
        post("/itens/delete/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                new ItemController(request, response).delete();
                return new ModelAndView(new ItemController(request, response), "");
            }
        }, new MustacheTemplateEngine());
        
        get("/itens/filterbynameajax/:nome", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return new ItemController(request, response).findJson();
            }
        });
        
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
