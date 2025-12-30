package com.example.database_gui.dao;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.Driver;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    public List<Driver> getAllDrivers() {

        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM Driver";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                drivers.add(
                        new Driver(
                                rs.getString("DriverID"),
                                rs.getString("Name"),
                                rs.getString("LicenseNumber")
                        )
                );
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve drivers from database", e);
        }

        return drivers;
    }
    public void insertDriver(Driver driver) {
        String sql = "INSERT INTO Driver (DriverID, Name, LicenseNumber) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, driver.getId());
            stmt.setString(2, driver.getName());
            stmt.setString(3, driver.getLicenseNum());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Could not insert driver", e);
        }
    }

    public void deleteDriver(Driver driver) {
        String sql = "DELETE FROM Driver WHERE DriverID = ? AND Name = ? AND LicenseNumber = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, driver.getId());
            stmt.setString(2, driver.getName());
            stmt.setString(3, driver.getLicenseNum());
            int deletedCount = stmt.executeUpdate();
            if (deletedCount == 0) throw new DatabaseException("No matching driver found");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete driver", e);
        }
    }
}
