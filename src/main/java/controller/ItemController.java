/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.ItemDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Item;
import spark.Request;
import spark.Response;

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
    
    public void insert() {
        request.splat();
        Item item = new Item();
        item.setDescricao(request.queryParams("descricao").trim());
        new ItemDAO().insert(item);
        response.redirect("/");
    }
    
    public void edit() {
        Item item = new Item();
        item.setId(Integer.parseInt(request.params(":id")));
        new ItemDAO().update(item);
        response.redirect("/");
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
        item.setId(Integer.parseInt(request.params(":id")));
        new ItemDAO().delete(item);
        response.redirect("/");
    }
    
}
