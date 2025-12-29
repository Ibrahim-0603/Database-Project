package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.dao.BusDAO;
import com.example.database_gui.dao.DriverBusAssignmentDAO;
import com.example.database_gui.dao.DriverDAO;
import com.example.database_gui.model.DriverBusAssignment;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class driverBusController extends BaseController{
    @FXML
    private TableView<DriverBusAssignment> driverBusAssignmentTable;

    @FXML
    private ComboBox<String> driverBox;

    @FXML
    private ComboBox<String> busBox;

    @FXML
    private TableColumn<DriverBusAssignment, String> dIdCol;

    @FXML
    private TableColumn<DriverBusAssignment, String> bIdCol;

    @FXML
    private TableColumn<DriverBusAssignment, String> shiftCol;

    private final DriverBusAssignmentDAO driverBusAssignmentDAO = new DriverBusAssignmentDAO();
    private final BusDAO busDAO = new BusDAO();
    private final DriverDAO driverDAO = new DriverDAO();


    @FXML
    public void initialize(){
        dIdCol.setCellValueFactory(new PropertyValueFactory<>("driverID"));
        bIdCol.setCellValueFactory(new PropertyValueFactory<>("busID"));
        shiftCol.setCellValueFactory(new PropertyValueFactory<>("shift"));

        loadDriverIDs();
        loadBusIDs();

        driverBox.setOnAction(e -> loadByDriver());
        busBox.setOnAction(e -> loadByBus());
    }

    private void loadByDriver() {
        try {
            busBox.setValue(null);

            String driverID = driverBox.getValue();
            if (driverID != null) {
                driverBusAssignmentTable.setItems(
                        FXCollections.observableArrayList(driverBusAssignmentDAO.getBusesByDrivers(driverID)));
            }
        } catch (DatabaseException e) {
            showDatabaseError("Failed to load assignments for driver: " + driverBox.getValue(), e);        }
    }
    private void loadByBus() {
        try {
            driverBox.setValue(null);

            String busID = busBox.getValue();
            if (busID != null) {
                driverBusAssignmentTable.setItems(
                        FXCollections.observableArrayList(driverBusAssignmentDAO.getDriversByBuses(busID)));
            }
        } catch (DatabaseException e) {
            showDatabaseError("Failed to load assignments for bus: " + busBox.getValue(), e);
        }
    }
    private void loadDriverIDs() {
        driverBox.setItems(
                FXCollections.observableArrayList(
                        driverDAO.getAllDrivers().stream().map(driver -> driver.getId()).toList()));
    }
    private void loadBusIDs() {
        busBox.setItems(
                FXCollections.observableArrayList(busDAO.getAllBuses().stream().map(bus -> bus.getBusID()).toList()));
    }
}
