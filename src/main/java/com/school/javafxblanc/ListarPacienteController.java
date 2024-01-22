package com.school.javafxblanc;
//Importações
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class ListarPacienteController implements Initializable {
    //Todas as variaveis
    @FXML
    private TextField txtID;
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
    }

    //Botão que adiciona um novo paciente à lista
    public void btnAddPaciente(ActionEvent actionEvent) {
        lastID += 1;
        Settings.getListaPacientes().add(new Paciente(lastID, txtNome.getText(), Integer.parseInt(txtIdade.getText()), rbMale.isSelected(), Integer.parseInt(txtNumUtente.getText())));
    }

    //Botão que remove um paciente da lista
    public void ButtonRemove(ActionEvent actionEvent) {
        //Criação de uma alert box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Definição do titulo da alert box
        alert.setTitle("Eliminar paciente");
        //Definição da texto que aparece na alert box
        alert.setHeaderText("Deseja mesmo eliminar o paciente?");
        //Criação de dois botões "Sim" e "Não"
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não");
        //Adiciona os dois botões
        alert.getButtonTypes().setAll(botaoSim, botaoNao);
        //Se o botão "Sim" for clicado remove um paciente da lista
        alert.showAndWait().ifPresent(responde -> {
            if (responde == botaoSim) {
                int selectedID = tableView.getSelectionModel().getSelectedIndex();
                tableView.getItems().remove(selectedID);
            }
        });
    }

    //Botão que pesquisa um paciente
    public void btnPesquisarPaciente(ActionEvent actionEvent) {
        //Se a textfield estiver vazia lista todos os pacientes
        if(txtPesquisar.getText().equals("")){
            tableView.refresh();
            tableView.setItems(Settings.getListaPacientes());
        }

        //Se a textfield não estiver vazia pesquisa um paciente
        else {
            for (Paciente p : Settings.getListaPacientes()) {
                if (Objects.equals(p.getID(), Integer.parseInt(txtPesquisar.getText())))
                    Pesquisa.add(p);
                    if (Pesquisa.size() == 2)
                        Pesquisa.removeFirst();
            }
            tableView.setItems(Pesquisa);
        }
        txtPesquisar.setText("");
    }

    public void keyPesquisa(KeyEvent keyEvent) {
        tableView.setItems(Pesquisa);
    }

    //Permite editar um paciente da lista
    public void editPaciente(MouseEvent mouseEvent) {
        int i = tableView.getSelectionModel().getSelectedIndex();

        Paciente paciente = (Paciente) tableView.getItems().get(i);

        txtID.setText(String.valueOf(paciente.getID()));
        txtNome.setText(paciente.getNome());
        txtIdade.setText(String.valueOf(paciente.getIdade()));
        txtNumUtente.setText(String.valueOf(paciente.getNumDeUtente()));
    }

    //Botão que quando clicado edita um paciente da lista
    public void buttonEditar(ActionEvent actionEvent) {

        //Se nenhum item for selecionado vai aparecer uma alert box
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            //Criação de uma alert box
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //Definição do titulo da alert box
            alert.setTitle("Item não selecionado");
            //Definição da texto que aparece na alert box
            alert.setHeaderText("Selecione um item, por favor");
            //Mostra a alert box
            alert.show();
            return;
        }

        int ID = parseInt(txtID.getText());
        String nome = txtNome.getText();
        int idade = parseInt(txtIdade.getText());
        int numUtente = parseInt(txtNumUtente.getText());

        //Os items editados vão ser colocados na lista
        for (Paciente paciente : Settings.getListaPacientes()){
            if (ID == paciente.getID()){
                paciente.setNome(nome);
                paciente.setIdade(idade);
                paciente.setGenero(rbMale.isSelected());
                paciente.setNumDeUtente(numUtente);
                break;
            }
            txtID.setText("");
            txtNome.setText("");
            txtIdade.setText("");
            txtNumUtente.setText("");
        }
        //Atualiza a lista
        tableView.refresh();
    }
}
