package com.example.database_gui.GUI_Classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InputUserController extends BaseController{
    @FXML
    private TextField userText;

    @FXML
    private TextField passText;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = userText.getText();
        String password = passText.getText();

        // 1. Check credentials
        if ("admin".equals(username) && "admin".equals(password)) {
            navigateToMain(event);
        } else {
            // 2. Show error using the inherited method from BaseController
            showDatabaseError("Access Denied", new Exception("Invalid username or password. Could not access."));
        }
    }

    private void navigateToMain(ActionEvent event) {
        try {
            // Load the Main dashboard FXML
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/database_gui/fxml/Main_Islam.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showDatabaseError("Navigation Error", e);
        }
    }
}
