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
public class Mesa {
    private int id;
    private int numero;
    private int nlugares;

    public Mesa(int id, int numero, int nlugares) {
        this.id = id;
        this.numero = numero;
        this.nlugares = nlugares;
    }

    public Mesa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNlugares() {
        return nlugares;
    }

    public void setNlugares(int nlugares) {
        this.nlugares = nlugares;
    }

    @Override
    public String toString() {
        return "Mesa{" + "id=" + id + ", numero=" + numero + ", nlugares=" + nlugares + '}';
    }
        
}
