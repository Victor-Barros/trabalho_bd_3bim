/*0
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author victor_barros
 */
public class Pedido {
    private int id;
    private Item item;
    private Comanda comanda;
    private Funcionario funcionario;
    private int quantidade;

    public Pedido(int id, Item item, Comanda comanda, Funcionario funcionario, int quantidade) {
        this.id = id;
        this.item = item;
        this.comanda = comanda;
        this.funcionario = funcionario;
        this.quantidade = quantidade;
    }

    public Pedido(int id, Item item, Funcionario funcionario, int quantidade) {
        this.id = id;
        this.item = item;
        this.funcionario = funcionario;
        this.quantidade = quantidade;
    }

    public Pedido() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", item=" + item + ", comanda=" + comanda + ", funcionario=" + funcionario + ", quantidade=" + quantidade + '}';
    }
    
}
