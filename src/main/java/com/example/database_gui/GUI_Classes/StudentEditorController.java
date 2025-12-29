package com.example.database_gui.GUI_Classes;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.dao.StudentDAO;
import com.example.database_gui.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;

public class StudentEditorController extends BaseController{
    @FXML
    private TextField idInput, nameInput, emailInput, deptInput, routeInput;
    private final StudentDAO studentDAO = new StudentDAO();

    @FXML
    private void handleAdd(ActionEvent event){
        try{
            Student s = new Student(idInput.getText(), nameInput.getText(), emailInput.getText(), deptInput.getText(), routeInput.getText());
            studentDAO.insertStudent(s);
        }
        catch (Exception e){
            showDatabaseError("Insertion failed" ,e);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event){
        try{
            Student condition = new Student(idInput.getText(), nameInput.getText(), emailInput.getText(), deptInput.getText(), routeInput.getText());
            studentDAO.deleteStudent(condition);

        } catch (Exception e){
            throw new DatabaseException("Could not delete student", e);
        }
    }
}
