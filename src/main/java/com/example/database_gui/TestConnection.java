package com.example.database_gui;
import com.example.database_gui.database.DatabaseConnection;

import java.sql.Connection;

public class TestConnection {


    public static void main(String[] args) throws Exception {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        System.out.println("Connected successfully!");
        conn.close();
    }


}
