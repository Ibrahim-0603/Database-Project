package com.example.database_gui.dao;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.MaintenanceDates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDatesDAO {
    public List<MaintenanceDates> getAllMaintenanceDates() {

        List<MaintenanceDates> dates = new ArrayList<>();
        String sql = "SELECT * FROM busMaintenance, Bus WHERE busMaintenance.BusId = Bus.BusID";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                dates.add(
                        new MaintenanceDates(
                                rs.getString("BusID"),
                                rs.getDate("MaintenanceDate").toLocalDate()
                        )
                );
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve maintenance dates from database", e);
        }

        return dates;
    }

    public void insertMaintenanceDate(MaintenanceDates md) {
        String sql = "INSERT INTO busMaintenance (BusID, MaintenanceDate) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, md.getBusID());
            stmt.setDate(2, java.sql.Date.valueOf(md.getMaintenanceDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Could not insert maintenance date", e);
        }
    }

    public void deleteMaintenanceDate(MaintenanceDates md) {
        String sql = "DELETE FROM busMaintenance WHERE BusID = ? AND MaintenanceDate = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, md.getBusID());
            stmt.setDate(2, java.sql.Date.valueOf(md.getMaintenanceDate()));
            int deletedCount = stmt.executeUpdate();
            if (deletedCount == 0) throw new DatabaseException("No matching record found");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete maintenance record", e);
        }
    }
}
