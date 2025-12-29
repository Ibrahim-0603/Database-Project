package com.example.database_gui.model;

public class Route {
    private String routeID;
    private String routeName;
    private String startLocation;
    private String endLocation;

    public Route(String routeID, String routeName, String startLocation, String endLocation) {
        this.routeID = routeID;
        this.routeName = routeName;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeID='" + routeID + '\'' +
                ", routeName='" + routeName + '\'' +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                '}';
    }
}
