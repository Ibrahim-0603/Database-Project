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

        try (Connection conn = DatabaseConnection.getConnection();
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
}
