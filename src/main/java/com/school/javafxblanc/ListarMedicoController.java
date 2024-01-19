package com.school.javafxblanc;
//Importações
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
import java.util.Optional;
import java.util.ResourceBundle;

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
    private AnchorPane pane;
    @FXML
    private TableView<Medico> tableView;
    @FXML
    private TableColumn<Medico,Integer> tableColumnID ;
    @FXML
    private TableColumn<Medico,String> tableColumnNome;
    @FXML
    private TableColumn<Medico,Integer> tableColumnIdade;
    @FXML
    private TableColumn<Medico,Boolean> tableColumnGenero;
    @FXML
    private TableColumn<Medico,String> tableColumnEspecialidade;
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

        tableColumnGenero.setCellFactory(eve -> new TableCell<Medico, Boolean>(){
            @Override
            protected void updateItem(Boolean item, boolean empty){
                super.updateItem(item, empty);

                setText(empty ? null : item ? "Masculino" : "Feminino");
            }
        });

        tableView.setItems(Settings.getListaMedicos());
        Settings.loadListaMedicos();
    }

    //Botão que adiciona um novo médico à lista
    public void btnAddMedico(ActionEvent actionEvent) {
        lastID += 1;
        Settings.getListaMedicos().add(new Medico(lastID, txtNome.getText(), Integer.parseInt(txtIdade.getText()), rbMale.isSelected(), txtEspecialidade.getText()));
    }

    //Botão que remove um médico da lista
    public void ButtonRemove(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar médico");
        alert.setHeaderText("Deseja mesmo eliminar o médico?");
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

    public void buttonMedicos(ActionEvent actionEvent) throws Exception{
        Parent scene = FXMLLoader.load(getClass().getResource("listarMedico.fxml"));
        borderPane.setCenter(scene);
        borderPane.setLeft(null);
    }

    public void buttonPacientes(ActionEvent actionEvent) throws Exception{
        Parent scene = FXMLLoader.load(getClass().getResource("listarPaciente.fxml"));
        borderPane.setCenter(scene);
    }

    public void buttonConsultas(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("listarConsultas.fxml"));
        borderPane.setCenter(scene);
    }

    public void buttonAcercaDe(ActionEvent actionEvent) throws Exception{
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
}
