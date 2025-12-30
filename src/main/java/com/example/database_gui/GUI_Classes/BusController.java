package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.dao.BusDAO;
import com.example.database_gui.model.Bus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;


public class BusController extends BaseController {
    @FXML
    private TableView<Bus> busTable;

    @FXML
    private TableColumn<Bus, String> idCol;
    @FXML
    private TableColumn<Bus, String> PlateCol;
    @FXML
    private TableColumn<Bus, String> ModelCol;
    @FXML
    private TableColumn<Bus, String> CapCol;
    @FXML
    private TableColumn<Bus, String> RouteCol;

    private final BusDAO busDAO = new BusDAO();

    @FXML
    public void initialize(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("busID"));
        PlateCol.setCellValueFactory(new PropertyValueFactory<>("plateNum"));
        ModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        CapCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        RouteCol.setCellValueFactory(new PropertyValueFactory<>("routeID"));


        try {
            ObservableList<Bus> buses = FXCollections.observableArrayList(busDAO.getAllBuses());
            if (buses.isEmpty()) {
                throw new DatabaseException("No buses found");
            }
            busTable.setItems(buses);
        } catch (DatabaseException e) {
            showDatabaseError("Could not show buses", e);
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
        this.switchScene("BusTableOptions.fxml", event);
    }
}


