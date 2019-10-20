package br.senac.perfumaria.gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class TelaLoginController implements Initializable {

    private double xOffset;
    private double yOffset;
    
    private Stage telaPrincipalStage;
    
    @FXML
    private JFXTextField lbUsuario;
    @FXML
    private JFXPasswordField lbSenha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void btEntrar(ActionEvent event) throws Exception {
        Parent telaPrincipal = FXMLLoader.load(getClass().getResource("/br/senac/perfumaria/gui/telaPrincipal.fxml"));
        telaPrincipalStage = new Stage();
        Scene telaPrincipalScene = new Scene(telaPrincipal);
        
        //Metodo para deixar ela draggable
        telaPrincipal.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        
        //Metodo que roda quando esta sendo arrastada
        telaPrincipal.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                telaPrincipalStage.setX(event.getScreenX() - xOffset);
                telaPrincipalStage.setY(event.getScreenY() - yOffset);
            }
        });
            telaPrincipalStage.initStyle(StageStyle.UNDECORATED);
            telaPrincipalStage.setScene(telaPrincipalScene);
            telaPrincipalStage.show();
            lbSenha.getScene().getWindow().hide();
            telaPrincipalStage.toFront();
    }
}
