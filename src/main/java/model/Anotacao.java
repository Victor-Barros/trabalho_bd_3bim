/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.util.Date;

/**
 *
 * @author victor_barros
 */
public class Anotacao {
    
    private int id;
    private String titulo;
    private String descricao;
    private Color cor;
    private boolean status;
    private Date created;
    private Date edited;

    public Anotacao() {
        
    }
    
    public Anotacao(int id, String titulo, String descricao, Color cor, Date created, Date edited) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.cor = cor;
        this.created = created;
        this.edited = edited;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
    public Date getEdited() {
        return edited;
    }

    public void setedited(Date edited) {
        this.edited = edited;
    }
    
    @Override
    public String toString() {
        return "Anotacao{" + "id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", cor=" + cor + ", status=" + status + ", created=" + created + ", edited=" + edited + '}';
    }
}
