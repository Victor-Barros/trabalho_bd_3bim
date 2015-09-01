/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

/**
 *
 * @author iapereira
 */
public class RouteExamples {
    public static void main(String[] args) {
                  /*
         get("/hello", (req, res) -> "Hello World");
        */
        
         /*get("/hello/:name", (request, response) -> {
             return "Hello: " + request.params(":name");
         });*/
        
        // matches "GET /say/hello/to/world"
        // request.splat()[0] is 'hello' and request.splat()[1] 'world'
        //get("/say/*/to/*", (request, response) -> {
        //  return "Number of splat parameters: " + request.splat().length;
        //});
    }
}
