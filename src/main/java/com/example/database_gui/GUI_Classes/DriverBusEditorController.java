package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.ValidationException;
import com.example.database_gui.dao.BusDAO;
import com.example.database_gui.dao.DriverBusAssignmentDAO;
import com.example.database_gui.dao.DriverDAO;
import com.example.database_gui.model.Bus;
import com.example.database_gui.model.Driver;
import com.example.database_gui.model.DriverBusAssignment;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.stream.Collectors;

public class DriverBusEditorController extends BaseController {
    @FXML private TextField shiftInput;
    @FXML private ComboBox<String> driverIdCombo;
    @FXML private ComboBox<String> busIdCombo;

    private final DriverDAO driverDAO = new DriverDAO();
    private final BusDAO busDAO = new BusDAO();
    private final DriverBusAssignmentDAO dao = new DriverBusAssignmentDAO();

    @FXML
    public void initialize() {
        try {
            driverIdCombo.setItems(FXCollections.observableArrayList(
                    driverDAO.getAllDrivers().stream().map(Driver::getId).collect(Collectors.toList())
            ));

            busIdCombo.setItems(FXCollections.observableArrayList(
                    busDAO.getAllBuses().stream().map(Bus::getBusID).collect(Collectors.toList())
            ));
        } catch (Exception e) {
            showDatabaseError("Failed to load IDs", e);
        }
    }
    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            validateInput(driverIdCombo, busIdCombo);
            validateInput(shiftInput);

            DriverBusAssignment dba = new DriverBusAssignment(
                    driverIdCombo.getValue(),
                    busIdCombo.getValue(),
                    shiftInput.getText()
            );
            dao.insertAssignment(dba);
            switchScene("driverBusTable.fxml", event);
        } catch (ValidationException e) {
            showDatabaseError("Insertion failed", e);
        } catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            validateInput(driverIdCombo, busIdCombo);
            validateInput(shiftInput);

            DriverBusAssignment dba = new DriverBusAssignment(
                    driverIdCombo.getValue(),
                    busIdCombo.getValue(),
                    shiftInput.getText()
            );
            dao.deleteAssignment(dba);
            switchScene("driverBusTable.fxml", event);
        } catch (ValidationException e) {
            showDatabaseError("Delete failed", e);
        } catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }
    }
}
