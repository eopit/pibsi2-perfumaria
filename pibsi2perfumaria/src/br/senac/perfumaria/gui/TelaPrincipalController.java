package br.senac.perfumaria.gui;

import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
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
    private JFXTextField lbBusca;
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

        lbCEP.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                int tamanhoCEP = lbCEP.getText().length();
                ViaCEP viaCep = new ViaCEP();

                try {
                    viaCep.buscar(lbCEP.getText());
                    lbLogradouro.setText(viaCep.getLogradouro());;
                    lbComplemento.setText(viaCep.getComplemento());
                    lbBairro.setText(viaCep.getBairro());
                    lbCidade.setText(viaCep.getLocalidade());
                    lbUF.setText(viaCep.getUf());
                } catch (ViaCEPException ex) {
                    Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
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
        lbNome.setText("");
        lbSobrenome.setText("");
        lbDataDeNascimento.setText("");
        lbCPF.setText("");
        lbRG.setText("");
        comboGenero.setValue(null);
        comboEstadoCivil.setValue(null);
        lbTelefone.setText("");
        lbEmail.setText("");
        lbCEP.setText("");
        lbLogradouro.setText("");
        lbComplemento.setText("");
        lbBairro.setText("");
        lbCidade.setText("");
        lbUF.setText("");
        lbBusca.setText("");
    }

    @FXML
    private void salvarCliente(ActionEvent event) {
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void testeCep(KeyEvent event) {

    }
}
