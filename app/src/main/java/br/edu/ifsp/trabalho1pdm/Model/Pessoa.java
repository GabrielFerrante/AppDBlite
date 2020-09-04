package br.edu.ifsp.trabalho1pdm.Model;

public class Pessoa {

    private int id;
    private String nome;
    private String email;
    private int idade;
    private String celular;

    public static final String ID_PESSOA = "br.edu.ifsp.trabalho1pdm.ID_PESSOA";

    public Pessoa( String nome, String email, int idade, String celular) {

        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.celular = celular;
    }
    public Pessoa(int id, String nome, String email, int idade, String celular) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.celular = celular;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
