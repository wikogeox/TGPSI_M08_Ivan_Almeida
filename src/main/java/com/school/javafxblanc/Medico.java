package com.school.javafxblanc;

import javafx.scene.control.DatePicker;

public class Medico extends Pessoa {
    //Atributos
    private String especialidade;
    private int ID;

    //Metodos construtores
    public Medico(int lastID, String text, DatePicker datePickerData, String txtNomePText, String txtNomeMText) {
        super();
    }

    public Medico(int ID, String nome, int idade, boolean genero, String especialidade) {
        super(nome, idade, genero);
        this.especialidade = especialidade;
        this.ID = ID;
    }

    //Metodos getters e setters
    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
