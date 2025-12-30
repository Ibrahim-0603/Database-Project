package com.example.database_gui.GUI_Classes;

import com.example.database_gui.dao.RouteDAO;
import com.example.database_gui.model.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RouteEditorController extends BaseController {
    @FXML
    private TextField idInput, nameInput, startInput, endInput;
    private final RouteDAO routeDAO = new RouteDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            Route r = new Route(idInput.getText(), nameInput.getText(), startInput.getText(), endInput.getText());
            routeDAO.insertRoute(r);
            switchScene("RouteTable.fxml", event);

        } catch (Exception e) {
            showDatabaseError("Insertion failed", e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            Route r = new Route(idInput.getText(), nameInput.getText(), startInput.getText(), endInput.getText());
            routeDAO.deleteRoute(r);
            switchScene("RouteTable.fxml", event);
        } catch (Exception e) {
            showDatabaseError("Delete failed", e);
        }
    }
}