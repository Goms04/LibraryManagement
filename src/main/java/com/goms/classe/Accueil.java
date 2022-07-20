package com.goms.classe;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Accueil implements Initializable {


    @FXML
    private Button but1;

    @FXML
    private Button but2;

    @FXML
    private Button but3;

    @FXML
    private Button but4;

    @FXML
    private Label menu;

    @FXML
    private Label txt;

    @FXML
    private AnchorPane slidebar;

    @FXML
    private Label menuClose;

    @FXML
    private BorderPane contenu;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menu.setVisible(true);
        menuClose.setVisible(false);

        slidebar.setTranslateX(-500);

        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slidebar);
            slide.setToX(0);
            slide.play();

            slidebar.setTranslateX(-500);
            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(false);
                menuClose.setVisible(true);


            });
       });


        menuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slidebar);
            slide.setToX(-500);
            slide.play();

            slidebar.setTranslateX(0);
            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(true);
                menuClose.setVisible(false);


            });
        });


    }
    Parent center = null;

    @FXML
    void accueil(MouseEvent event) {
        try {
            contenu.setCenter(null);
            center = FXMLLoader.load(getClass().getResource("/com/goms/Interfaces/Acc.fxml"));
            txt.setText("ACCUEIL");
            contenu.setCenter(center);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void adh(MouseEvent event) {
        try {
            contenu.setCenter(null);
            center = FXMLLoader.load(getClass().getResource("/com/goms/Interfaces/Adherent.fxml"));
            txt.setText("ADHERENTS");
            contenu.setCenter(center);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void doc() {
        try {
            contenu.setCenter(null);
            center = FXMLLoader.load(getClass().getResource("/com/goms/Interfaces/Document.fxml"));
            txt.setText("DOCUMENTS");
            contenu.setCenter(center);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void emp(MouseEvent event) {
        try {
            contenu.setCenter(null);
            center = FXMLLoader.load(getClass().getResource("/com/goms/Interfaces/Emprunts.fxml"));
            txt.setText("EMPRUNTS");
            contenu.setCenter(center);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
