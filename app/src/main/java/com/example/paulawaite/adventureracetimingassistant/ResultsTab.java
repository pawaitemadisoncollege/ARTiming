package com.example.paulawaite.adventureracetimingassistant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.paulawaite.adventureracetimingassistant.entity.GlobalVariables;
import com.example.paulawaite.adventureracetimingassistant.entity.MapComparator;
import com.example.paulawaite.adventureracetimingassistant.entity.Race;
import com.example.paulawaite.adventureracetimingassistant.entity.Team;
import com.example.paulawaite.adventureracetimingassistant.entity.Timing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by paulawaite on 7/6/16.
 */
public class ResultsTab extends Fragment {

    ListView listView;
    GlobalVariables globals;
    Race race;
    TextView message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        globals = GlobalVariables.getInstance();
        race = globals.getEventData().getRace();

        View resultsInflater = inflater.inflate(R.layout.results, null, false);

        listView = (ListView) resultsInflater.findViewById(R.id.listView);
        message = (TextView) resultsInflater.findViewById(R.id.resultsEmptyMessage);

        Map<Team, Integer> standings = race.calculateStandingsByCPs();

        standings = MapComparator.sortByValue(standings);

        if (standings != null && standings.size() > 0) {
            List<String> standingsList = new ArrayList<>();
            for (Map.Entry entry : standings.entrySet()) {
                Team team = (Team) entry.getKey();
                standingsList.add(team.getName() + "  --  " + entry.getValue() + "CPs");
            }

            message.setText("");
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, standingsList);
            listView.setAdapter(adapter);
        }

        return resultsInflater;
    }
}

