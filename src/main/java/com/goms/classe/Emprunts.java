package com.goms.classe;

import com.goms.model.Emp;
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
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.goms.classe.ConnexionBD.getConnection;

public class Emprunts implements Initializable {

    PreparedStatement st;
    ResultSet rs;
    private Emp selected;
    Connection conn = ConnexionBD.getConnection();

    @FXML
    private TextField adtel;

    @FXML
    private TextField adnom;

    @FXML
    private TextField adprn;

    @FXML
    private TextField adrech;

    @FXML
    private DatePicker dateE;

    @FXML
    private DatePicker dateR;

    @FXML
    private TextField laut;

    @FXML
    private TextField txtid;

    @FXML
    private ComboBox<String> ltitre;

    @FXML
    private TableView<Emp> tableau;

    @FXML
    private TableColumn<Emp, String> colid;

    @FXML
    private TableColumn<Emp, String> colnom;

    @FXML
    private TableColumn<Emp, String> colprn;

    @FXML
    private TableColumn<Emp, String> colivre;

    @FXML
    private TableColumn<Emp, String> coldate;



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
    void rechAdh(MouseEvent event) {
        String sql = "select * from adherents where id = '" + adrech.getText() + "'";
        int nbr =0;
        try {
            st = conn.prepareStatement(sql);
            rs  = st.executeQuery();
            if(rs.next()){
                adrech.setText(rs.getString("id"));
                adnom.setText(rs.getString("nom"));
                adprn.setText(rs.getString("prenom"));
                adtel.setText(rs.getString("tel"));
                adrech.setText("");
                nbr = 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (nbr==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Aucun Adhérent trouvé sous cet identifiant", ButtonType.OK);
            alert.showAndWait();
        }

    }




    @FXML
    void comb(ActionEvent event) {
        try {
            String sql = "select auteur from livre where titre = ?; ";
            st = conn.prepareStatement(sql);
            st.setString(1, ltitre.getEditor().getText());

            rs = st.executeQuery();
            if(rs.next()){
                laut.setText(rs.getString("auteur"));
            }
            rs.close();
            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
   public void combo(){

        try {
            String sql = "select titre,auteur from livre where disponible = 'oui' ";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ltitre.getItems().addAll(rs.getString("titre"));
                
            }
            rs.close();
            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @FXML
    void supprimer(ActionEvent event) {
         String sql = "delete from emprunts where id = '"+txtid.getText()+"' ";

        try {
            st=conn.prepareStatement(sql);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("VOULEZ-VOUS VRAIMENT ENLEVER CE LIVRE!!!");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();


             if (option.get() == ButtonType.OK) {
                 st.executeUpdate();
                 adnom.setText("");
                 adprn.setText("");
                 adtel.setText("");
                 ltitre.setValue(null);
                 txtid.setText("");

                 List();
            } else if (option.get() == ButtonType.CANCEL) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void imprimer(){
        /* Document doc =new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Facture.pdf"));
            doc.open();
            String format ="dd/MM/YY hh:mm";

            SimpleDateFormat formater=new SimpleDateFormat(format);
            java.util.Date date = new java.util.Date();
            doc.add(new Paragraph("Facture a : " + adnom.getText() + " "
                    + "" + adprn.getText() + ""
                    + "\nSon Telephone est :" + adtel.getText() + ""
                    + "\nLe Livre emprunté:" + ltitre.getValue() + " "
                    + "\nDe " + laut.getText() + ""
                    + "\n Il a emprunter le livre du" + dateE.getValue() + " au " + dateR.getValue() + " "
                    + "\nLomé le " + formater.format(date) + ""
                    + "\nSignature", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK)));
            doc.close();
            Desktop.getDesktop().open(new File("Facture.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public ObservableList<Emp> getEmpList(){
        ObservableList<Emp> emplist = FXCollections.observableArrayList();
        ResultSet rs;
        Statement st;
        Connection conn = ConnexionBD.getConnection();
        String sql = "SELECT * FROM emprunts ";
        try{
            st= conn.createStatement();
            rs= st.executeQuery(sql);
            Emp emp = null;
            while (rs.next()) {
                emp = new Emp(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"), rs.getInt("tel"), rs.getString("titre"), rs.getString("auteur"), rs.getString("dateE"), rs.getString("dateR"));
                emplist.add(emp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return emplist;
    }

    @FXML
    void emprunter(){

        String query = "INSERT INTO emprunts (`nom`, `prenom`, `tel`, `titre`, `auteur`,`dateE`,`dateR`) VALUES ('" + adnom.getText() + "','" + adprn.getText() + "','" + adtel.getText() + "','"
                + ltitre.getValue() + "','" + laut.getText() + "','" + dateE.getValue() + "','" + dateR.getValue() + "')";
        executeQuery(query);
        adnom.setText("");
        adprn.setText("");
        adtel.setText("");
        ltitre.setValue("");
        laut.setText("");
        dateE.setValue(null);
        dateR.setValue(null);

        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Livre ajouté avec success",ButtonType.OK);
        alert.showAndWait();
        List();
    }

    @FXML
    void info(MouseEvent event) {
        selected = tableau.getSelectionModel().getSelectedItems().get(0);
        txtid.setText(String.valueOf(selected.getId()));
        adnom.setText(selected.getTitre());
        adprn.setText(selected.getPrenom());
        adtel.setText(String.valueOf(selected.getTel()));
        ltitre.setValue(selected.getTitre());
        laut.setText(selected.getAuteur());
        /*dateE.setValue(String.valueOf(selected.getDateE()));
        dateR.setValue(String.valueOf(selected.getDateR()));*/
    }

    public void  List(){
        tableau.getItems().clear();

        ObservableList<Emp> list = getEmpList();
        colid.setCellValueFactory(new PropertyValueFactory<Emp,String>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<Emp,String>("nom"));
        colprn.setCellValueFactory(new PropertyValueFactory<Emp,String>("prenom"));
        colivre.setCellValueFactory(new PropertyValueFactory<Emp,String>("titre"));
        coldate.setCellValueFactory(new PropertyValueFactory<Emp,String>("dateR"));
        tableau.setItems(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection conn = ConnexionBD.getConnection();
       combo();
       List();
    }
}
