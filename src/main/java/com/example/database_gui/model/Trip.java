package com.example.database_gui.model;


import java.time.LocalDate;
import java.time.LocalTime;

public class Trip implements Scheduler{
    private String routeId;
    private int tNumber;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private LocalDate date;
    private String driverID;
    private String busID;
    public Trip(String routeId, int tNumber, LocalTime arrivalTime, LocalTime departureTime, LocalDate date, String driverID , String busID){
        this.routeId=routeId;
        this.tNumber=tNumber;
        this.arrivalTime=arrivalTime;
        this.departureTime = departureTime;
        this.date=date;
        this.driverID=driverID;
        this.busID=busID;

    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public int getNumber() {
        return tNumber;
    }

    public void setNumber(int tNumber) {
        this.tNumber = tNumber;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    @Override
    public LocalTime getStartTime()
    {
        return getDepartureTime();
    }

    @Override
    public LocalTime getEndTime() {

        return getArrivalTime();
    }

    @Override
    public long getDuration() {
        return Scheduler.super.getDuration();
    }
}