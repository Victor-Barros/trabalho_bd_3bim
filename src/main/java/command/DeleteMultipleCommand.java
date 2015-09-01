package command;

import database.PessoaDAO;
import java.sql.SQLException;
import spark.Request;
import spark.Response;

/**
 * @author iapereira
 */
public class DeleteMultipleCommand extends Command {

    public DeleteMultipleCommand(Request request, Response response) throws SQLException {
        super(request, response);
        if (request.queryMap("id").hasValue()){
            String vetId[] = request.queryMap("id").values(); // pega todos os valores que vieram do html que possuem o mesmo nome (no caso id)
            if (vetId.length > 0) {
                PessoaDAO pessoaDAO = new PessoaDAO();
                for (String id : vetId) {
                    pessoaDAO.delete(Integer.parseInt(id));
                }
                map.put("message", "Todos deletados...");
            } 
        } else {
              map.put("message", "Nenhum id selecionado...");
        }
    }
}
