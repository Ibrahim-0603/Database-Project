package com.example.database_gui.GUI_Classes;

import com.example.database_gui.dao.DriverBusAssignmentDAO;
import com.example.database_gui.model.DriverBusAssignment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DriverBusEditorController extends BaseController {
    @FXML private TextField driverIdInput, busIdInput, shiftInput;
    private final DriverBusAssignmentDAO dao = new DriverBusAssignmentDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            DriverBusAssignment dba = new DriverBusAssignment(driverIdInput.getText(), busIdInput.getText(), shiftInput.getText());
            dao.insertAssignment(dba);
        } catch (Exception e) {
            showDatabaseError("Insertion failed", e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            DriverBusAssignment dba = new DriverBusAssignment(driverIdInput.getText(), busIdInput.getText(), shiftInput.getText());
            dao.deleteAssignment(dba);
        } catch (Exception e) {
            showDatabaseError("Delete failed", e);
        }
    }
}
