package com.example.database_gui.model;

public class Student extends Person
{

    private String email;
    private String department;
    private String routeID;

    public Student(String studentID, String studentName, String email, String department, String routeID)
    {
        super(studentID, studentName);
        this.email = email;
        this.department = department;
        this.routeID = routeID;
    }

    public Student(String id, String name)
    {
        super(id, name);
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getRouteID()
    {
        return routeID;
    }

    public void setRouteID(String routeID)
    {
        this.routeID = routeID;
    }

    @Override
    public String toString()
    {
        return "Student{" +
                "id='" + getId() + '\'' + ", name='" + getName() + '\'' +
                ", email='" + email + '\'' + ", department='" + department + '\'' +
                ", routeID='" + routeID + '\'' + '}';
    }
}