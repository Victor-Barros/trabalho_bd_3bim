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
public class Comanda {
    private int id;
    private boolean status;
    private Mesa mesa;
    private Cliente cliente;
    private double total;

    public Comanda(int id, boolean status, Mesa mesa, Cliente cliente, double total) {
        this.id = id;
        this.status = status;
        this.mesa = mesa;
        this.cliente = cliente;
        this.total = total;
    }

    public Comanda() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Comanda{" + "id=" + id + ", status=" + status + ", mesa=" + mesa + ", cliente=" + cliente + ", total=" + total + '}';
    }
    
}
