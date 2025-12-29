package com.example.database_gui.GUI_Classes;

import com.example.database_gui.dao.RouteDAO;
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

public class RouteController {

    @FXML
    private TableView<Route> routeTable;

    @FXML
    private TableColumn<Route, String> idCol;

    @FXML
    private TableColumn<Route, String> nameCol;

    @FXML
    private TableColumn<Route, String> startCol;

    @FXML
    private TableColumn<Route, String> endCol;

    private final RouteDAO routeDAO = new RouteDAO();

    @FXML
    public void initialize(){

        idCol.setCellValueFactory(new PropertyValueFactory<>("routeID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("routeName"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startLocation"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endLocation"));

        ObservableList<Route> routes = FXCollections.observableArrayList(routeDAO.getAllRoutes());

        routeTable.setItems(routes);
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/com/example/database_gui/fxml/Main.fxml")
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


}
