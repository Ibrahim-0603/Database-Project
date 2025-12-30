package com.example.database_gui.dao;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.DriverBusAssignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DriverBusAssignmentDAO {
    public List<DriverBusAssignment> getBusesByDrivers(String driverID) {
        List<DriverBusAssignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM DriverBusAssignment WHERE driverID = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, driverID);

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    assignments.add(
                            new DriverBusAssignment(
                                    rs.getString("DriverID"),
                                    rs.getString("BusID"),
                                    rs.getString("Shift")
                            )
                    );
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve buses from database", e);
        }

        return assignments;
    }
    public List<DriverBusAssignment> getDriversByBuses(String busID) {
        List<DriverBusAssignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM DriverBusAssignment WHERE busID = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, busID);

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    assignments.add(
                            new DriverBusAssignment(
                                    rs.getString("driverID"),
                                    rs.getString("busID"),
                                    rs.getString("shift")
                            )
                    );
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve drivers from database", e);
        }

        return assignments;
    }

    public void insertAssignment(DriverBusAssignment assignment) {
        String sql = "INSERT INTO DriverBusAssignment (driverID, busID, shift) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, assignment.getDriverID());
            stmt.setString(2, assignment.getBusID());
            stmt.setString(3, assignment.getShift());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Could not insert assignment", e);
        }
    }

    public void deleteAssignment(DriverBusAssignment assignment) {
        String sql = "DELETE FROM DriverBusAssignment WHERE driverID = ? AND busID = ? AND shift = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, assignment.getDriverID());
            stmt.setString(2, assignment.getBusID());
            stmt.setString(3, assignment.getShift());
            int deletedCount = stmt.executeUpdate();
            if (deletedCount == 0) throw new DatabaseException("No matching assignment found");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete assignment", e);
        }
    }
}
