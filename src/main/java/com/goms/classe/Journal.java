package com.goms.classe;

import com.goms.model.Dic;
import com.goms.model.Jour;
import com.goms.model.Liv;
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
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.goms.classe.ConnexionBD.getConnection;

public class Journal implements Initializable {

    private Jour selected;

    @FXML
    private Button ajouter;

    @FXML
    private Button modifier;

    @FXML
    private TableView<Jour> tableau;

    @FXML
    private TextField txtitre;

    @FXML
    private TextField txtid;

    @FXML
    private DatePicker testate;

    @FXML
    private TableColumn<Jour, Integer> colid;

    @FXML
    private TableColumn<Jour, String> coltitre;

    @FXML
    private TableColumn<Jour, Date> coldate;

    @FXML
    private Button supprimer;

    PreparedStatement st;
    Connection conn = ConnexionBD.getConnection();


    @FXML
    void ajouter(ActionEvent event) {
        String query = "INSERT INTO journal (`titre`, `date`) VALUES ('" + txtitre.getText() + "','" + testate.getValue()+ "')";
        executeQuery(query);
        txtitre.setText("");
        testate.setValue(null);
        List();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Journal ajouté avec success",ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    void modifier(ActionEvent event) {

        String id = txtid.getText();
        String titre = txtitre.getText();
        String date = String.valueOf(testate.getValue());



        String sql = "update journal set titre=?,date=? where id = '"+txtid.getText()+"' ";
        if (!titre.equals("") && !date.equals("")) {
            try {
                st = conn.prepareStatement(sql);
                st.setString(1, titre);
                st.setString(2, date);

                st.executeUpdate();
                txtid.setText("");
                txtitre.setText("");
                testate.setValue(null);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Journal Modifié avec success", ButtonType.OK);
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

        String sql = "delete from journal where id = '"+txtid.getText()+"' ";

        try {
            st=conn.prepareStatement(sql);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("VOULEZ-VOUS VRAIMENT ENLEVER CE Journal!!!");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();


            if (option.get() == ButtonType.OK) {
                st.executeUpdate();
                txtitre.setText("");
                txtid.setText("");
                testate.setValue(null);

                List();
            } else if (option.get() == ButtonType.CANCEL) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void bouger(MouseEvent event) {
        selected = tableau.getSelectionModel().getSelectedItems().get(0);
        txtid.setText(String.valueOf(selected.getId()));
        txtitre.setText(selected.getTitre());
        testate.setValue(LocalDate.parse(selected.getDate().toString()));
    }


     public ObservableList<Jour> getJourList(){
        ObservableList<Jour> jourlist = FXCollections.observableArrayList();
        ResultSet rs;
        Statement st;
        Connection conn = ConnexionBD.getConnection();
        String sql = "SELECT * FROM journal ";
        try{
            st= conn.createStatement();
            rs= st.executeQuery(sql);
            Jour jour = null;
            while (rs.next()) {
                jour = new Jour(rs.getInt("id"), rs.getString("titre"), rs.getDate("date"));
                jourlist.add(jour);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return jourlist;
    }



    public void  List(){
        tableau.getItems().clear();

         ObservableList<Jour> list = getJourList();
        colid.setCellValueFactory(new PropertyValueFactory<Jour,Integer>("id"));
        coltitre.setCellValueFactory(new PropertyValueFactory<Jour,String>("titre"));
        coldate.setCellValueFactory(new PropertyValueFactory<Jour,Date>("date"));
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
