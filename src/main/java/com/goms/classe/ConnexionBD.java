package com.goms.classe;
import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Class.forName;

public class ConnexionBD {
    static Connection conn;
    //public Connection conn;

    public static Connection getConnection() {
        String user = "root";
        String pass = "";
        String url = "jdbc:mysql://localhost:3306/biblio?serverTimezone=UTC";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connexion réussie");
        }catch (Exception e){
            e.printStackTrace();
        }

        return conn;
    }
}
