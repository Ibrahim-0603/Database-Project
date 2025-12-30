package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.ValidationException;
import com.example.database_gui.dao.TripDAO;
import com.example.database_gui.model.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TripEditorController extends BaseController {
    @FXML
    private TextField routeIdInput, tripNumInput, arrTimeInput, depTimeInput, dateInput, driverIdInput, busIdInput;
    private final TripDAO tripDAO = new TripDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            validateInput(routeIdInput, tripNumInput, arrTimeInput, depTimeInput, dateInput, driverIdInput, busIdInput);

            Trip t = new Trip(routeIdInput.getText(), Integer.parseInt(tripNumInput.getText()),
                    LocalTime.parse(arrTimeInput.getText()), LocalTime.parse(depTimeInput.getText()),
                    LocalDate.parse(dateInput.getText()), driverIdInput.getText(), busIdInput.getText());
            tripDAO.insertTrip(t);
            switchScene("tripTable.fxml", event);

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
            validateInput(routeIdInput, tripNumInput, arrTimeInput, depTimeInput, dateInput, driverIdInput, busIdInput);

            Trip t = new Trip(routeIdInput.getText(), Integer.parseInt(tripNumInput.getText()),
                    LocalTime.parse(arrTimeInput.getText()), LocalTime.parse(depTimeInput.getText()),
                    LocalDate.parse(dateInput.getText()), driverIdInput.getText(), busIdInput.getText());
            tripDAO.deleteTrip(t);
            switchScene("tripTable.fxml", event);
        } catch (ValidationException e) {
            showDatabaseError("Delete failed", e);
        }catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }
    }
}