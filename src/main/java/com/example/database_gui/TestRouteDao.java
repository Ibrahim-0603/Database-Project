package com.example.database_gui;

import com.example.database_gui.dao.RouteDAO;

public class TestRouteDao {
    public static void main(String[] args) {
        RouteDAO dao = new RouteDAO();
        dao.getAllRoutes().forEach(s ->
                System.out.println(s.getRouteName())
        );
    }
}
