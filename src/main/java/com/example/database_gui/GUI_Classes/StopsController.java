package com.example.database_gui.GUI_Classes;

import com.example.database_gui.dao.StopsDAO;
import com.example.database_gui.model.Stops;
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

public class StopsController extends BaseController {
    @FXML
    private TableView<Stops> stopsTable;
    @FXML
    private TableColumn<Stops, String> idCol;
    @FXML
    private TableColumn<Stops, String> nameCol;
    @FXML
    private TableColumn<Stops, String> locCol;
    @FXML
    private TableColumn<Stops, String> routeCol;

    private final StopsDAO stopsDAO = new StopsDAO();

    @FXML
    public void initialize(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("stopID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("stopName"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        routeCol.setCellValueFactory(new PropertyValueFactory<>("routeID"));

        try {
            ObservableList<Stops> stops = FXCollections.observableArrayList(stopsDAO.getAllStops());
            stopsTable.setItems(stops);
        } catch (Exception e) {
            showDatabaseError("Could not show stops", e);
        }
    }
}
