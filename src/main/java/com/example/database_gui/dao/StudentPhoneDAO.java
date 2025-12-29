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

    public void insertPhone(StudentPhone sp) {
        String sql = "INSERT INTO StudentPhone (studentID, phoneNumber) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sp.getStudentID());
            stmt.setString(2, sp.getPhoneNum());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Could not insert phone number", e);
        }
    }

    public void deletePhone(StudentPhone sp) {
        String sql = "DELETE FROM StudentPhone WHERE studentID = ? AND phoneNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sp.getStudentID());
            stmt.setString(2, sp.getPhoneNum());
            int deletedCount = stmt.executeUpdate();
            if (deletedCount == 0) throw new DatabaseException("No matching record found");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete phone number", e);
        }
    }
}
