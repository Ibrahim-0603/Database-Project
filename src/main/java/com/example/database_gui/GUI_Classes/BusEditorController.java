package com.example.database_gui.GUI_Classes;

import com.example.database_gui.dao.BusDAO;
import com.example.database_gui.model.Bus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BusEditorController extends BaseController {
    @FXML
    private TextField idInput, plateInput, modelInput, capInput, routeInput;
    private final BusDAO busDAO = new BusDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            Bus b = new Bus(idInput.getText(), plateInput.getText(), modelInput.getText(),
                    Integer.parseInt(capInput.getText()), routeInput.getText());
            busDAO.insertBus(b);
            switchScene("BusTable.fxml", event);
        } catch (Exception e) {
            showDatabaseError("Insertion failed", e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            Bus b = new Bus(idInput.getText(), plateInput.getText(), modelInput.getText(),
                    Integer.parseInt(capInput.getText()), routeInput.getText());
            busDAO.deleteBus(b);
            switchScene("BusTable.fxml", event);
        } catch (Exception e) {
            showDatabaseError("Delete failed", e);
        }
    }
}