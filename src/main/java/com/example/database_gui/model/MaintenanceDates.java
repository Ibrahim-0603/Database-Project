package com.example.database_gui.model;


import java.time.LocalDate;

public class MaintenanceDates
{
    private String busID;
    private LocalDate maintenanceDate;
    public MaintenanceDates(String busID, LocalDate maintenanceDate){
        this.busID=busID;
        this.maintenanceDate=maintenanceDate;

    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }
}