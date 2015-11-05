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
public class Funcionario {
    private int id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private Endereco endereco;
    private String email;
    private Date nascimento;
    private Double salario;
    private Boolean gerente;
    private String senha;
    private Boolean status;
    private TimeStamp created;
    private TimeStamp edited;

    public Funcionario(int id, String nome, String sobrenome, String cpf, String telefone, Endereco endereco, String email, Date nascimento, Double salario, Boolean gerente, String senha, Boolean status, TimeStamp created, TimeStamp edited) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.nascimento = nascimento;
        this.salario = salario;
        this.gerente = gerente;
        this.senha = senha;
        this.status = status;
        this.created = created;
        this.edited = edited;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Boolean getGerente() {
        return gerente;
    }

    public void setGerente(Boolean gerente) {
        this.gerente = gerente;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TimeStamp getCreated() {
        return created;
    }

    public void setCreated(TimeStamp created) {
        this.created = created;
    }

    public TimeStamp getEdited() {
        return edited;
    }

    public void setEdited(TimeStamp edited) {
        this.edited = edited;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cpf=" + cpf + ", telefone=" + telefone + ", endereco=" + endereco + ", email=" + email + ", nascimento=" + nascimento + ", salario=" + salario + ", gerente=" + gerente + ", senha=" + senha + ", status=" + status + ", created=" + created + ", edited=" + edited + '}';
    }
    
    
}
