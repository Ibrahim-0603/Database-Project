package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.dao.BusPerTimeSlotDAO;
import com.example.database_gui.model.BusesPerTimeSlot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalTime;

public class BusTimeslotController extends BaseController {
    @FXML private TableView<  BusesPerTimeSlot> table;
    @FXML private TableColumn<BusesPerTimeSlot, String> routeCol;
    @FXML private TableColumn<BusesPerTimeSlot, String> dayCol;
    @FXML private TableColumn<BusesPerTimeSlot, LocalTime> timeCol;
    @FXML private TableColumn<BusesPerTimeSlot, Integer> numStudentsCol;
    @FXML private TableColumn<BusesPerTimeSlot, Integer> bigCol;
    @FXML private TableColumn<BusesPerTimeSlot, Integer> medCol;
    @FXML private TableColumn<BusesPerTimeSlot, Integer> baseCol;
    @FXML private TableColumn<BusesPerTimeSlot, Integer> extraCol;

    private final BusPerTimeSlotDAO dao = new BusPerTimeSlotDAO();

    @FXML
    public void initialize() {
        routeCol.setCellValueFactory(new PropertyValueFactory<>("routeName"));
        dayCol.setCellValueFactory(new PropertyValueFactory<>("dayOfWeek"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        numStudentsCol.setCellValueFactory(new PropertyValueFactory<>("numStudents"));
        bigCol.setCellValueFactory(new PropertyValueFactory<>("bigBuses60"));
        medCol.setCellValueFactory(new PropertyValueFactory<>("medBuses40"));
        baseCol.setCellValueFactory(new PropertyValueFactory<>("baseBus24"));
        extraCol.setCellValueFactory(new PropertyValueFactory<>("extraSpare24"));

        try {
        ObservableList<BusesPerTimeSlot> list = FXCollections.observableArrayList(dao.getBusesNeeded());
        table.setItems(list);
        }
        catch (DatabaseException e) {
            showDatabaseError("Could not show query", e);
        }
    }
}
