package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.dao.StudentDAO;
import com.example.database_gui.model.Student;
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

public class StudentController extends BaseController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> idCol;
    @FXML
    private TableColumn <Student, String> NameCol;
    @FXML
    private TableColumn <Student, String> EmailCol;
    @FXML
    private TableColumn <Student, String> DeptCol;
    @FXML
    private TableColumn <Student, String> RouteCol;

    private final StudentDAO studentDAO = new StudentDAO();

    @FXML
    public void initialize(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        DeptCol.setCellValueFactory(new PropertyValueFactory<>("Department"));
        RouteCol.setCellValueFactory(new PropertyValueFactory<>("RouteID"));

        try {
            ObservableList<Student> students = FXCollections.observableArrayList(studentDAO.getAllStudents());
            studentTable.setItems(students);
        } catch (DatabaseException e) {
            showDatabaseError("Could not show students", e);
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
        this.switchScene("StudentTableOptions.fxml", event);
    }
}
