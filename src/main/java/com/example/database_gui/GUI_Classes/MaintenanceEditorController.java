package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.ValidationException;
import com.example.database_gui.dao.MaintenanceDatesDAO;
import com.example.database_gui.model.MaintenanceDates;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

public class MaintenanceEditorController extends BaseController {
    @FXML
    private TextField busIdInput, dateInput;
    private final MaintenanceDatesDAO dao = new MaintenanceDatesDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            validateInput(busIdInput, dateInput);

            MaintenanceDates md = new MaintenanceDates(busIdInput.getText(), LocalDate.parse(dateInput.getText()));
            dao.insertMaintenanceDate(md);
            switchScene("MaintenanceDatesTable.fxml", event);
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
            validateInput(busIdInput, dateInput);

            MaintenanceDates md = new MaintenanceDates(busIdInput.getText(), LocalDate.parse(dateInput.getText()));
            dao.deleteMaintenanceDate(md);
            switchScene("MaintenanceDatesTable.fxml", event);
        } catch (ValidationException e) {
            showDatabaseError("Delete failed", e);
        }catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }
    }
}
