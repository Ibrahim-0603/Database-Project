package com.example.database_gui.dao;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.StudentSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentScheduleDAO {
    public List<StudentSchedule> getSchedulesByStudent(String studentID) {

        List<StudentSchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM StudentSchedule WHERE studentID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

             stmt.setString(1, studentID);

             try(ResultSet rs = stmt.executeQuery()) {
                 while (rs.next()) {
                     schedules.add(
                             new StudentSchedule(
                                     rs.getString("scheduleID"),
                                     rs.getString("studentID"),
                                     rs.getString("dayOfWeek"),
                                     rs.getTime("firstClassTime").toLocalTime(),
                                     rs.getTime("lastClassTime").toLocalTime()
                             )
                     );
                 }
             }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve student schedules from database", e);
        }

        return schedules;
    }
}
