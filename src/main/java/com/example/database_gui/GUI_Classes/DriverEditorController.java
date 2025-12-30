package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.ValidationException;
import com.example.database_gui.dao.DriverDAO;
import com.example.database_gui.model.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DriverEditorController extends BaseController {
    @FXML
    private TextField idInput, nameInput, licenseInput;
    private final DriverDAO driverDAO = new DriverDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            validateInput(idInput, nameInput, licenseInput);

            Driver d = new Driver(idInput.getText(), nameInput.getText(), licenseInput.getText());
            driverDAO.insertDriver(d);
            switchScene("driverTable.fxml", event);

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
            validateInput(idInput, nameInput, licenseInput);

            Driver d = new Driver(idInput.getText(), nameInput.getText(), licenseInput.getText());
            driverDAO.deleteDriver(d);
            switchScene("driverTable.fxml", event);
        } catch (ValidationException e) {
            showDatabaseError("Delete failed", e);
        }catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }
    }

}