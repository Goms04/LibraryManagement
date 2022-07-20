package com.goms.classe;

import com.goms.model.Adh;
import com.goms.model.Liv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.goms.classe.ConnexionBD.getConnection;

public class Adherent implements Initializable {



    private Adh selected;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtprn;

    @FXML
    private TextField txtpro;

    @FXML
    private TextField txtel;

    @FXML
    private TextField txtmail;

    @FXML
    private TextField txtrech;

    @FXML
    private TextField txtid;

    @FXML
    private TableView<Adh> tableau;

    @FXML
    private TableColumn<Adh, String> colnom;

    @FXML
    private TableColumn<Adh, String> colprn;

    @FXML
    private TableColumn<Adh, String> colpro;

    @FXML
    private TableColumn<Adh, Integer> coltel;

    @FXML
    private TableColumn<Adh, String> colmail;

    Connection conn = ConnexionBD.getConnection();

    @FXML
    private Button ajouter;

    @FXML
    private Button modifier;

    @FXML
    private Button supprimer;

    @FXML
    private Button imprimer;

    @FXML
    private Button rechercher;

    PreparedStatement st;
    ResultSet rs;

    @FXML
    void reset(ActionEvent event) {
        txtid.setText("");
        txtnom.setText("");
        txtprn.setText("");
        txtpro.setText("");
        txtel.setText("");
        txtmail.setText("");
        txtrech.setText("");
        ajouter.setDisable(false);
        modifier.setDisable(true);
        supprimer.setDisable(true);
    }

    @FXML
    void ajouter() {

        String query = "INSERT INTO adherents (`nom`, `prenom`, `profession`, `tel`, `email`) VALUES ('" + txtnom.getText() + "','" + txtprn.getText() + "','" + txtpro.getText() + "','"
                + txtel.getText() + "','" + txtmail.getText() + "')";
        executeQuery(query);
        txtnom.setText("");
        txtprn.setText("");
        txtpro.setText("");
        txtrech.setText("");
        txtel.setText("");
        txtmail.setText("");
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Adherent ajouté avec success",ButtonType.OK);
        alert.showAndWait();
        List();

    }


    @FXML
    void imprimer(ActionEvent event) {

    }

    @FXML
    void modifier(ActionEvent event) {
        String nom = txtnom.getText();
        String prn = txtprn.getText();
        String pro = txtpro.getText();
        String tel = txtel.getText();
        String mail = txtmail.getText();


        String sql = "update adherents set nom=?,prenom=?,profession=?,tel=?,email=? where id = '"+txtid.getText()+"' ";
        if (!nom.equals("") && !prn.equals("") && !tel.equals("") && !mail.equals(null)) {
            try {
                st = conn.prepareStatement(sql);
                st.setString(1, nom);
                st.setString(2, prn);
                st.setString(3, pro);
                st.setString(4, tel);
                st.setString(5, mail);
                st.executeUpdate();
                txtid.setText("");
                txtnom.setText("");
                txtprn.setText("");
                txtpro.setText("");
                txtel.setText("");
                txtmail.setText("");
                txtrech.setText("");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Livre Modifié avec success", ButtonType.OK);
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
    void rech(KeyEvent event) {
        tableau.getItems().clear();
        ObservableList<Adh> data = FXCollections.observableArrayList();
        String sql = "select * from adherents where nom like ?" ;
        try{
            st= conn.prepareStatement(sql);
            st.setString(1, "%"+txtrech.getText()+"%");
            rs= st.executeQuery();
            Adh adh = null;
            while (rs.next()) {
                adh = new Adh(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("profession"), rs.getInt("tel"), rs.getString("email"));
                data.add(adh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        colnom.setCellValueFactory(new PropertyValueFactory<Adh, String>("nom"));
        colprn.setCellValueFactory(new PropertyValueFactory<Adh, String>("prenom"));
        colpro.setCellValueFactory(new PropertyValueFactory<Adh, String>("profession"));
        coltel.setCellValueFactory(new PropertyValueFactory<Adh, Integer>("tel"));
        colmail.setCellValueFactory(new PropertyValueFactory<Adh, String>("email"));
        tableau.setItems(data);
    }




    @FXML
     void rechercher(ActionEvent event) {
        tableau.getItems().clear();
        ObservableList<Adh> data = FXCollections.observableArrayList();
        String sql = "select * from adherents where nom like ?" ;
        try{
            st= conn.prepareStatement(sql);
            st.setString(1, "%"+txtrech.getText()+"%");
            rs= st.executeQuery();
             Adh adh = null;
            while (rs.next()) {
                adh = new Adh(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("profession"), rs.getInt("tel"), rs.getString("email"));
                data.add(adh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        colnom.setCellValueFactory(new PropertyValueFactory<Adh, String>("nom"));
        colprn.setCellValueFactory(new PropertyValueFactory<Adh, String>("prenom"));
        colpro.setCellValueFactory(new PropertyValueFactory<Adh, String>("profession"));
        coltel.setCellValueFactory(new PropertyValueFactory<Adh, Integer>("tel"));
        colmail.setCellValueFactory(new PropertyValueFactory<Adh, String>("email"));
        tableau.setItems(data);
    }

    @FXML
    void supprimer(ActionEvent event) {

        String sql = "delete from adherents where id = '"+txtid.getText()+"' ";

        try {
            st=conn.prepareStatement(sql);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("VOULEZ-VOUS VRAIMENT ENLEVER CE LIVRE!!!");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();


             if (option.get() == ButtonType.OK) {
                st.executeUpdate();
                txtid.setText("");
                txtnom.setText("");
                txtprn.setText("");
                txtpro.setText("");
                txtel.setText("");
                txtmail.setText("");
                txtrech.setText("");

                 List();
            } else if (option.get() == ButtonType.CANCEL) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ObservableList<Adh> getAdhList() {
        ObservableList<Adh> adhlist = FXCollections.observableArrayList();
        ResultSet rs;
        Statement st;
        Connection conn = getConnection();
        String sql = "SELECT * FROM adherents ";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            Adh adh = null;
            while (rs.next()) {
                adh = new Adh(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("profession"), rs.getInt("tel"), rs.getString("email"));
                adhlist.add(adh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adhlist;
    }


    public void List() {
        tableau.getItems().clear();

        ObservableList<Adh> list = getAdhList();
        colnom.setCellValueFactory(new PropertyValueFactory<Adh, String>("nom"));
        colprn.setCellValueFactory(new PropertyValueFactory<Adh, String>("prenom"));
        colpro.setCellValueFactory(new PropertyValueFactory<Adh, String>("profession"));
        coltel.setCellValueFactory(new PropertyValueFactory<Adh, Integer>("tel"));
        colmail.setCellValueFactory(new PropertyValueFactory<Adh, String>("email"));
        tableau.setItems(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List();
        conn = getConnection();
        modifier.setDisable(true);
        supprimer.setDisable(true);
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

    @FXML
    void info(MouseEvent event) {
        selected = tableau.getSelectionModel().getSelectedItems().get(0);
        txtid.setText(String.valueOf(selected.getId()));
        txtnom.setText(selected.getNom());
        txtprn.setText(selected.getPrenom());
        txtpro.setText(selected.getProfession());
        txtel.setText(String.valueOf(selected.getTel()));
        txtmail.setText(selected.getEmail());
        ajouter.setDisable(true);
        modifier.setDisable(false);
        supprimer.setDisable(false);
    }
}
