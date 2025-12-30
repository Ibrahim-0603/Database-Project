package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.Main;
import com.example.database_gui.dao.MaintenanceDatesDAO;
import com.example.database_gui.model.MaintenanceDates;
import com.example.database_gui.model.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MaintenanceDatesController extends BaseController {
    @FXML
    private TableView<MaintenanceDates> maintenanceDatesTable;

    @FXML
    private TableColumn<MaintenanceDates, String> idCol;

    @FXML
    private TableColumn<MaintenanceDates, String> dateCol;

    private final MaintenanceDatesDAO maintenanceDatesDAO = new MaintenanceDatesDAO();

    @FXML
    public void initialize(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("busID"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("maintenanceDate"));

        try {
            ObservableList<MaintenanceDates> dates = FXCollections.observableArrayList(maintenanceDatesDAO.getAllMaintenanceDates());

            maintenanceDatesTable.setItems(dates);
        } catch (DatabaseException e) {
            showDatabaseError("Could not show maintenance dates", e);
        }
    }
    @Override
    protected void switchScene(String fxmlFile, ActionEvent event) {
        try {
            super.switchScene(fxmlFile, event);
        }
        catch (IOException e){
            showDatabaseError("Cannot open editor", e);
        }
    }

    @FXML
    protected void handleEditButton(ActionEvent event){
        this.switchScene("MaintenanceDatesTableOptions.fxml", event);
    }

}
