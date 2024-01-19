package com.school.javafxblanc;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListarPacienteController implements Initializable {
    //Todas as variaveis
    @FXML
    private RadioButton rbMale;
    @FXML
    private RadioButton rbFemale;
    @FXML
    private TableView<Paciente> tableView;
    @FXML
    private TableColumn<Paciente,Integer> tableColumnID ;
    @FXML
    private TableColumn<Paciente,String> tableColumnNome;
    @FXML
    private TableColumn<Paciente,Integer> tableColumnIdade;
    @FXML
    private TableColumn<Paciente,Boolean> tableColumnGenero;
    @FXML
    private TableColumn<Paciente,Integer> tableColumnNumDeUtente;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtIdade;
    @FXML
    private TextField txtNumUtente;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private static ObservableList<Paciente> Pesquisa = FXCollections.observableArrayList();

    int lastID = 5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnID.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("ID"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nome"));
        tableColumnIdade.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("idade"));
        tableColumnGenero.setCellValueFactory(new PropertyValueFactory<Paciente, Boolean>("genero"));
        tableColumnNumDeUtente.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("numDeUtente"));

        tableColumnGenero.setCellFactory(eve -> new TableCell<Paciente, Boolean>(){
            @Override
            protected void updateItem(Boolean item, boolean empty){
                super.updateItem(item, empty);

                setText(empty ? null : item ? "Masculino" : "Feminino");
            }
        });

        tableView.setItems(Settings.getListaPacientes());
        Settings.loadListaPacientes();
    }

    //Botão que adiciona um novo médico à lista
    public void btnAddPaciente(ActionEvent actionEvent) {
        lastID += 1;
        Settings.getListaPacientes().add(new Paciente(lastID, txtNome.getText(), Integer.parseInt(txtIdade.getText()), rbMale.isSelected(), Integer.parseInt(txtNumUtente.getText())));
    }

    //Botão que remove um médico da lista
    public void ButtonRemove(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar paciente");
        alert.setHeaderText("Deseja mesmo eliminar o paciente?");
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não");
        alert.getButtonTypes().setAll(botaoSim, botaoNao);
        alert.showAndWait().ifPresent(responde -> {
            if (responde == botaoSim) {
                int selectedID = tableView.getSelectionModel().getSelectedIndex();
                tableView.getItems().remove(selectedID);
            }
        });
    }

    public void btnPesquisarPaciente(ActionEvent actionEvent) {
        if(txtPesquisar.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Campo vazio");
            alert.setHeaderText("Para pesquisar tem de escrever");
            ButtonType botaoOk = new ButtonType("Ok");
            alert.getButtonTypes().setAll(botaoOk);

            Optional<ButtonType> choose = alert.showAndWait();
            if(choose.get() == botaoOk);
        }
        else {
            for (Paciente p : Settings.getListaPacientes()) {
                if (Objects.equals(p.getID(), Integer.parseInt(txtPesquisar.getText())))
                    Pesquisa.add(p);
            }
            Settings.getListaPacientes().clear();
            tableView.setItems(Pesquisa);
        }
    }

    public void keyPesquisa(KeyEvent keyEvent) {
        tableView.setItems(Pesquisa);
    }

}
