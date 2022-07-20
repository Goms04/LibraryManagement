package com.goms.classe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Document {

    @FXML
    private Button journal;

    @FXML
    private Button livre;

    @FXML
    private Button bd;

    @FXML
    private Button dico;

    @FXML
    private BorderPane borderPane;


    Parent center = null;
    @FXML
    void bd(MouseEvent event) {
        try {
            borderPane.setCenter(null);
            center = FXMLLoader.load(getClass().getResource("/com/goms/Interfaces/Bd.fxml"));
            borderPane.setCenter(center);
            bd.setDisable(true);
            dico.setDisable(false);
            livre.setDisable(false);
            journal.setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void dico(MouseEvent event) {
        try {
            borderPane.setCenter(null);
            center = FXMLLoader.load(getClass().getResource("/com/goms/Interfaces/Dico.fxml"));
            borderPane.setCenter(center);
            dico.setDisable(true);
            livre.setDisable(false);
            bd.setDisable(false);
            journal.setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void journal(MouseEvent event) {
        try {
            borderPane.setCenter(null);
            center = FXMLLoader.load(getClass().getResource("/com/goms/Interfaces/Journal.fxml"));
            borderPane.setCenter(center);
            journal.setDisable(true);
            dico.setDisable(false);
            livre.setDisable(false);
            bd.setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void livre(MouseEvent event) {
        try {
            borderPane.setCenter(null);
            center = FXMLLoader.load(getClass().getResource("/com/goms/Interfaces/Livre.fxml"));
            borderPane.setCenter(center);
            livre.setDisable(true);
            dico.setDisable(false);
            bd.setDisable(false);
            journal.setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
