package com.example.database_gui.GUI_Classes;

import com.example.database_gui.dao.StudentPhoneDAO;
import com.example.database_gui.model.StudentPhone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StudentPhoneEditorController extends BaseController {
    @FXML
    private TextField studentIdInput, phoneInput;
    private final StudentPhoneDAO dao = new StudentPhoneDAO();

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            StudentPhone sp = new StudentPhone(studentIdInput.getText(), phoneInput.getText());
            dao.insertPhone(sp);
            // You might need to create a phoneTable.fxml if it doesn't exist
            openEditor(event, "studentTable.fxml");
        } catch (Exception e) {
            showDatabaseError("Insertion failed", e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            StudentPhone sp = new StudentPhone(studentIdInput.getText(), phoneInput.getText());
            dao.deletePhone(sp);
            openEditor(event, "studentTable.fxml");
        } catch (Exception e) {
            showDatabaseError("Delete failed", e);
        }
    }
}
