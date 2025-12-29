package com.example.database_gui.dao;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.BusesPerTimeSlot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusPerTimeSlotDAO {
    public List<BusesPerTimeSlot> getBusesNeeded() {
        List<BusesPerTimeSlot> list = new ArrayList<>();
        String sql = "SELECT r.RouteName, ss.DayOfWeek, ss.FirstClassTime AS TimeSlot, " +
                "COUNT(*) AS NumStudents, " +
                "FLOOR(COUNT(*) / 60) AS BigBuses_60, " +
                "CASE WHEN (COUNT(*) % 60) > 24 THEN 1 ELSE 0 END AS MedBuses_40, " +
                "CASE WHEN (COUNT(*) % 60) BETWEEN 1 AND 24 THEN 1 ELSE 0 END AS BaseBus_24, " +
                "IF(COUNT(*) > 0, 1, 0) AS ExtraSpare_24 " +
                "FROM Student s, Route r, studentschedule ss " +
                "WHERE s.RouteID = r.RouteID AND s.StudentID = ss.StudentID " +
                "GROUP BY r.RouteName, ss.DayOfWeek, ss.FirstClassTime " +
                "ORDER BY ss.DayOfWeek, TimeSlot";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new BusesPerTimeSlot(
                        rs.getString("RouteName"),
                        rs.getString("DayOfWeek"),
                        rs.getTime("TimeSlot").toLocalTime(),
                        rs.getInt("NumStudents"),
                        rs.getInt("BigBuses_60"),
                        rs.getInt("MedBuses_40"),
                        rs.getInt("BaseBus_24"),
                        rs.getInt("ExtraSpare_24")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
