module Bibliot {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens com.goms.classe to javafx.fxml;
    exports com.goms.classe;
    exports com.goms;
    exports com.goms.model;
    opens com.goms.model to javafx.fxml;
}