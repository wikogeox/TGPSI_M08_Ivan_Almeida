package com.school.javafxblanc;
//Importações
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class ListarMedicoController implements Initializable {
    //Todas as variaveis
    @FXML
    private BorderPane borderPane;
    @FXML
    private ImageView imageViewImage;
    @FXML
    private RadioButton rbMale;
    @FXML
    private RadioButton rbFemale;
    @FXML
    private TextField txtID;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<Medico> tableView;
    @FXML
    private TableColumn<Medico, Integer> tableColumnID;
    @FXML
    private TableColumn<Medico, String> tableColumnNome;
    @FXML
    private TableColumn<Medico, Integer> tableColumnIdade;
    @FXML
    private TableColumn<Medico, Boolean> tableColumnGenero;
    @FXML
    private TableColumn<Medico, String> tableColumnEspecialidade;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtIdade;
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
        tableColumnGenero.setCellValueFactory(new PropertyValueFactory<Medico, Boolean>("genero"));
        tableColumnEspecialidade.setCellValueFactory(new PropertyValueFactory<Medico, String>("especialidade"));

        tableColumnGenero.setCellFactory(eve -> new TableCell<Medico, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);

                setText(empty ? null : item ? "Masculino" : "Feminino");
            }
        });

        tableView.setItems(Settings.getListaMedicos());
    }

    //Botão que adiciona um novo médico à lista
    public void btnAddMedico(ActionEvent actionEvent) {
        lastID += 1;
        Settings.getListaMedicos().add(new Medico(lastID, txtNome.getText(), parseInt(txtIdade.getText()), rbMale.isSelected(), txtEspecialidade.getText()));
    }

    //Botão que remove um médico da lista
    public void ButtonRemove(ActionEvent actionEvent) {
        //Criação de uma alert box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Definição do titulo da alert box
        alert.setTitle("Eliminar médico");
        //Definição da texto que aparece na alert box
        alert.setHeaderText("Deseja mesmo eliminar o médico?");
        //Criação de dois botões "Sim" e "Não"
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não");
        //Adiciona os dois botões
        alert.getButtonTypes().setAll(botaoSim, botaoNao);
        //Se o botão "Sim" for clicado remove um médico da lista
        alert.showAndWait().ifPresent(responde -> {
            if (responde == botaoSim) {
                int selectedID = tableView.getSelectionModel().getSelectedIndex();
                tableView.getItems().remove(selectedID);
            }
        });
    }

    //Botão que pesquisa um médico
    public void btnPesquisarMedico(ActionEvent actionEvent) {
        //Se a textfield estiver vazia lista todos os médicos
        if (txtPesquisar.getText().equals("")) {
            tableView.refresh();
            tableView.setItems(Settings.getListaMedicos());

        //Se a textfield não estiver vazia pesquisa um médico
        } else {
            for (Medico m : Settings.getListaMedicos()) {
                if (m.getEspecialidade().toLowerCase().contains(txtPesquisar.getText().toLowerCase()))
                    Pesquisa.add(m);
                    if (Pesquisa.size() >= 2)
                        if (Objects.equals(Pesquisa.getFirst().getEspecialidade(), Pesquisa.getLast().getEspecialidade())){

                        }
                        else
                            Pesquisa.removeFirst();
            }
            tableView.setItems(Pesquisa);
        }
        txtPesquisar.setText("");
    }

    public void keyPesquisa(KeyEvent keyEvent) {
//        tableView.setItems(Pesquisa);
    }

    //Botão fechar - Quando clicado fecha o trabalho
    public void buttonClose(ActionEvent actionEvent) {
        //Criação de uma alert box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Definição do titulo da alert box
        alert.setTitle("Sair da aplicação");
        //Definição da texto que aparece na alert box
        alert.setHeaderText("Deseja mesmo sair da aplicação?");
        //Criação de dois botões "Sim" e "Não"
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não");
        //Adiciona os dois botões
        alert.getButtonTypes().setAll(botaoSim, botaoNao);

        //Se o botão "Sim" for clicado fecha a aplicação
        Optional<ButtonType> choose = alert.showAndWait();
        if (choose.get() == botaoSim)
            Platform.exit();
    }

    //Botão que quando clicado coloca no centro a lista de médicos
    public void buttonMedicos(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("listarMedico.fxml"));
        borderPane.setCenter(scene);
        borderPane.setLeft(null);
    }

    //Botão que quando clicado coloca no centro a lista de pacientes
    public void buttonPacientes(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("listarPaciente.fxml"));
        borderPane.setCenter(scene);
    }

    //Botão que quando clicado coloca no centro a lista de consultas
    public void buttonConsultas(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("listarConsultas.fxml"));
        borderPane.setCenter(scene);
    }

    //Botão que abre uma janela com o Acerca de
    public void buttonAcercaDe(ActionEvent actionEvent) throws Exception {
        // Aquisição do controlo da cena (Scene) about FXML
        Parent scene = FXMLLoader.load(getClass().getResource("AcercaDe.fxml"));

        //Nova janela (Stage)
        Stage about = new Stage();

        //Definições da Stage
        about.setTitle("Acerca de");
        about.setResizable(false);

        // Associação da Scene à Stage
        about.setScene(new Scene(scene));

        // Abertura da janela About em modo MODAL, em relação à primaryStage
        about.initOwner(Settings.getPrimaryStage());
        about.initModality(Modality.WINDOW_MODAL);

        //Abertura da janela About
        about.show();
    }

    //Permite editar um médico da lista
    public void editMedico(MouseEvent mouseEvent) {
        int i = tableView.getSelectionModel().getSelectedIndex();

        Medico medico = (Medico) tableView.getItems().get(i);

        txtID.setText(String.valueOf(medico.getID()));
        txtNome.setText(medico.getNome());
        txtIdade.setText(String.valueOf(medico.getIdade()));
        txtEspecialidade.setText(medico.getEspecialidade());
    }

    //Botão que quando clicado edita um médico da lista
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
        String especialidade = txtEspecialidade.getText();

        //Os items editados vão ser colocados na lista
        for (Medico medico : Settings.getListaMedicos()){
            if (ID == medico.getID()){
                medico.setNome(nome);
                medico.setIdade(idade);
                medico.setGenero(rbMale.isSelected());
                medico.setEspecialidade(especialidade);
                break;
            }
            txtID.setText("");
            txtNome.setText("");
            txtIdade.setText("");
            txtEspecialidade.setText("");
        }
        //Atualiza a lista
        tableView.refresh();
    }
}
