package com.example.database_gui.model;

import java.time.LocalTime;

public class DriverBusAssignment {
    private String driverID;
    private String busID;
    private String shift;

    public DriverBusAssignment(String driverID, String busID, String shift){
        this.busID=busID;
        this.driverID=driverID;
        this.shift=shift;

    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}