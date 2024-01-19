package com.school.javafxblanc;

public class Paciente extends Pessoa {
    //Atributos
    private int numDeUtente;
    private int ID;

    //Metodos construtores
    public Paciente() {
        super();
    }

    public Paciente(int ID, String nome, int idade, boolean genero, int numDeUtente) {
        super(nome, idade, genero);
        this.numDeUtente = numDeUtente;
        this.ID = ID;
    }

    //Metodos getters e setters
    public int getNumDeUtente() {
        return numDeUtente;
    }

    public void setNumDeUtente(int numDeUtente) {
        this.numDeUtente = numDeUtente;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
