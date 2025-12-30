package com.example.database_gui.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/bus%20management%20db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DatabaseConnection() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(ClassNotFoundException e){
            throw new SQLException("JDBC Driver not found", e);
        }
    }
    public static DatabaseConnection getInstance() throws SQLException{
        if (instance == null || instance.getConnection().isClosed()|| instance.getConnection()==null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
