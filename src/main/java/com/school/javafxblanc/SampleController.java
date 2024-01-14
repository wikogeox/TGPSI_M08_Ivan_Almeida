package com.school.javafxblanc;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class SampleController {
    @FXML
    private BorderPane borderPane;

    public void menuExitApplication(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair da aplicação");
        alert.setHeaderText("Deseja mesmo sair da aplicação?");
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não");
        alert.getButtonTypes().setAll(botaoSim, botaoNao);

        Optional<ButtonType> choose = alert.showAndWait();
        if(choose.get() == botaoSim)
            Platform.exit();
    }

    public void menuInserirMedico(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("inserirMedico.fxml"));
        borderPane.setCenter(scene);
    }

    public void menuListarMedicos(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("listarMedico.fxml"));
        borderPane.setCenter(scene);
    }

    public void menuPesquisarMedico(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("pesquisarMedico.fxml"));
        borderPane.setCenter(scene);
    }

    public void menuMarcarConsultas(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("marcarConsultas.fxml"));
        borderPane.setCenter(scene);
    }

    public void menuListarConsultas(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("listarConsultas.fxml"));
        borderPane.setCenter(scene);
    }

    public void menuEliminarConsultas(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("eleminarConsultas.fxml"));
        borderPane.setCenter(scene);
    }

    public void menuPesquisarConsultas(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("pesquisarConsultas.fxml"));
        borderPane.setCenter(scene);
    }

    public void menuAcercaDe(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("AcercaDe.fxml"));

        //Nova janela (Stage)
        Stage about = new Stage();
        //Definições da Stage
        about.setTitle("Acerca de");

        // Associação da Scene à Stage
        about.setScene(new Scene(scene));

        // Abertura da janela About em modo MODAL, em relação à primaryStage
        //about.initOwner(Settings.primaryStage);
        about.initOwner(Settings.getPrimaryStage());
        about.initModality(Modality.WINDOW_MODAL);

        //Abertura da janela About
        about.show();
    }
}
