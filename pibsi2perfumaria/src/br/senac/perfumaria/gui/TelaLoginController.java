package br.senac.perfumaria.gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class TelaLoginController implements Initializable {
    
    //Variaveis

    private Stage telaPrincipalStage;
    
    //
    
    @FXML
    private JFXTextField lbUsuario;
    @FXML
    private JFXPasswordField lbSenha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Metodo Initialize
    }    
    
        private void arrastarJanela() {
        
   }

        @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void btEntrar(ActionEvent event) throws Exception {
        if (telaPrincipalStage == null || !telaPrincipalStage.isShowing()) {
            Parent telaPrincipal = FXMLLoader.load(getClass().getResource("/br/senac/perfumaria/gui/telaPrincipal.fxml"));
            telaPrincipalStage = new Stage();
            Scene telaPrincipalScene = new Scene(telaPrincipal);
            telaPrincipalStage.setScene(telaPrincipalScene);
//            telaPrincipalStage.setTitle("Clientes");
//            telaPrincipalStage.setMinHeight(380);
//            telaPrincipalStage.setMinWidth(812);
            telaPrincipalStage.show();
            lbSenha.getScene().getWindow().hide();
        }
        telaPrincipalStage.toFront();
    }
}
