package com.school.javafxblanc;

import javafx.scene.control.RadioButton;

public class Pessoa {
    //Atributos
    private String nome;
    private int idade;
    private boolean genero;

    //Metodos construtores
    public Pessoa() {

    }

    public Pessoa(String nome, int idade, boolean genero) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
    }

    //Metodos getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean getGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }
}
