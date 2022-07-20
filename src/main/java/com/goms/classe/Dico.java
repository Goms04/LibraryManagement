package com.goms.classe;

import com.goms.model.Dic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.goms.classe.ConnexionBD.getConnection;

public class Dico implements Initializable {

    @FXML
    private Button ajouter;

    @FXML
    private Button modifier;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtitre;

    @FXML
    private TextField txtaut;

    @FXML
    private TableView<Dic> tableau;

    @FXML
    private TableColumn<Dic, Integer> colid;

    @FXML
    private TableColumn<Dic, String> coltitre;

    @FXML
    private TableColumn<Dic, String> colaut;

    @FXML
    private Button supprimer;

    PreparedStatement st;
    Connection conn=ConnexionBD.getConnection();

    @FXML
    void ajouter(ActionEvent event) {
        String query = "INSERT INTO dico (`titre`, `auteur`) VALUES ('" + txtitre.getText() + "','" + txtaut.getText() + "')";
        executeQuery(query);
        txtitre.setText("");
        txtaut.setText("");
        List();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Dictionnaire ajouté avec success",ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    void modifier(ActionEvent event) {
         String id = txtid.getText();
        String titre = txtitre.getText();
        String auteur = txtaut.getText();



        String sql = "update dico set titre=?,auteur=? where id = '"+txtid.getText()+"' ";
        if (!titre.equals("") && !auteur.equals("")) {
            try {
                st = conn.prepareStatement(sql);
                st.setString(1, titre);
                st.setString(2, auteur);

                st.executeUpdate();
                txtid.setText("");
                txtitre.setText("");
                txtaut.setText("");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Dictionnaire Modifié avec success", ButtonType.OK);
                alert.showAndWait();
                List();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void supprimer(ActionEvent event) {

        String sql = "delete from dico where id = '"+txtid.getText()+"' ";

        try {
            st=conn.prepareStatement(sql);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("VOULEZ-VOUS VRAIMENT ENLEVER CE DICTIONNAIRE!!!");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();


            if (option.get() == ButtonType.OK) {
                st.executeUpdate();
                txtitre.setText("");
                txtid.setText("");
                txtaut.setText("");

                List();
            } else if (option.get() == ButtonType.CANCEL) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ObservableList<Dic> getDicList(){
        ObservableList<Dic> diclist = FXCollections.observableArrayList();
        ResultSet rs;
        Statement st;
        Connection conn = ConnexionBD.getConnection();
        String sql = "SELECT * FROM dico ";
        try{
            st= conn.createStatement();
            rs= st.executeQuery(sql);
            Dic dic = null;
            while (rs.next()) {
                dic = new Dic(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"));
                diclist.add(dic);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return diclist;
    }


    @FXML
    void bouger(MouseEvent event) {

        Dic selected = tableau.getSelectionModel().getSelectedItems().get(0);
        txtid.setText(String.valueOf(selected.getId()));
        txtitre.setText(selected.getTitre());
        txtaut.setText(selected.getAuteur());
        ;

    }



    public void  List(){
        tableau.getItems().clear();

         ObservableList<Dic> list = getDicList();
        colid.setCellValueFactory(new PropertyValueFactory<Dic,Integer>("id"));
        coltitre.setCellValueFactory(new PropertyValueFactory<Dic,String>("titre"));
        colaut.setCellValueFactory(new PropertyValueFactory<Dic,String>("auteur"));
        tableau.setItems(list);
    }


    private void executeQuery(String query) {

        try {
            Connection conn = getConnection();
            Statement st;
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            List();
    }
}
