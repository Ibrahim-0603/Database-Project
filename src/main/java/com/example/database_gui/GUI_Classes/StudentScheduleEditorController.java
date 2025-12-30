package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.ValidationException;
import com.example.database_gui.dao.StudentScheduleDAO;
import com.example.database_gui.model.StudentSchedule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalTime;

public class StudentScheduleEditorController extends BaseController {
    @FXML
    private TextField scheduleIdInput, studentIdInput, dayInput, firstClassInput, lastClassInput;
    private final StudentScheduleDAO dao = new StudentScheduleDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            validateInput(scheduleIdInput, studentIdInput, dayInput, firstClassInput, lastClassInput);

            StudentSchedule s = new StudentSchedule(scheduleIdInput.getText(), studentIdInput.getText(),
                    dayInput.getText(), LocalTime.parse(firstClassInput.getText()),
                    LocalTime.parse(lastClassInput.getText()));
            dao.insertSchedule(s);
            switchScene("studentScheduleTableTable.fxml", event);

        } catch (ValidationException e) {
            showDatabaseError("Insertion failed", e);
        }catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            validateInput(scheduleIdInput, studentIdInput, dayInput, firstClassInput, lastClassInput);

            StudentSchedule s = new StudentSchedule(scheduleIdInput.getText(), studentIdInput.getText(),
                    dayInput.getText(), LocalTime.parse(firstClassInput.getText()),
                    LocalTime.parse(lastClassInput.getText()));
            dao.deleteSchedule(s);
            switchScene("studentScheduleTableTable.fxml", event);
        } catch (ValidationException e) {
            showDatabaseError("Delete failed", e);
        }catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }
    }
}