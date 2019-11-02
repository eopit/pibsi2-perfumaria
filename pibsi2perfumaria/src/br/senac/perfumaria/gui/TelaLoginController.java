package br.senac.perfumaria.gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaLoginController implements Initializable {

    private double xOffset;
    private double yOffset;

    private Stage telaPrincipalStage;

    private String usuario = "root";
    private String senha = "root";

    @FXML
    private JFXTextField lbUsuario;
    @FXML
    private JFXPasswordField lbSenha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbUsuario.setText("root");
        //Plataform run later serve para rodar depois de dar o initialize na scene.
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lbSenha.requestFocus();
                
                //Faz o ENTER logar quando estiver no campo de usuario
                lbSenha.setOnKeyPressed(new EventHandler<KeyEvent>()  {
                    @Override
                    public void handle(KeyEvent ke) {
                        if (ke.getCode().equals(KeyCode.ENTER)) {
                            try {
                                botaoLogar();
                            } catch (Exception ex) {
                                Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                );
            }
        });

    }

    public void botaoLogar() throws Exception {
        //Checa se o usuario esta correto
        if (lbUsuario.getText().equals(usuario) && lbSenha.getText().equals(senha)) {
            //Seta a cor do texto pra branco
            lbUsuario.setStyle("-fx-text-fill: white;");
            lbSenha.setStyle("-fx-text-fill: white;");

            //Cria e inicia a nova tela
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

        if (!lbUsuario.getText().equals(usuario) || !lbSenha.getText().equals(senha)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao logar!");
            alert.setContentText("Favor verificar o campo em vermelho");
            alert.showAndWait();
        }

        if (!lbUsuario.getText().equals(usuario)) {
            lbUsuario.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        }

        if (!lbSenha.getText().equals(senha)) {
            lbSenha.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        }
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void btEntrar(ActionEvent event) throws Exception {
        botaoLogar();
    }
}
