package com.school.javafxblanc;

import java.time.LocalDate;

public class Consulta {
    //Atributos
    private String tipoDeConsulta;
    private LocalDate data;
    private Paciente paciente;
    private Medico medico;
    private int IDM;

    //Metodo construtor
    public Consulta(int IDM) {
        this.IDM = IDM;
    }

    //Agregação - Metodo construtor
    public Consulta(int IDM, String tipoDeConsulta, LocalDate data,Paciente paciente, Medico medico) {
        this.tipoDeConsulta = tipoDeConsulta;
        this.data = data;
        this.paciente = paciente;
        this.medico = medico;
        this.IDM = IDM;
    }

    //Metodos getters e setters
    public String getTipoDeConsulta() {
        return tipoDeConsulta;
    }

    public void setTipoDeConsulta(String tipoDeConsulta) {
        this.tipoDeConsulta = tipoDeConsulta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public int getIDM() {
        return IDM;
    }

    public void setIDM(int IDM) {
        this.IDM = IDM;
    }
}
