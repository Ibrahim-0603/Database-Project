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


}
