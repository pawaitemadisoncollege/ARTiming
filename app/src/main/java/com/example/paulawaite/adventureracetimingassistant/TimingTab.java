package com.example.paulawaite.adventureracetimingassistant;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulawaite.adventureracetimingassistant.entity.DataSetUp;
import com.example.paulawaite.adventureracetimingassistant.entity.GlobalVariables;
import com.example.paulawaite.adventureracetimingassistant.entity.Race;
import com.example.paulawaite.adventureracetimingassistant.entity.Timing;
import com.example.paulawaite.adventureracetimingassistant.entity.TransitionArea;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by paulawaite on 7/6/16.
 */
public class TimingTab extends Fragment implements View.OnClickListener {

    private GlobalVariables globals;
    private Spinner taSpinner;
    private Spinner teamSpinner;
    private Spinner cpSpinner;
    private ImageView checkInButton;
    private ImageView checkOutButton;
    private Button saveButton;
    private TextView checkInTime;
    private TextView checkOutTime;
    private EditText notes;
    private Race race;
    private Timing timing;
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        globals = GlobalVariables.getInstance();
        race = globals.getEventData().getRace();
        timing = new Timing();

        View timingInflater = inflater.inflate(R.layout.timing, null, false);

        checkInTime = (TextView) timingInflater.findViewById(R.id.CheckInTime);
        checkOutTime = (TextView) timingInflater.findViewById(R.id.CheckOutTime);

        checkInButton = (ImageView) timingInflater.findViewById(R.id.checkInImage);
        checkInButton.setOnClickListener(this);

        /* I wanted to do something with the inmage to indicate it had been pressed, so I found this code to use for now:
           reference: http://stackoverflow.com/questions/4617898/how-can-i-give-imageview-click-effect-like-a-button-in-android
         */
        checkInButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        checkInButton.callOnClick();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }

                return true;
            }
        });

        checkOutButton = (ImageView) timingInflater.findViewById(R.id.checkOutImage);
        checkOutButton.setOnClickListener(this);

       /* I wanted to do something with the inmage to indicate it had been pressed, so I found this code to use for now:
           reference: http://stackoverflow.com/questions/4617898/how-can-i-give-imageview-click-effect-like-a-button-in-android
         */
        checkOutButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        checkOutButton.callOnClick();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }

                return true;
            }
        });

        saveButton = (Button) timingInflater.findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(this);

        notes = (EditText) timingInflater.findViewById(R.id.editNotes) ;

        taSpinner = (Spinner) timingInflater.findViewById(R.id.TASpinner);
        createTASpinner();

        cpSpinner = (Spinner) timingInflater.findViewById(R.id.CPSpinner);
        createCpSpinner();

        teamSpinner = (Spinner) timingInflater.findViewById(R.id.TeamSpinner);
        createTeamSpinner();

        return timingInflater;
    }

    public void createTASpinner() {

        List<Integer> taIds = race.getTransitionAreaIds();
        //remove TA 0 since we this is the start and we do not record anything for it.
        taIds.remove(0);

        ArrayAdapter<Integer> taAdapter = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_list_item_1, taIds);
        taSpinner.setAdapter(taAdapter);
        taSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timing.setTransitionArea(race.getTransitionAreas().get(position + 1));  // adding 1 because we removed the 0TA and TA starts at 1
                createCpSpinner();
                checkForExistingData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void createTeamSpinner() {

        ArrayAdapter<String> teamAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, race.getTeamIdsWithName());
        teamSpinner.setAdapter(teamAdapter);
        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timing.setTeam(race.getTeams().get(position + 1));
                checkForExistingData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void createCpSpinner() {

        int possibleCps = 0;
        ArrayList<Integer> cpList = new ArrayList<Integer>();

        if (timing.getTransitionArea() != null) {
            possibleCps = timing.getTransitionArea().getTotalPossibleCps();
        }

        for (int i = 0; i <= possibleCps; i++) {
            cpList.add(i);
        }


        ArrayAdapter<Integer> cpAdapter = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_list_item_1, cpList);
        cpSpinner.setAdapter(cpAdapter);
        cpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timing.setCheckpoints(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onClick(View view) {


        Calendar calendar = Calendar.getInstance();
        switch (view.getId()) {
            case R.id.checkInImage:
                timing.setTimeIn(calendar.getTime());
                checkInTime.setText(dateTimeFormat.format(timing.getTimeIn()));
                break;
            case R.id.checkOutImage:
                timing.setTimeOut(calendar.getTime());
                checkOutTime.setText(dateTimeFormat.format(timing.getTimeOut()));
                break;
            case R.id.buttonSave:
                timing.setNotes(notes.getText().toString());
                race.getTimings().add(timing);
                Toast.makeText(getActivity(), "Timing saved for " + timing.getTeam().getName(), Toast.LENGTH_SHORT).show();
                timing = new Timing();
                timing.setTransitionArea(race.getTransitionAreas().get(1));
                checkInTime.setText("");
                checkOutTime.setText("");
                notes.setText("");
                cpSpinner.setSelection(0);
                break;
///TODO: when saving, check if a timing already exists for that TA/TEAM, if so, replace that item.
        }

    }

    public void checkForExistingData() {
        if (timing.getTeam() != null && timing.getTransitionArea() != null) {
            Timing existingTiming = race.getTimingByTeamAndTA(timing.getTeam().getNumber(), timing.getTransitionArea().getNumber());
            if (existingTiming != null) {
                cpSpinner.setSelection(existingTiming.getCheckpoints());
                if (existingTiming.getTimeIn() != null) {
                    checkInTime.setText(dateTimeFormat.format(existingTiming.getTimeIn()));
                } else {
                    checkInTime.setText("");
                }
                if (existingTiming.getTimeOut() != null) {
                    checkOutTime.setText(dateTimeFormat.format(existingTiming.getTimeOut()));
                } else {
                    checkOutTime.setText("");
                }
                notes.setText((CharSequence) existingTiming.getNotes());
            } else {
                cpSpinner.setSelection(0);
                checkInTime.setText("");
                checkOutTime.setText("");
                notes.setText("");
            }
        }


    }


}
