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

        try (Connection conn = DatabaseConnection.getConnection();
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
}
