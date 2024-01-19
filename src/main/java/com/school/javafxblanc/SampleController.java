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

    public void buttonComecar(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("listarMedico.fxml"));

        //Nova janela (Stage)
        Stage about = new Stage();
        //Definições da Stage
        about.setTitle("Hospital");

        // Associação da Scene à Stage
        about.setScene(new Scene(scene));

        // Abertura da janela About em modo MODAL, em relação à primaryStage
        about.initOwner(Settings.getPrimaryStage());
        about.initModality(Modality.WINDOW_MODAL);

        //Abertura da janela About
        about.show();
    }

    public void buttonClose(ActionEvent actionEvent) {
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
}