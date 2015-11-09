/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author victor_barros
 */
public class Item {
    private int id;
    private String nome;
    private String descricao;
    private Double valor;
    private Boolean status;

    public Item(int id, String nome, String descricao, Double valor, Boolean status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public Boolean isStatus() {
        return status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", nome=" + nome + ", valor=" + valor + '}';
    }
    
}
