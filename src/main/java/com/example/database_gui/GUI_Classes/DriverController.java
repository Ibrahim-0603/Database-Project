package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.dao.DriverDAO;
import com.example.database_gui.model.Driver;
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

public class DriverController extends BaseController{

    @FXML
    private TableView<Driver> driverTable;
    @FXML
    private TableColumn<Driver, String> idCol;
    @FXML
    private TableColumn<Driver, String> nameCol;
    @FXML
    private TableColumn<Driver, String> numCol;

    private final DriverDAO driverDAO = new DriverDAO();

    @FXML
    private  void initialize(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        numCol.setCellValueFactory(new PropertyValueFactory<>("LicenseNum"));

        try {
            ObservableList<Driver> drivers = FXCollections.observableArrayList(driverDAO.getAllDrivers());

            driverTable.setItems(drivers);
        } catch (DatabaseException e) {
            showDatabaseError("Could not show drivers", e);
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
        this.switchScene("DriverTableOptions.fxml", event);
    }

}
