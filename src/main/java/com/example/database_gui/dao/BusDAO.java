package com.example.database_gui.dao;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.Bus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {
    public List<Bus> getAllBuses() {

        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT * FROM Bus";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                buses.add(
                        new Bus(
                                rs.getString("BusID"),
                                rs.getString("PlateNumber"),
                                rs.getString("Model"),
                                rs.getInt("Capacity"),
                                rs.getString("RouteID")
                        )
                );
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve buses from database", e);
        }

        return buses;
    }

    public void insertBus(Bus bus) {
        String sql = "INSERT INTO Bus (BusID, PlateNumber, Model, Capacity, RouteID) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bus.getBusID());
            stmt.setString(2, bus.getPlateNum());
            stmt.setString(3, bus.getModel());
            stmt.setInt(4, bus.getCapacity());
            stmt.setString(5, bus.getRouteID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Could not insert bus", e);
        }
    }

    public void deleteBus(Bus bus) {
        String sql = "DELETE FROM Bus WHERE BusID = ? AND PlateNumber = ? AND Model = ? AND Capacity = ? AND RouteID = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bus.getBusID());
            stmt.setString(2, bus.getPlateNum());
            stmt.setString(3, bus.getModel());
            stmt.setInt(4, bus.getCapacity());
            stmt.setString(5, bus.getRouteID());
            int deletedCount = stmt.executeUpdate();
            if (deletedCount == 0) throw new DatabaseException("No matching bus found");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete bus", e);
        }
    }

}
