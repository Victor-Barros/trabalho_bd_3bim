package model;

/**
 *
 * @author iapereira
 */
public class Pessoa {
    private int id;
    private String nome;
    private String sobrenome;
    
    public Pessoa(){
        
    }

    public Pessoa(int id, String nome, String sobrenome) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }   
    
    
    public Pessoa (String nome, String sobrenome){
        this.nome =  nome;
        this.sobrenome = sobrenome;
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }
    public void setSobrenome(String sobrenome){
        this.sobrenome =  sobrenome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
