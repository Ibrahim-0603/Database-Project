package com.example.database_gui.model;

public class StudentPhone {

    private String studentID;
    private String phoneNumber;

    public StudentPhone(String studentID, String phoneNumber) {
        this.studentID = studentID;
        this.phoneNumber = phoneNumber;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPhoneNum() {
        return phoneNumber;
    }

    public void setPhoneNum(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "StudentPhone{" + "studentID='" + studentID + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' + '}';
    }
}