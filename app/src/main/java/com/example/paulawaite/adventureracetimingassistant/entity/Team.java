package com.example.paulawaite.adventureracetimingassistant.entity;

import com.example.paulawaite.adventureracetimingassistant.entity.Division;

/**
 * Created by paulawaite on 7/6/16.
 */
public class Team {
    private String name;
    private Division division;
    private int number;

    public Team() {
    }

    public Team(String name, Division division, int number) {
        this.name = name;
        this.division = division;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", division=" + division +
                ", number=" + number +
                '}';
    }
}
