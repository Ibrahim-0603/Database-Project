package com.example.database_gui.dao;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.StudentPhone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentPhoneDAO {
    public List<StudentPhone> getAllStudentPhoneNumbers() {

        List<StudentPhone> phoneNumbers = new ArrayList<>();
        String sql = "SELECT * FROM StudentPhone, Student WHERE StudentPhone.studentID = student.studentID";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                phoneNumbers.add(
                        new StudentPhone(
                                rs.getString("studentID"),
                                rs.getString("phoneNumber")
                        )
                );
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve student phonenumbers from database", e);
        }

        return phoneNumbers;
    }
}
