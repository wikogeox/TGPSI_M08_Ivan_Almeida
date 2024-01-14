package com.school.javafxblanc;
//Importações
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListarMedicoController implements Initializable {
    //Todas as variaveis
    @FXML
    private TableView<Medico> tableView;
    @FXML
    private TableColumn<Medico,Integer> tableColumnID ;
    @FXML
    private TableColumn<Medico,String> tableColumnNome;
    @FXML
    private TableColumn<Medico,Integer> tableColumnIdade;
    @FXML
    private TableColumn<Medico,String> tableColumnGenero;
    @FXML
    private TableColumn<Medico,String> tableColumnEspecialidade;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtIdade;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtEspecialidade;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private static ObservableList<Medico> Pesquisa = FXCollections.observableArrayList();

    int lastID = 14;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnID.setCellValueFactory(new PropertyValueFactory<Medico, Integer>("ID"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Medico, String>("nome"));
        tableColumnIdade.setCellValueFactory(new PropertyValueFactory<Medico, Integer>("idade"));
        tableColumnGenero.setCellValueFactory(new PropertyValueFactory<Medico, String>("genero"));
        tableColumnEspecialidade.setCellValueFactory(new PropertyValueFactory<Medico, String>("especialidade"));

        tableView.setItems(Settings.getListaMedicos());
    }

    public void buttonCarregarLista(ActionEvent actionEvent) {
        //Limpa e a seguir carrega a ObservableList que se encontra na classe Settings
        Settings.getListaMedicos().clear();
        Settings.loadListaMedicos();
    }
    //Botão que adiciona um novo médico à lista
    public void btnAddMedico(ActionEvent actionEvent) {
        lastID += 1;
        Settings.getListaMedicos().add(new Medico(lastID, txtNome.getText(), Integer.parseInt(txtIdade.getText()),txtGenero.getText(), txtEspecialidade.getText()));
    }
    //Botão que remove um médico da lista
    public void ButtonRemove(ActionEvent actionEvent) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
    }

    public void btnPesquisarMedico(ActionEvent actionEvent) {
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
            for (Medico m : Settings.getListaMedicos()) {
                if (m.getEspecialidade().toLowerCase().equals(txtPesquisar.getText().toLowerCase()))
                    Pesquisa.add(m);
            }
            Settings.getListaMedicos().clear();
            tableView.setItems(Pesquisa);
        }
    }

    public void keyPesquisa(KeyEvent keyEvent) {
        tableView.setItems(Pesquisa);
    }
}
