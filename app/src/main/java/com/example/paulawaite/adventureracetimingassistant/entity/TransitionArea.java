package com.example.paulawaite.adventureracetimingassistant.entity;

import com.example.paulawaite.adventureracetimingassistant.entity.Discipline;

/**
 * Created by paulawaite on 7/6/16.
 */
public class TransitionArea {
    private Discipline disciplineIn;
    private Discipline disciplineOut;
    private String description;
    private String notes;
    private int number;
    private int totalPossibleCps;

    public TransitionArea() {
    }

    public TransitionArea(Discipline disciplineIn, Discipline disciplineOut, String description, String notes, int number, int totalPossibleCps) {
        this.disciplineIn = disciplineIn;
        this.disciplineOut = disciplineOut;
        this.description = description;
        this.notes = notes;
        this.number = number;
        this.totalPossibleCps = totalPossibleCps;
    }

    public int getTotalPossibleCps() {
        return totalPossibleCps;
    }

    public void setTotalPossibleCps(int totalPossibleCps) {
        this.totalPossibleCps = totalPossibleCps;
    }

    public Discipline getDisciplineIn() {
        return disciplineIn;
    }

    public void setDisciplineIn(Discipline disciplineIn) {
        this.disciplineIn = disciplineIn;
    }

    public Discipline getDisciplineOut() {
        return disciplineOut;
    }

    public void setDisciplineOut(Discipline disciplineOut) {
        this.disciplineOut = disciplineOut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "TransitionArea{" +
                "disciplineIn=" + disciplineIn +
                ", disciplineOut=" + disciplineOut +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                ", number=" + number +
                ", totalPossibleCps=" + totalPossibleCps +
                '}';
    }
}
