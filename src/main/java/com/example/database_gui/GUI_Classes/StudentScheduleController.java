package com.example.database_gui.GUI_Classes;

import com.example.database_gui.dao.StudentDAO;
import com.example.database_gui.dao.StudentScheduleDAO;
import com.example.database_gui.model.Student;
import com.example.database_gui.model.StudentSchedule;
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

public class StudentScheduleController extends BaseController {

    @FXML private ComboBox<String> studentIdBox;
    @FXML
    private TableView<StudentSchedule> ScheduleTable;

    @FXML private TableColumn<StudentSchedule, String> idCol;
    @FXML private TableColumn<StudentSchedule, String> schedIdCol;
    @FXML private TableColumn<StudentSchedule, String> DoWCol;
    @FXML private TableColumn<StudentSchedule, String> FCTCol;
    @FXML private TableColumn<StudentSchedule, String> LCTCol;

    private final StudentScheduleDAO scheduleDAO = new StudentScheduleDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    @FXML
    public void initialize() {

        schedIdCol.setCellValueFactory(new PropertyValueFactory<>("ScheduleID"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        DoWCol.setCellValueFactory(new PropertyValueFactory<>("DayOfWeek"));
        FCTCol.setCellValueFactory(new PropertyValueFactory<>("FirstClassTime"));
        LCTCol.setCellValueFactory(new PropertyValueFactory<>("LastClassTime"));

        studentIdBox.setItems(
                FXCollections.observableArrayList(studentDAO.getAllStudents().stream().map(Student::getId).toList()));
        studentIdBox.setOnAction(e -> loadSchedules());
    }

    private void loadSchedules() {
        try {
            String studentID = studentIdBox.getValue();

            if (studentID != null) {
                ScheduleTable.setItems(FXCollections.observableArrayList(scheduleDAO.getSchedulesByStudent(studentID)));
            }
        } catch (Exception e) {
            showDatabaseError("Could not show schedules", e);
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
        this.switchScene("StudentScheduleTableOptions.fxml", event);
    }
}
