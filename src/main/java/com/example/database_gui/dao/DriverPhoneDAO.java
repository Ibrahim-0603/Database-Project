package com.example.database_gui.dao;
import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.DriverPhone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverPhoneDAO {
    public List<DriverPhone> getAllDriverPhoneNumbers() {

        List<DriverPhone> phoneNumbers = new ArrayList<>();
        String sql = "SELECT * FROM DriverPhone, Driver WHERE DriverPhone.driverID = Driver.DriverID";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                phoneNumbers.add(
                        new DriverPhone(
                                rs.getString("driverID"),
                                rs.getString("phoneNumber")
                        )
                );
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve driver phonenumbers from database", e);
        }

        return phoneNumbers;
    }

    public void insertPhone(DriverPhone dp) {
        String sql = "INSERT INTO DriverPhone (driverID, phoneNumber) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dp.getDriverID());
            stmt.setString(2, dp.getPhoneNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Could not insert phone number", e);
        }
    }

    public void deletePhone(DriverPhone dp) {
        String sql = "DELETE FROM DriverPhone WHERE driverID = ? AND phoneNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dp.getDriverID());
            stmt.setString(2, dp.getPhoneNumber());
            int deletedCount = stmt.executeUpdate();
            if (deletedCount == 0) throw new DatabaseException("No matching record found");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete phone number", e);
        }
    }
}
