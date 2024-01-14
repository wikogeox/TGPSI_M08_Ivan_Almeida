package com.school.javafxblanc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

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
        listaMedicos.add(new Medico(1,"João Silva", 22, "masculino", "pediatria"));
        listaMedicos.add(new Medico(2,"Joana Ferreira", 27, "feminino", "obstetrícia"));
        listaMedicos.add(new Medico(3,"Mario Valente", 32, "masculino", "cardiologia"));
        listaMedicos.add(new Medico(4,"António Costa", 45, "masculino", "dermatologia"));
        listaMedicos.add(new Medico(5,"Maria Rocha", 50, "feminino", "otorrinolaringologia"));
        listaMedicos.add(new Medico(6,"Andreia Cunha", 37, "feminino", "neurologia"));
        listaMedicos.add(new Medico(7,"João Santos", 44, "masculino", "clinica geral"));
        listaMedicos.add(new Medico(8,"André Reis", 22, "masculino", "nutrição"));
        listaMedicos.add(new Medico(9,"Helder Almeida", 41, "masculino", "oncologia"));
        listaMedicos.add(new Medico(10,"Manoela Pires", 43, "feminino", "oftamologia"));
        listaMedicos.add(new Medico(11,"Érica Ramos", 29, "feminino", "radiologia"));
        listaMedicos.add(new Medico(12,"Mario Machado", 37, "masculino", "urologia"));
        listaMedicos.add(new Medico(13,"Joana Sousa", 28, "feminino", "psiquiatria"));
        listaMedicos.add(new Medico(14,"Maria Martins", 30, "feminino", "ortopedia"));
    }

    private static ObservableList<Paciente> listaPacientes = FXCollections.observableArrayList();

    public static ObservableList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public static void setListaPacientes(ObservableList<Paciente> listaPacientes) {
        Settings.listaPacientes = listaPacientes;
    }

    public static void loadListaPacientes(){
        listaPacientes.add(new Paciente(1,"Simão Costa", 41, "masculino", 198762548));
        listaPacientes.add(new Paciente(2,"Mariana Teixeira", 22, "feminino", 263549895));
        listaPacientes.add(new Paciente(3,"Mariano Sousa", 55, "masculino", 537465122));
        listaPacientes.add(new Paciente(4,"Miguel Furtado", 31, "masculino", 283726432));
        listaPacientes.add(new Paciente(5,"Fabiana Ramos", 22, "feminino", 980712333));
    }


    private static ObservableList<Consulta> listaConsultas = FXCollections.observableArrayList();

    public static ObservableList<Consulta> getListaConsultas() {
        return listaConsultas;
    }

    public static void setListaConsultas(ObservableList<Consulta> listaConsultas) {
        Settings.listaConsultas = listaConsultas;
    }


    public static void loadListaConsultas(){
        Paciente paciente1 = listaPacientes.get(0);
        Medico medico1 = listaMedicos.get(0);

        Paciente paciente2 = listaPacientes.get(1);
        Medico medico2 = listaMedicos.get(1);

        Paciente paciente3 = listaPacientes.get(2);
        Medico medico3 = listaMedicos.get(2);

        Paciente paciente4 = listaPacientes.get(4);
        Medico medico4 = listaMedicos.get(4);

        listaConsultas.add(new Consulta(1, "pediatria", "14/1/2024", paciente1, medico1));
        listaConsultas.add(new Consulta(2, "obstetrícia", "22/1/2024", paciente2, medico2));
        listaConsultas.add(new Consulta(3, "cardiologia", "24/1/2024", paciente3, medico3));
        listaConsultas.add(new Consulta(5, "otorrinolaringologia", "22/1/2024", paciente4, medico4));
    }
}
