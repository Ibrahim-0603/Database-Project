package com.example.database_gui.GUI_Classes;

import com.example.database_gui.dao.DriverPhoneDAO;
import com.example.database_gui.model.DriverPhone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StudentPhoneEditorController extends BaseController {
    @FXML
    private TextField driverIdInput, phoneInput;
    private final DriverPhoneDAO dao = new DriverPhoneDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            DriverPhone dp = new DriverPhone(driverIdInput.getText(), phoneInput.getText());
            dao.insertPhone(dp);
            // You might need to create a phoneTable.fxml if it doesn't exist
            switchScene("StudentPhoneTable.fxml", event);
        } catch (Exception e) {
            showDatabaseError("Insertion failed", e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            DriverPhone sp = new DriverPhone(driverIdInput.getText(), phoneInput.getText());
            dao.deletePhone(sp);
            switchScene("StudentPhoneTable.fxml", event);
        } catch (Exception e) {
            showDatabaseError("Delete failed", e);
        }
    }
}
