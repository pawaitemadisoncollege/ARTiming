package com.example.paulawaite.adventureracetimingassistant.entity;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by paulawaite on 7/6/16.
 */
public class DataSetUp {
    private Race race;

    public DataSetUp() {
        createRace();
    }

    private void createRace(){

        Calendar start = createStartDateTime();
        Calendar finish = createFinishDateTime();

        race = new Race(start, finish, "Stubborn Mule 30-Hour");
        race.setTeams(createTeams());
        race.setTransitionAreas(createTransitionAreas());
        race.setTimings(new ArrayList<Timing>());
    }

    private Map<Integer, TransitionArea> createTransitionAreas() {

        Map<Integer, TransitionArea> tas = new TreeMap<Integer, TransitionArea>();

        tas.put(0, new TransitionArea(Discipline.START, Discipline.BIKE, "Race HQ", "", 0, 0));
        tas.put(1, new TransitionArea(Discipline.BIKE, Discipline.LAND_NAVIGATION, "Lost Land Lake Rd", "", 1, 3));
        tas.put(2, new TransitionArea(Discipline.LAND_NAVIGATION, Discipline.BIKE, "Lost Land Lake Rd", "", 2, 11));
        tas.put(3, new TransitionArea(Discipline.BIKE, Discipline.PADDLE, "Two Lakes Campground", "", 3, 8));
        tas.put(4, new TransitionArea(Discipline.PADDLE, Discipline.LAND_NAVIGATION, "Two Lakes Campground", "", 4, 12));
        tas.put(5, new TransitionArea(Discipline.LAND_NAVIGATION, Discipline.ROAD_BIKE, "Two Lakes Campground", "", 5, 23));
        tas.put(6, new TransitionArea(Discipline.LAND_NAVIGATION, Discipline.ROAD_BIKE, "Race HQ", "", 6, 5));

        return tas;

    }

    private Map<Integer, Team> createTeams() {
        Map<Integer, Team> teams = new TreeMap<Integer, Team>();


        teams.put(1, new Team("Strong Machine", Division.PREMIER_COED, 1));
        teams.put(2, new Team("WEDALI", Division.PREMIER_COED, 2));
        teams.put(3, new Team("Rib Mountain Racing", Division.PREMIER_COED, 3));
        teams.put(4, new Team("Elkbones", Division.PREMIER_COED, 4));

        return teams;
    }


    private Calendar createStartDateTime() {
        Calendar start = Calendar.getInstance();
        start.set(Calendar.YEAR, 2016);
        start.set(2016, 6, 25, 6, 0, 0);
        return start;
    }

    private Calendar createFinishDateTime() {
        Calendar start = Calendar.getInstance();
        start.set(Calendar.YEAR, 2016);
        start.set(2016, 6, 26, 12, 0, 0);
        return start;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
