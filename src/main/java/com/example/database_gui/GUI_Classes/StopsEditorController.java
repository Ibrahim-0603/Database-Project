package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.ValidationException;
import com.example.database_gui.dao.StopsDAO;
import com.example.database_gui.model.Stops;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StopsEditorController extends BaseController {
    @FXML private TextField stopIdInput, nameInput, locationInput, routeIdInput;
    private final StopsDAO dao = new StopsDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            validateInput(stopIdInput, nameInput, locationInput, routeIdInput);

            Stops s = new Stops(stopIdInput.getText(), nameInput.getText(), locationInput.getText(), routeIdInput.getText());
            dao.insertStop(s);
            switchScene("stopsTable.fxml", event);
        } catch (ValidationException e) {
            showDatabaseError("Insertion failed", e);
        }catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            validateInput(stopIdInput, nameInput, locationInput, routeIdInput);

            Stops s = new Stops(stopIdInput.getText(), nameInput.getText(), locationInput.getText(), routeIdInput.getText());
            dao.deleteStop(s);
            switchScene("stopsTable.fxml", event);
        } catch (ValidationException e) {
            showDatabaseError("Delete failed", e);
        }catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }
    }
}
