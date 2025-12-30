package com.example.database_gui.dao;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.StudentSchedule;
import com.example.database_gui.model.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripDAO {
    public List<Trip> getTripsByRoute(String routeID) {

        List<Trip> trips= new ArrayList<>();
        String sql = "SELECT * FROM Trip WHERE routeID = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, routeID);

            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    trips.add(
                            new Trip(
                                    rs.getString("RouteID"),
                                    rs.getInt("TripNum"),
                                    rs.getTime("ArrivalTime").toLocalTime(),
                                    rs.getTime("DepartureTime").toLocalTime(),
                                    rs.getDate("Date").toLocalDate(),
                                    rs.getString("DriverID"),
                                    rs.getString("BusID")
                            )
                    );
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve trips from database", e);
        }

        return trips;
    }
    public void insertTrip(Trip trip) {
        String sql = "INSERT INTO Trip (RouteID, TripNum, ArrivalTime, DepartureTime, Date, DriverID, BusID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trip.getRouteId());
            stmt.setInt(2, trip.getNumber());
            stmt.setTime(3, java.sql.Time.valueOf(trip.getArrivalTime()));
            stmt.setTime(4, java.sql.Time.valueOf(trip.getDepartureTime()));
            stmt.setDate(5, java.sql.Date.valueOf(trip.getDate()));
            stmt.setString(6, trip.getDriverID());
            stmt.setString(7, trip.getBusID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Could not insert trip", e);
        }
    }

    public void deleteTrip(Trip trip) {
        String sql = "DELETE FROM Trip WHERE RouteID = ? AND TripNum = ? AND ArrivalTime = ? AND DepartureTime = ? AND Date = ? AND DriverID = ? AND BusID = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trip.getRouteId());
            stmt.setInt(2, trip.getNumber());
            stmt.setTime(3, java.sql.Time.valueOf(trip.getArrivalTime()));
            stmt.setTime(4, java.sql.Time.valueOf(trip.getDepartureTime()));
            stmt.setDate(5, java.sql.Date.valueOf(trip.getDate()));
            stmt.setString(6, trip.getDriverID());
            stmt.setString(7, trip.getBusID());
            int deletedCount = stmt.executeUpdate();
            if (deletedCount == 0) throw new DatabaseException("No matching trip found");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete trip", e);
        }
    }
}
