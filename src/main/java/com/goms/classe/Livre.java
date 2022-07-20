package com.goms.classe;

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

public class Livre implements Initializable {
    @FXML
    private TextField txtitre;

    @FXML
    private TextField txtauteur;

    @FXML
    private TextField txtannee;

    @FXML
    private TextField txtmais;

    @FXML
    private TextField txtRech;

    @FXML
    private TextField txtid;

    @FXML
    private Button ajouter;

    @FXML
    private Button modifier;

    @FXML
    private Button supprimer;

    @FXML
    private Button search;

    private Liv selected;

    @FXML
    private TableView<Liv> tableau;

    @FXML
    private TableColumn<Liv, String> coltitre;

    @FXML
    private TableColumn<Liv, String> colaut;

    @FXML
    private TableColumn<Liv, Integer> colan;

    @FXML
    private TableColumn<Liv, String> colmais;

    @FXML
    private TableColumn<Liv, String> coldisp;

    @FXML
    private ComboBox<String> comboBox;
    ObservableList<String> list = FXCollections.observableArrayList("Oui","Non");
    Connection conn = ConnexionBD.getConnection();
    PreparedStatement st;
    ResultSet rs;


    @FXML
    void reset(ActionEvent event) {
        txtid.setText("");
        txtitre.setText("");
        txtauteur.setText("");
        txtannee.setText("");
        txtmais.setText("");
        comboBox.setValue("");
        txtRech.setText("");
        ajouter.setDisable(false);
        modifier.setDisable(true);
        supprimer.setDisable(true);
    }


    @FXML
    void ajouter(ActionEvent event) {



        String query = "INSERT INTO livre (`titre`, `auteur`, `annee`, `edition`, `disponible`) VALUES ('" + txtitre.getText() + "','" + txtauteur.getText() + "','" + txtannee.getText() + "','"
                + txtmais.getText() + "','" + comboBox.getValue() + "')";
        executeQuery(query);
        txtitre.setText("");
        txtauteur.setText("");
        txtannee.setText("");
        txtRech.setText("");
        txtmais.setText("");
        comboBox.setValue(null);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Livre ajouté avec success",ButtonType.OK);
        alert.showAndWait();
        List();

    }

    @FXML
    void modifier(ActionEvent event) {
        String titre = txtitre.getText();
        String auteur = txtauteur.getText();
        String annee = txtannee.getText();
        String edition = txtmais.getText();
        String dispo = comboBox.getValue();


        String sql = "update livre set titre=?,auteur=?,annee=?,edition=?,disponible=? where id = '"+txtid.getText()+"' ";
        if (!titre.equals("") && !auteur.equals("") && !edition.equals("") && !annee.equals(null)) {
            try {
                st = conn.prepareStatement(sql);
                st.setString(1, titre);
                st.setString(2, auteur);
                st.setString(3, annee);
                st.setString(4, edition);
                st.setString(5, dispo);
                st.executeUpdate();
                txtid.setText("");
                txtitre.setText("");
                txtauteur.setText("");
                txtannee.setText("");
                txtmais.setText("");
                txtRech.setText("");
                comboBox.setValue(null);
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
        ObservableList<Liv> data = FXCollections.observableArrayList();
        String sql = "select * from livre where titre like ?" ;
        try{
            st= conn.prepareStatement(sql);
            st.setString(1, "%"+txtRech.getText()+"%");
            rs= st.executeQuery();
            Liv liv = null;
            while (rs.next()) {
                liv = new Liv( rs.getInt("id"),rs.getString("titre"), rs.getString("auteur"), rs.getInt("annee"), rs.getString("edition"), rs.getString("disponible"));
                data.add(liv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        coltitre.setCellValueFactory(new PropertyValueFactory<Liv,String>("titre"));
        colaut.setCellValueFactory(new PropertyValueFactory<Liv,String>("auteur"));
        colan.setCellValueFactory(new PropertyValueFactory<Liv,Integer>("annee"));
        colmais.setCellValueFactory(new PropertyValueFactory<Liv,String>("edition"));
        coldisp.setCellValueFactory(new PropertyValueFactory<Liv,String>("disponible"));
        tableau.setItems(data);
    }

    @FXML
    void search(KeyEvent event) {

       tableau.getItems().clear();
        ObservableList<Liv> data = FXCollections.observableArrayList();
        String sql = "select * from livre where titre like ?" ;
        try{
            st= conn.prepareStatement(sql);
            st.setString(1, "%"+txtRech.getText()+"%");
            rs= st.executeQuery();
            Liv liv = null;
            while (rs.next()) {
                liv = new Liv( rs.getInt("id"),rs.getString("titre"), rs.getString("auteur"), rs.getInt("annee"), rs.getString("edition"), rs.getString("disponible"));
                data.add(liv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
          coltitre.setCellValueFactory(new PropertyValueFactory<Liv,String>("titre"));
        colaut.setCellValueFactory(new PropertyValueFactory<Liv,String>("auteur"));
        colan.setCellValueFactory(new PropertyValueFactory<Liv,Integer>("annee"));
        colmais.setCellValueFactory(new PropertyValueFactory<Liv,String>("edition"));
        coldisp.setCellValueFactory(new PropertyValueFactory<Liv,String>("disponible"));
        tableau.setItems(data);

        }


    @FXML
    void supprimer(ActionEvent event) {
         String sql = "delete from livre where id = '"+txtid.getText()+"' ";

        try {
            st=conn.prepareStatement(sql);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("VOULEZ-VOUS VRAIMENT ENLEVER CE LIVRE!!!");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();


             if (option.get() == ButtonType.OK) {
                 st.executeUpdate();
                 txtitre.setText("");
                 txtauteur.setText("");
                 txtannee.setText("");
                 txtmais.setText("");
                 txtRech.setText("");
                 comboBox.setValue(null);

                 List();
            } else if (option.get() == ButtonType.CANCEL) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public ObservableList<Liv> getLivList(){
        ObservableList<Liv> livlist = FXCollections.observableArrayList();
        ResultSet rs;
        Statement st;
        Connection conn = ConnexionBD.getConnection();
        String sql = "SELECT * FROM livre ";
        try{
            st= conn.createStatement();
            rs= st.executeQuery(sql);
            Liv liv = null;
            while (rs.next()) {
                liv = new Liv( rs.getInt("id"),rs.getString("titre"), rs.getString("auteur"), rs.getInt("annee"), rs.getString("edition"), rs.getString("disponible"));
                livlist.add(liv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return livlist;
    }

    public void  List(){
        tableau.getItems().clear();

         ObservableList<Liv> list = getLivList();
        coltitre.setCellValueFactory(new PropertyValueFactory<Liv,String>("titre"));
        colaut.setCellValueFactory(new PropertyValueFactory<Liv,String>("auteur"));
        colan.setCellValueFactory(new PropertyValueFactory<Liv,Integer>("annee"));
        colmais.setCellValueFactory(new PropertyValueFactory<Liv,String>("edition"));
        coldisp.setCellValueFactory(new PropertyValueFactory<Liv,String>("disponible"));
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
        comboBox.setItems(list);
        conn = getConnection();
        modifier.setDisable(false);
        supprimer.setDisable(false);
    }

    @FXML
    void info(MouseEvent event) {
        selected = tableau.getSelectionModel().getSelectedItems().get(0);
        txtid.setText(String.valueOf(selected.getId()));
        txtitre.setText(selected.getTitre());
        txtauteur.setText(selected.getAuteur());
        txtannee.setText(String.valueOf(selected.getAnnee()));
        txtmais.setText(selected.getEdition());
        comboBox.setValue(selected.getDisponible());
        ajouter.setDisable(true);
        modifier.setDisable(false);
        supprimer.setDisable(false);
    }
}
