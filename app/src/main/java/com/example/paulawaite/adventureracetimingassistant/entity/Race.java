package com.example.paulawaite.adventureracetimingassistant.entity;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by paulawaite on 7/6/16.
 */
public class Race {
    private Calendar startDateTime;
    private Calendar endDateTime;
    private String name;
    private Map<Integer, TransitionArea> transitionAreas;
    private Map<Integer, Team> teams;
    private ArrayList<Integer> transitionAreaIds;
    private ArrayList<Integer> teamIds;
    private ArrayList<String> teamIdsNames;
    private ArrayList<Timing> timings;

    public Race() {
    }

    public Race(Calendar startDateTime, Calendar endDateTime, String name) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.name = name;
    }

    public ArrayList<Timing> getTimings() {
        return timings;
    }

    public void setTimings(ArrayList<Timing> timings) {
        this.timings = timings;
    }

    public Calendar getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Calendar startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Calendar getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Calendar endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, TransitionArea> getTransitionAreas() {
        return transitionAreas;
    }

    public void setTransitionAreas(Map<Integer, TransitionArea> transitionAreas) {
        this.transitionAreas = transitionAreas;
    }

    public Map<Integer, Team> getTeams() {
        return teams;
    }

    public void setTeams(Map<Integer, Team> teams) {
        this.teams = teams;
    }

    public List<Integer> getTransitionAreaIds() {
        transitionAreaIds = new ArrayList<>(transitionAreas.keySet());
        return transitionAreaIds;
    }

    public List<Integer> getTeamIds() {
        teamIds = new ArrayList<>(teams.keySet());
        return teamIds;
    }

    public List<String> getTeamIdsWithName() {
        teamIdsNames = new ArrayList<String>();
        for (Map.Entry<Integer,Team> entry: teams.entrySet()) {
            String idName = entry.getValue().getNumber() + " - " +entry.getValue().getName();
            teamIdsNames.add(idName);
        }
        return teamIdsNames;
    }

    public List<Timing> getTimingsByTA(int taNumber) {
        List<Timing> timingsByTA = new ArrayList<Timing>();
        if (timings != null && !(timings.isEmpty())) {
            for (Timing timing : timings) {
                if (timing.getTransitionArea().getNumber() == taNumber) {
                    timingsByTA.add(timing);
                }
            }
        }
        return timingsByTA;

    }

    // returns all timings for a team
    public List<Timing> getTimingsByTeam(int teamNumber) {
        return getTimingsByTeam(teamNumber, timings);
    }

    public Timing getTimingByTeamAndTA(int teamNumber, int TANumber) {
        List<Timing> timings = getTimingsByTeam(teamNumber, getTimingsByTA(TANumber));
        if (timings != null && !(timings.isEmpty())) {
            return getTimingsByTeam(teamNumber, getTimingsByTA(TANumber)).get(0);
        } else {
            return null;
        }
    }

    public List<Timing> getTimingsByTeam(int teamNumber, List<Timing> timings) {
        List<Timing> timingsByTeam = new ArrayList<Timing>();
        if (timings != null && !(timings.isEmpty())) {
            for (Timing timing : timings) {
                if (timing.getTeam().getNumber() == teamNumber) {
                    timingsByTeam.add(timing);
                }
            }
        }
        return timingsByTeam;
    }

    public Map<Team, Integer> calculateStandingsByCPs() {
        Map<Team, Integer> standings = new HashMap<Team, Integer>();
        for (Timing timing : timings) {
            if (standings.containsKey(timing.getTeam())) {
                Team team = timing.getTeam();
                int cps = timing.getCheckpoints();
                int oldCps = standings.get(team);
                standings.put(team, oldCps + cps);
            } else {
                standings.put(timing.getTeam(), timing.getCheckpoints());
            }
        }
        return standings;

    }

}
