package com.goms.classe;

import com.goms.model.Bnd;
import com.goms.model.Dic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
//import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.goms.classe.ConnexionBD.getConnection;

public class Bd implements Initializable {
    private Bnd selected;
    @FXML
    private TextField txtitre;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtdess;

    @FXML
    private TableView<Bnd> tableau;

    @FXML
    private TableColumn<Bnd, Integer> colid;

    @FXML
    private TableColumn<Bnd, String> coltitre;

    @FXML
    private TableColumn<Bnd, String> coldes;

    @FXML
    private Button ajouter;

    @FXML
    private Button modifier;

    @FXML
    private Button supprimer;

    PreparedStatement st;
    Connection conn = ConnexionBD.getConnection();

    @FXML
    void ajouter(ActionEvent event) {
        String query = "INSERT INTO bd (`titre`, `dessinateur`) VALUES ('" + txtitre.getText() + "','" + txtdess.getText() + "')";
        executeQuery(query);
        txtitre.setText("");
        txtdess.setText("");
        List();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Journal ajouté avec success",ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    void modifier(ActionEvent event) {

        String id = txtid.getText();
        String titre = txtitre.getText();
        String dess = txtdess.getText();



        String sql = "update bd set titre=?,dessinateur=? where id = '"+txtid.getText()+"' ";
        if (!titre.equals("") && !dess.equals("")) {
            try {
                st = conn.prepareStatement(sql);
                st.setString(1, titre);
                st.setString(2, dess);

                st.executeUpdate();
                txtid.setText("");
                txtitre.setText("");
                txtdess.setText("");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Jounal Modifié avec success", ButtonType.OK);
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

        String sql = "delete from bd where id = '"+txtid.getText()+"' ";

        try {
            st=conn.prepareStatement(sql);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("VOULEZ-VOUS VRAIMENT ENLEVER CETTE BANDE DESSINNEE!!!");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();


             if (option.get() == ButtonType.OK) {
                 st.executeUpdate();
                 txtitre.setText("");
                 txtdess.setText("");

                 List();
            } else if (option.get() == ButtonType.CANCEL) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

     public ObservableList<Bnd> getBndList(){
        ObservableList<Bnd> bndlist = FXCollections.observableArrayList();
        ResultSet rs;
        Statement st;
        Connection conn = ConnexionBD.getConnection();
        String sql = "SELECT * FROM bd ";
        try{
            st= conn.createStatement();
            rs= st.executeQuery(sql);
            Bnd bnd = null;
            while (rs.next()) {
                bnd = new Bnd(rs.getInt("id"), rs.getString("titre"), rs.getString("dessinateur"));
                bndlist.add(bnd);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return bndlist;
    }


    @FXML
    void bouger(MouseEvent event) {
        selected = tableau.getSelectionModel().getSelectedItems().get(0);
        txtid.setText(String.valueOf(selected.getId()));
        txtitre.setText(selected.getTitre());
        txtdess.setText(selected.getDessinateur());
    }

    public void  List(){
        tableau.getItems().clear();

         ObservableList<Bnd> list = getBndList();
        colid.setCellValueFactory(new PropertyValueFactory<Bnd,Integer>("id"));
        coltitre.setCellValueFactory(new PropertyValueFactory<Bnd,String>("titre"));
        coldes.setCellValueFactory(new PropertyValueFactory<Bnd,String>("dessinateur"));
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
