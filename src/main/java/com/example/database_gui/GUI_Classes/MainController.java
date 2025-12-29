package com.example.database_gui.GUI_Classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private void switchScene(String fxmlFile, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/com/example/database_gui/fxml/" + fxmlFile)
            );

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openStudents(ActionEvent event) {

        switchScene("studentTable.fxml", event);
    }

    @FXML
    private void openRoutes(ActionEvent event) {
        switchScene("routeTable.fxml", event);
    }

    @FXML
    private void openTrips(ActionEvent event) {
        switchScene("tripTable.fxml", event);
    }

    @FXML
    private void openBuses(ActionEvent event) {
        switchScene("busTable.fxml", event);
    }

    @FXML
    private void openDrivers(ActionEvent event) {
        switchScene("driverTable.fxml", event);
    }
    @FXML
    private void openSchedules(ActionEvent event) {
        switchScene("studentScheduleTable.fxml", event);
    }
    @FXML
    private void openMaintenanceDates(ActionEvent event) {
        switchScene("MaintenanceDatesTable.fxml", event);
    }
    @FXML
    private void openStops(ActionEvent event) {
        switchScene("stopsTable.fxml", event);
    }
    @FXML
    private void openAssignments(ActionEvent event) {
        switchScene("driverBusTable.fxml", event);
    }
    @FXML
    private void openBusesPerTimeSlot(ActionEvent event) {
        switchScene("BusPerTimeSlotQuery.fxml", event);
    }


}
