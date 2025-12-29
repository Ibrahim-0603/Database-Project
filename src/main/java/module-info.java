module com.example.database_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires javafx.base;
    requires java.compiler;
    requires javafx.graphics;
    requires java.desktop;


    exports com.example.database_gui;

    opens com.example.database_gui.model to javafx.base;
    opens com.example.database_gui.GUI_Classes to javafx.fxml;
    opens com.example.database_gui to javafx.fxml;
    exports com.example.database_gui.Exceptions;
    opens com.example.database_gui.Exceptions to javafx.fxml;

}