package com.example.database_gui.model;

import java.time.Duration;
import java.time.LocalTime;

public class StudentSchedule implements Scheduler{
    private String studentID;
    private String scheduleID;
    private String dayOfWeek;
    private LocalTime firstClassTime;
    private LocalTime lastClassTime;
    public StudentSchedule(String scheduleID, String studentID , String dayOfWeek, LocalTime firstClassTime, LocalTime lastClassTime){
        this.scheduleID=scheduleID;
        this.studentID=studentID;
        this.dayOfWeek = dayOfWeek;
        this.firstClassTime = firstClassTime;
        this.lastClassTime = lastClassTime;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getFirstClassTime() {
        return firstClassTime;
    }

    public void setFirstClassTime(LocalTime firstClassTime) {
        this.firstClassTime = firstClassTime;
    }

    public LocalTime getLastClassTime() {
        return lastClassTime;
    }

    public void setLastClassTime(LocalTime lastClassTime) {
        this.lastClassTime = lastClassTime;
    }

    @Override
    public LocalTime getStartTime() {
        return getFirstClassTime();
    }

    @Override
    public LocalTime getEndTime(){
        return getLastClassTime();
    }

    @Override
    public long getDuration() {
        return Scheduler.super.getDuration();
    }
}