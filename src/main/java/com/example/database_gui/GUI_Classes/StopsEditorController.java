package com.example.database_gui.GUI_Classes;

import com.example.database_gui.dao.StopsDAO;
import com.example.database_gui.model.Stops;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StopsEditorController extends BaseController {
    @FXML private TextField stopIdInput, nameInput, locationInput, routeIdInput;
    private final StopsDAO dao = new StopsDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            Stops s = new Stops(stopIdInput.getText(), nameInput.getText(), locationInput.getText(), routeIdInput.getText());
            dao.insertStop(s);
            openEditor(event, "stopsTable.fxml");
        } catch (Exception e) {
            showDatabaseError("Insertion failed", e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            Stops s = new Stops(stopIdInput.getText(), nameInput.getText(), locationInput.getText(), routeIdInput.getText());
            dao.deleteStop(s);
            openEditor(event, "stopsTable.fxml");
        } catch (Exception e) {
            showDatabaseError("Delete failed", e);
        }
    }
}
