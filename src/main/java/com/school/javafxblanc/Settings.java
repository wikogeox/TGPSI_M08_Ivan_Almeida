package com.school.javafxblanc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Settings {
    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Settings.primaryStage = primaryStage;
    }

    private static ObservableList<Medico> listaMedicos = FXCollections.observableArrayList();

    public static ObservableList<Medico> getListaMedicos() {
        return listaMedicos;
    }

    public static void setListaMedicos(ObservableList<Medico> listaMedicos) {
        Settings.listaMedicos = listaMedicos;
    }

    public static void loadListaMedicos(){
        listaMedicos.add(new Medico(1,"João Silva", 22, true, "pediatria"));
        listaMedicos.add(new Medico(2,"Joana Ferreira", 27, false, "obstetrícia"));
        listaMedicos.add(new Medico(3,"Mario Valente", 32, true, "cardiologia"));
        listaMedicos.add(new Medico(4,"António Costa", 45, true, "dermatologia"));
        listaMedicos.add(new Medico(5,"Maria Rocha", 50, false, "otorrinolaringologia"));
        listaMedicos.add(new Medico(6,"Andreia Cunha", 37, false, "neurologia"));
        listaMedicos.add(new Medico(7,"João Santos", 44, true, "clinica geral"));
        listaMedicos.add(new Medico(8,"André Reis", 22, true, "nutrição"));
        listaMedicos.add(new Medico(9,"Helder Almeida", 41, true, "oncologia"));
        listaMedicos.add(new Medico(10,"Manoela Pires", 43, false, "oftamologia"));
        listaMedicos.add(new Medico(11,"Érica Ramos", 29, false, "radiologia"));
        listaMedicos.add(new Medico(12,"Mario Machado", 37, true, "urologia"));
        listaMedicos.add(new Medico(13,"Joana Sousa", 28, false, "psiquiatria"));
        listaMedicos.add(new Medico(14,"Maria Martins", 30, false, "ortopedia"));
    }

    private static ObservableList<Paciente> listaPacientes = FXCollections.observableArrayList();

    public static ObservableList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public static void setListaPacientes(ObservableList<Paciente> listaPacientes) {
        Settings.listaPacientes = listaPacientes;
    }

    public static void loadListaPacientes(){
        listaPacientes.add(new Paciente(1,"Simão Costa", 41, true, 198762548));
        listaPacientes.add(new Paciente(2,"Mariana Teixeira", 22, false, 263549895));
        listaPacientes.add(new Paciente(3,"Mariano Sousa", 55, true, 537465122));
        listaPacientes.add(new Paciente(4,"Miguel Furtado", 31, true, 283726432));
        listaPacientes.add(new Paciente(5,"Fabiana Ramos", 22, false, 980712333));
    }


    private static ObservableList<Consulta> listaConsultas = FXCollections.observableArrayList();

    public static ObservableList<Consulta> getListaConsultas() {
        return listaConsultas;
    }

    public static void setListaConsultas(ObservableList<Consulta> listaConsultas) {
        Settings.listaConsultas = listaConsultas;
    }


    public static void loadListaConsultas(){
        listaConsultas.add(new Consulta(1, "pediatria", LocalDate.of(2024, 1, 14), listaPacientes.get(1), listaMedicos.get(1)));
        listaConsultas.add(new Consulta(2, "obstetrícia", LocalDate.of(2024, 1, 19), listaPacientes.get(1), listaMedicos.get(1)));
        listaConsultas.add(new Consulta(3, "cardiologia", LocalDate.of(2024, 1, 23), listaPacientes.get(1), listaMedicos.get(1)));
        listaConsultas.add(new Consulta(5, "otorrinolaringologia", LocalDate.of(2024, 1, 30), listaPacientes.get(1), listaMedicos.get(1)));
    }
}
