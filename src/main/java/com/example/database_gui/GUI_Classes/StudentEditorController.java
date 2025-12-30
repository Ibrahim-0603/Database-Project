package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.ValidationException;
import com.example.database_gui.dao.StudentDAO;
import com.example.database_gui.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StudentEditorController extends BaseController{
    @FXML
    private TextField idInput, nameInput, emailInput, deptInput, routeInput;
    private final StudentDAO studentDAO = new StudentDAO();

    @FXML
    private void handleAdd(ActionEvent event){
        try {
            validateInput(idInput, nameInput, emailInput, deptInput, routeInput);
            Student s = new Student(idInput.getText(), nameInput.getText(), emailInput.getText(), deptInput.getText(), routeInput.getText());
            studentDAO.insertStudent(s);
            switchScene("studentTable.fxml", event);
        }
        catch (ValidationException e){
            showDatabaseError("Insertion failed" ,e);
        } catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event){
        try{
            validateInput(idInput, nameInput, emailInput, deptInput, routeInput);
            Student condition = new Student(idInput.getText(), nameInput.getText(), emailInput.getText(), deptInput.getText(), routeInput.getText());
            studentDAO.deleteStudent(condition);
            switchScene("studentTable.fxml", event);
        } catch (ValidationException e){
            showDatabaseError("Deletion failed", e);
        } catch (IOException e){
            showDatabaseError("Failed to load scene", e);
        } catch (Exception e){
            showDatabaseError(e);
        }

    }
}
