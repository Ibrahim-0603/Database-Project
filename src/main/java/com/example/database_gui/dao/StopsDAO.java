package com.example.database_gui.dao;


import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.Stops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StopsDAO {
    public List<Stops> getAllStops() {

        List<Stops> stops = new ArrayList<>();
        String sql = "SELECT * FROM Stop";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                stops.add(
                        new Stops(
                                rs.getString("StopID"),
                                rs.getString("StopName"),
                                rs.getString("Location"),
                                rs.getString("RouteID")
                        )
                );
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve stops from database", e);
        }

        return stops;
    }

    public void insertStop(Stops stop) {
        String sql = "INSERT INTO Stop (StopID, StopName, Location, RouteID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, stop.getStopID());
            stmt.setString(2, stop.getStopName());
            stmt.setString(3, stop.getLocation());
            stmt.setString(4, stop.getRouteID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Could not insert stop", e);
        }
    }

    public void deleteStop(Stops stop) {
        String sql = "DELETE FROM Stop WHERE StopID = ? AND StopName = ? AND Location = ? AND RouteID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, stop.getStopID());
            stmt.setString(2, stop.getStopName());
            stmt.setString(3, stop.getLocation());
            stmt.setString(4, stop.getRouteID());
            int deletedCount = stmt.executeUpdate();
            if (deletedCount == 0) throw new DatabaseException("No matching stop found");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete stop", e);
        }
    }
}
