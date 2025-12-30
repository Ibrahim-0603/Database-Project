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

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
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

    public void insertSchedule(StudentSchedule schedule) {
        String sql = "INSERT INTO StudentSchedule (scheduleID, studentID, dayOfWeek, firstClassTime, lastClassTime) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, schedule.getScheduleID());
            stmt.setString(2, schedule.getStudentID());
            stmt.setString(3, schedule.getDayOfWeek());
            stmt.setTime(4, java.sql.Time.valueOf(schedule.getFirstClassTime()));
            stmt.setTime(5, java.sql.Time.valueOf(schedule.getLastClassTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Could not insert schedule", e);
        }
    }

    public void deleteSchedule(StudentSchedule schedule) {
        String sql = "DELETE FROM StudentSchedule WHERE scheduleID = ? AND studentID = ? AND dayOfWeek = ? AND firstClassTime = ? AND lastClassTime = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, schedule.getScheduleID());
            stmt.setString(2, schedule.getStudentID());
            stmt.setString(3, schedule.getDayOfWeek());
            stmt.setTime(4, java.sql.Time.valueOf(schedule.getFirstClassTime()));
            stmt.setTime(5, java.sql.Time.valueOf(schedule.getLastClassTime()));
            int deletedCount = stmt.executeUpdate();
            if (deletedCount == 0) throw new DatabaseException("No matching schedule found");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete schedule", e);
        }
    }
}
