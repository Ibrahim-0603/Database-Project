package com.example.database_gui.dao;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(
                        new Student(
                                rs.getString("StudentId"),
                                rs.getString("Name"),
                                rs.getString("Email"),
                                rs.getString("Department"),
                                rs.getString("RouteID")
                        )
                );
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve students from database", e);
        }

        return students;
    }
}
