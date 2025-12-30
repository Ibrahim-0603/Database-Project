package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.ValidationException;
import com.example.database_gui.dao.RouteDAO;
import com.example.database_gui.model.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RouteEditorController extends BaseController {
    @FXML
    private TextField idInput, nameInput, startInput, endInput;
    private final RouteDAO routeDAO = new RouteDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            validateInput(idInput, nameInput, startInput, endInput);

            Route r = new Route(idInput.getText(), nameInput.getText(), startInput.getText(), endInput.getText());
            routeDAO.insertRoute(r);
            switchScene("RouteTable.fxml", event);

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
            validateInput(idInput, nameInput, startInput, endInput);

            Route r = new Route(idInput.getText(), nameInput.getText(), startInput.getText(), endInput.getText());
            routeDAO.deleteRoute(r);
            switchScene("RouteTable.fxml", event);
        } catch (ValidationException e) {
            showDatabaseError("Delete failed", e);
        }catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }
    }
}