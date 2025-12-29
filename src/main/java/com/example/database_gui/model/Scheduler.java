package com.example.database_gui.model;

import java.time.Duration;
import java.time.LocalTime;

public interface Scheduler {

    LocalTime getStartTime();

    LocalTime getEndTime();

    default long getDuration(){
        return Duration.between(getStartTime(), getEndTime()).toMinutes();
    }
}
