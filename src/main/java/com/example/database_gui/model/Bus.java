package com.example.database_gui.model;

public class Bus
{
    private String busID;
    private String plateNum;
    private String model;
    private int capacity;
    private String routeID;



    public Bus(String busID , String plateNum, String model, int capacity, String routeID){
        this.busID=busID;
        this.capacity=capacity;
        this.model=model;
        this.routeID=routeID;
        this.plateNum=plateNum;

    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }
}