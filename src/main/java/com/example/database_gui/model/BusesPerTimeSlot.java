package com.example.database_gui.model;

import java.time.LocalTime;

public class BusesPerTimeSlot {
    private String routeName;
    private String dayOfWeek;
    private LocalTime timeSlot;
    private int numStudents;
    private int bigBuses60;
    private int medBuses40;
    private int baseBus24;
    private int extraSpare24;

    public BusesPerTimeSlot(String routeName, String dayOfWeek, LocalTime timeSlot,
                            int numStudents, int bigBuses60, int medBuses40,
                            int baseBus24, int extraSpare24) {
        this.routeName = routeName;
        this.dayOfWeek = dayOfWeek;
        this.timeSlot = timeSlot;
        this.numStudents = numStudents;
        this.bigBuses60 = bigBuses60;
        this.medBuses40 = medBuses40;
        this.baseBus24 = baseBus24;
        this.extraSpare24 = extraSpare24;
    }

    // Getters only (JavaFX TableView reads via getters)
    public String getRouteName() { return routeName; }
    public String getDayOfWeek() { return dayOfWeek; }
    public LocalTime getTimeSlot() { return timeSlot; }
    public int getNumStudents() { return numStudents; }
    public int getBigBuses60() { return bigBuses60; }
    public int getMedBuses40() { return medBuses40; }
    public int getBaseBus24() { return baseBus24; }
    public int getExtraSpare24() { return extraSpare24; }
}

