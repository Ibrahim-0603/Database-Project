package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.ValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public abstract class BaseController {

    protected void showDatabaseError(String header, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText(header);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
    protected void showDatabaseError(Exception e){
        showDatabaseError("Error", e);
    }

    protected void validateInput(TextField... fields) throws ValidationException {
        for (TextField field : fields) {
            if (field.getText() == null || field.getText().trim().isEmpty()) {
                throw new ValidationException("All fields must be filled out!");
            }
        }
    }
    protected void validateInput(ComboBox<?>... boxes)throws ValidationException{
        for(ComboBox<?> box : boxes){
            if(box.getValue()==null){
                throw new ValidationException("Please make a selection in all dropdowns!");
            }
        }
    }

    @FXML
    protected void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource("/com/example/database_gui/fxml/Main_Islam.fxml"))
            );

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void switchScene(String fxmlFile, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/database_gui/fxml/" + fxmlFile)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
