package br.senac.perfumaria.application;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Perfumaria extends Application {

    private double xOffset;
    private double yOffset;

    public void start(Stage main) throws Exception {
        Parent telaLogin = FXMLLoader.load(getClass().getResource("/br/senac/perfumaria/gui/telaLogin.fxml"));
        Scene loginScene = new Scene(telaLogin);

        Image icone = new Image(getClass().getResourceAsStream("/br/senac/perfumaria/gui/images/logo.png"));
        main.getIcons().add(icone);
        
        //Metodo para deixar ela draggable
// <editor-fold defaultstate="collapsed" desc="Draggable">     
        telaLogin.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //Metodo que roda quando esta sendo arrastada
        telaLogin.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.setX(event.getScreenX() - xOffset);
                main.setY(event.getScreenY() - yOffset);
                main.setOpacity(0.9f);
            }
        });

        //=====================================================================
        //Metodos para voltar a opacidade ao normal depois de arrastar
        telaLogin.setOnDragDone((event) -> {
            main.setOpacity(1.0f);
        });

        telaLogin.setOnMouseReleased((event) -> {
            main.setOpacity(1.0f);
        });

        //=====================================================================
// </editor-fold>   
        //Roda a scene nova com a configuracao
        main.setScene(loginScene);
        main.setMaxHeight(560); //560
        main.setMaxWidth(516);  //516
        main.setMinHeight(560);
        main.setMinWidth(516);
        main.initStyle(StageStyle.UNDECORATED);
        main.show();
    }
}
