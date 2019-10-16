
package br.senac.perfumaria.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;


public class TelaPrincipalController implements Initializable {

    private Pane pnl_2;
    @FXML
    private JFXPasswordField lbPasswordTeste;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    private void getPasswordTeste(ActionEvent event) {
        System.out.println(lbPasswordTeste.getText());
    }

    
}
