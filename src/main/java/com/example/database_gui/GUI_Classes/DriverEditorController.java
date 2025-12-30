package com.example.database_gui.GUI_Classes;

import com.example.database_gui.dao.DriverDAO;
import com.example.database_gui.model.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DriverEditorController extends BaseController {
    @FXML
    private TextField idInput, nameInput, licenseInput;
    private final DriverDAO driverDAO = new DriverDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            Driver d = new Driver(idInput.getText(), nameInput.getText(), licenseInput.getText());
            driverDAO.insertDriver(d);
            switchScene("driverTable.fxml", event);

        } catch (Exception e) {
            showDatabaseError("Insertion failed", e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            Driver d = new Driver(idInput.getText(), nameInput.getText(), licenseInput.getText());
            driverDAO.deleteDriver(d);
            switchScene("driverTable.fxml", event);
        } catch (Exception e) {
            showDatabaseError("Delete failed", e);
        }
    }

}