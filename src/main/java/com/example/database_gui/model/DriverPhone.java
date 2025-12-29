package com.example.database_gui.model;

public class DriverPhone {
    private String driverID;
    private String phoneNumber;
    public DriverPhone(String driverID, String phoneNumber){
        this.driverID=driverID;
        this.phoneNumber=phoneNumber;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}