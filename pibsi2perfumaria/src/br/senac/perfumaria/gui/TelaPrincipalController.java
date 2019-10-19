
package br.senac.perfumaria.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class TelaPrincipalController implements Initializable {

    private Pane pnl_2;
    private JFXPasswordField lbPasswordTeste;
    @FXML
    private TableView<?> tableCliente;
    @FXML
    private JFXTextField lbNome;
    @FXML
    private JFXTextField lbSobrenome;
    @FXML
    private JFXTextField lbDataDeNascimento;
    @FXML
    private JFXTextField lbCPF;
    @FXML
    private JFXTextField lbRG;
    @FXML
    private JFXComboBox<String> comboGenero;
    @FXML
    private JFXComboBox<String> comboEstadoCivil;
    @FXML
    private JFXTextField lbTelefone;
    @FXML
    private JFXTextField lbEmail;
    @FXML
    private JFXTextField lbCEP;
    @FXML
    private JFXTextField lbLogradouro;
    @FXML
    private JFXTextField lbComplemento;
    @FXML
    private JFXTextField lbBairro;
    @FXML
    private JFXTextField lbCidade;
    @FXML
    private JFXTextField lbUF;
    @FXML
    private JFXTextField lbNome1;
    @FXML
    private JFXButton btSalvarCliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Popula as comboBoxs da tab de Cadastro de Cliente.
            //Combobox de Genero
        comboGenero.getItems().add("Masculino");
        comboGenero.getItems().add("Feminino");   
        comboGenero.getItems().add("Indefinido");
        
            //Combobox do Estado Civil
        comboEstadoCivil.getItems().add("Solteiro");
        comboEstadoCivil.getItems().add("Casado");
        comboEstadoCivil.getItems().add("Separado");
        comboEstadoCivil.getItems().add("Divorciado");
        comboEstadoCivil.getItems().add("Viúvo");
    }    
    

    private void getPasswordTeste(ActionEvent event) {
        System.out.println(lbPasswordTeste.getText());
    }

    @FXML
    private void btEditarNaTable(ActionEvent event) {
    }

    @FXML
    private void btExcluirNaTable(ActionEvent event) {
    }

    @FXML
    private void btProcurarNaTable(ActionEvent event) {
    }

    @FXML
    private void limparCampos(ActionEvent event) {
    }

    @FXML
    private void salvarCliente(ActionEvent event) {
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }
}
