package com.school.javafxblanc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //1º passo - O método Start associa este Layout a um objeto à class Parent
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //2º passo . Definições da Stage
        primaryStage.setTitle("Trabalho final do módulo 8");
        primaryStage.setResizable(false);
        //3º passo - Associação do Layout do Parent à Scene e esta à Stage
        primaryStage.setScene(new Scene(root));
        //Não deixa passar da segunda janela para a principal
        Settings.setPrimaryStage(primaryStage);
        //Lista todas as listas
        Settings.loadListaMedicos();
        Settings.loadListaPacientes();
        Settings.loadListaConsultas();
        //4º passo - Mostrar a Stage no Sistema Operativo
        primaryStage.show();
    }
}
