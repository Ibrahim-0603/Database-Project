package com.example.database_gui.model;


public class Stops {
    private String stopID;
    private String stopName;
    private String location;
    private String RouteID;
    public Stops( String stopID , String stopName, String location ,String RouteID)
    {this.stopName=stopName;
        this.stopID=stopID;
        this.RouteID=RouteID;
        this.location=location;

    }

    public String getStopID() {
        return stopID;
    }

    public void setStopID(String stopID) {
        this.stopID = stopID;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRouteID() {
        return RouteID;
    }

    public void setRouteID(String routeID) {
        RouteID = routeID;
    }
}