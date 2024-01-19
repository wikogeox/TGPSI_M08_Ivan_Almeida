package com.school.javafxblanc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListarConsultasController implements Initializable {
    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtNomeP;
    @FXML
    private TextField txtNomeM;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private DatePicker datePickerData;
    @FXML
    private TableView<Consulta> tableViewConsultas;
    @FXML
    private TableColumn<Consulta,Integer> tableColumnID;
    @FXML
    private TableColumn<Consulta,String> tableColumnTipo;
    @FXML
    private TableColumn<Consulta, LocalDate> tableColumnIData;
    @FXML
    private TableColumn<Consulta, String> tableColumnPaciente;
    @FXML
    private TableColumn<Consulta,String> tableColumnMedico;

    @FXML
    private static ObservableList<Consulta> Pesquisa = FXCollections.observableArrayList();

    int lastID = 5;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnID.setCellValueFactory(new PropertyValueFactory<Consulta, Integer>("IDM"));
        tableColumnTipo.setCellValueFactory(new PropertyValueFactory<Consulta, String>("tipoDeConsulta"));
        tableColumnIData.setCellValueFactory(new PropertyValueFactory<Consulta, LocalDate>("data"));
        tableColumnPaciente.setCellValueFactory(new PropertyValueFactory<Consulta, String>("paciente"));
        tableColumnMedico.setCellValueFactory(new PropertyValueFactory<Consulta, String>("medico"));

        tableViewConsultas.setItems(Settings.getListaConsultas());
    }

    public void buttonCarregarLista(ActionEvent actionEvent) {
        //Limpa e a seguir carrega a ObservableList que se encontra na classe Settings
        Settings.getListaConsultas().clear();
        Settings.loadListaConsultas();
    }

    public void btnAddConsulta(ActionEvent actionEvent) {
//        lastID += 1;
//        Settings.getListaConsultas().add(new Consulta(lastID, txtTipo.getText(), datePickerData.getValue(), txtNomeP.getText(), txtNomeM.getText()));
    }

    public void ButtonRemove(ActionEvent actionEvent) {
        int selectedID = tableViewConsultas.getSelectionModel().getSelectedIndex();
        tableViewConsultas.getItems().remove(selectedID);
    }

    public void btnPesquisarConsulta(ActionEvent actionEvent) {
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
            for (Consulta c : Settings.getListaConsultas()) {
                if (c.getTipoDeConsulta().toLowerCase().equals(txtPesquisar.getText().toLowerCase()))
                    Pesquisa.add(c);
            }
            Settings.getListaConsultas().clear();
            tableViewConsultas.setItems(Pesquisa);
        }
    }
}
