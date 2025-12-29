package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.dao.RouteDAO;
import com.example.database_gui.dao.TripDAO;
import com.example.database_gui.model.Route;
import com.example.database_gui.model.Trip;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class TripController extends BaseController{

    @FXML private ComboBox<String> routeBox;
    @FXML private TableView<Trip> tripTable;

    @FXML private TableColumn<Trip, String> tripIdCol;
    @FXML private TableColumn<Trip, String> busIdCol;
    @FXML private TableColumn<Trip, String> routeIdCol;

    @FXML private TableColumn<Trip, String> driverCol;
    @FXML private TableColumn<Trip, String> dateCol;
    @FXML private TableColumn<Trip, String> depCol;
    @FXML private TableColumn<Trip, String> arrCol;

    private final TripDAO tripDAO = new TripDAO();
    private final RouteDAO routeDAO = new RouteDAO();

    @FXML
    public void initialize() {

        tripIdCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        busIdCol.setCellValueFactory(new PropertyValueFactory<>("busID"));
        driverCol.setCellValueFactory(new PropertyValueFactory<>("driverID"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        depCol.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrCol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));

        routeBox.setItems(FXCollections.observableArrayList(routeDAO.getAllRoutes().stream().map(Route::getRouteID).toList()));

        routeBox.setOnAction(e -> loadTrips());
    }

    private void loadTrips() {
        try {
            String routeID = routeBox.getValue();

            if (routeID != null) {
                tripTable.setItems(FXCollections.observableArrayList(tripDAO.getTripsByRoute(routeID)));
            }
        } catch (DatabaseException e) {
            showDatabaseError("Could not show trips", e);
        }
    }
}
