package com.goms;

import com.goms.classe.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Login.loadview(stage);


    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
