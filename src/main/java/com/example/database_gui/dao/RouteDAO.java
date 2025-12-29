package com.example.database_gui.dao;


import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {

    public List<Route> getAllRoutes() {

        List<Route> routes = new ArrayList<>();
        String sql = "SELECT * FROM Route";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                routes.add(
                        new Route(
                            rs.getString("RouteID"),
                            rs.getString("routeName"),
                            rs.getString("startLocation"),
                            rs.getString("endLocation")
                                )
                );
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve routes from database", e);
        }

        return routes;
    }

    public void insertRoute(Route route) {
        String sql = "INSERT INTO Route (RouteID, routeName, startLocation, endLocation) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, route.getRouteID());
            stmt.setString(2, route.getRouteName());
            stmt.setString(3, route.getStartLocation());
            stmt.setString(4, route.getEndLocation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Could not insert route", e);
        }
    }

    public void deleteRoute(Route route) {
        String sql = "DELETE FROM Route WHERE RouteID = ? AND routeName = ? AND startLocation = ? AND endLocation = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, route.getRouteID());
            stmt.setString(2, route.getRouteName());
            stmt.setString(3, route.getStartLocation());
            stmt.setString(4, route.getEndLocation());
            int deletedCount = stmt.executeUpdate();
            if (deletedCount == 0) throw new DatabaseException("No matching route found");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete route", e);
        }
    }


}
