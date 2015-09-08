package init;

import command.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import static spark.Spark.*;
import spark.SparkBase;
import spark.TemplateViewRoute;
import spark.template.mustache.MustacheTemplateEngine;
import database.AnotacaoDAO;
import java.awt.Color;
import model.Anotacao;

/**
 * *
 * Classe que determina as rotas
 *
 * @author victor_barros
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        
        
        staticFileLocation("/html"); 
        
        // index
        get("/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ListCommand(request, response).getMap(), "index.html");
            }
        }, new MustacheTemplateEngine());
        
        
        // trash
        get("/screen_trash", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ListTrashCommand(request, response).getMap(), "screen_trash.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/empty_trash", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new EmptyTrashCommand(request, response).getMap(), "");
            }
        }, new MustacheTemplateEngine());
        
        get("/restore_trash/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new RestoreTrashCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());

        get("/delete_trash/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new DeleteTrashCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());
        
        // delete
        get("/delete/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new DeleteCommand(request, response), "");
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
                return new ModelAndView(new InsertCommand(request, response).getMap(), "message.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/copy/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new CopyCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());

        // update
        get("/screen_update/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ScreenUpdateCommand(request, response).getMap(), "screen_update.html");
            }
        }, new MustacheTemplateEngine());

        post("/update/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new UpdateCommand(request, response).getMap(), "message.html");
            }
        }, new MustacheTemplateEngine());
        
    }
}
