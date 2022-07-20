package com.goms.classe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    @FXML
    private Button cancelbt;

    @FXML
    private PasswordField mdp;

    @FXML
    private Button loginbt;

    @FXML
    private TextField util;

    PreparedStatement ps;
    ResultSet rs;
   

    @FXML
    private void cancel() {
        Stage stage = (Stage) loginbt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void login(ActionEvent event)  {
        String uname = util.getText();
        String pass = mdp.getText();
        if(uname.equals("") && pass.equals("")){
            //boite de dialogue
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Cases vides à remplir SVP!");
            alert.showAndWait();
            util.requestFocus();
        }else{

        try{
            Connection conn = ConnexionBD.getConnection();
            String req="select * from user where utilisateur=? and motdepasse=?";
            ps = conn.prepareStatement(req);
            ps.setString(1,uname);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if(rs.next()){
                //boite de dialogue
               /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Connexion réussie");
                alert.showAndWait();*/

                //nouvelle page page
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/goms/Interfaces/Accueil.fxml")));
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();


            }else {
                //boite de dialogue
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur/Mot de passe erroné");
                alert.showAndWait();
                util.setText("");
                mdp.setText("");
                util.requestFocus();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        }
    }


    public static void loadview(Stage stage){
        Parent root = null;
        try {
            root = FXMLLoader.load(Login.class.getResource("/com/goms/Interfaces/Login.fxml"));

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
