/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.ItemDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import model.Item;
import spark.Request;
import spark.Response;
import org.json.*;

/**
 *
 * @author victor_barros
 */
public class ItemController {
    private Request request;
    private Response response;

    public ItemController(Request request, Response response) {
        this.request = request;
        this.response = response;
    }
    
    public Map list() {
        Map map = new HashMap();
        ArrayList<Item> itens;
        itens = new ItemDAO().list();
        if (itens.size() > 0) {
           map.put("itens", itens);
        }
        return map;
    }
    
    public Map view() {
        Map map = new HashMap();
        Item item = new ItemDAO().findById(Integer.parseInt(request.params(":id")));
        map.put("nome", item.getNome());
        map.put("descricao", item.getDescricao());
        map.put("valor", item.getValor());
        return map;
    }
    
    public void insert() {
        request.splat();
        Item item = new Item();
        item.setNome(request.queryParams("nome").trim());
        item.setDescricao(request.queryParams("descricao").trim());
        item.setValor(Double.parseDouble(request.queryParams("valor")));
        new ItemDAO().insert(item);
        response.redirect("/itens");
    }
    
    public void edit() {
        Item item = new Item();
        item.setId(Integer.parseInt(request.queryParams("id")));
        item.setNome(request.queryParams("nome").trim());
        item.setDescricao(request.queryParams("descricao").trim());
        item.setValor(Double.parseDouble(request.queryParams("valor")));
        new ItemDAO().update(item);
        response.redirect("/itens");
    }
    
    public Map screenEdit() {
        Map map = new HashMap();
        Item item;
        item = new ItemDAO().findById(Integer.parseInt(request.params(":id")));
        map.put("id", item.getId());
        map.put("nome", item.getNome());
        map.put("descricao", item.getDescricao());
        map.put("valor", item.getValor());
        return map;
    }
    
    public void delete() {
        Item item = new Item();
        item.setId(Integer.parseInt(request.queryParams("id")));
        new ItemDAO().delete(item);
        response.redirect("/itens");
    }
    
    //ajax
    
    public String findJson() {
        JSONArray jarray = new JSONArray(new ItemDAO().findByNome(request.params(":nome").trim()));
        return jarray.toString();
    }
    
}
