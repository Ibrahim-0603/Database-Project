package com.example.database_gui.dao;

import com.example.database_gui.Exceptions.DatabaseException;
import com.example.database_gui.database.DatabaseConnection;
import com.example.database_gui.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(
                        new Student(
                                rs.getString("StudentId"),
                                rs.getString("Name"),
                                rs.getString("Email"),
                                rs.getString("Department"),
                                rs.getString("RouteID")
                        )
                );
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve students from database", e);
        }

        return students;
    }

    public void insertStudent(Student student){
        String sql = "INSERT INTO student (StudentId, Name, Email, Department, RouteID) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getDepartment());
            stmt.setString(5, student.getRouteID());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            throw new DatabaseException("Could not insert student",e);
        }
    }

    public void deleteStudent(Student student){
        String sql = "DELETE FROM student WHERE StudentID = ? AND Name = ? AND Email = ? AND Department = ? AND RouteID = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getDepartment());
            stmt.setString(5, student.getRouteID());

            int deletedCount = stmt.executeUpdate();
            if(deletedCount==0){
                throw new DatabaseException("No student found");
            }
        } catch(SQLException e){
            throw new DatabaseException("Failed to delete", e);
        }
    }
}
