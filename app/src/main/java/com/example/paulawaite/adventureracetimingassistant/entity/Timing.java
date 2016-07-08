package com.example.paulawaite.adventureracetimingassistant.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by paulawaite on 7/6/16.
 */
public class Timing {
    private Team team;
    private TransitionArea transitionArea;
    private int checkpoints;
    private Date timeIn;
    private Date timeOut;
    private String notes;

    public Timing() {
    }

    public Timing(Team team, TransitionArea transitionArea, int checkpoints, Date timeIn, Date timeOut, String notes) {
        this.team = team;
        this.transitionArea = transitionArea;
        this.checkpoints = checkpoints;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.notes = notes;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public TransitionArea getTransitionArea() {
        return transitionArea;
    }

    public void setTransitionArea(TransitionArea transitionArea) {
        this.transitionArea = transitionArea;
    }

    public int getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(int checkpoints) {
        this.checkpoints = checkpoints;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Timing{" +
                "team=" + team +
                ", transitionArea=" + transitionArea +
                ", checkpoints=" + checkpoints +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                ", notes='" + notes + '\'' +
                '}';
    }
}
