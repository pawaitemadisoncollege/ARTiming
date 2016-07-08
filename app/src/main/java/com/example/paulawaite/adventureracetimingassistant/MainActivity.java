package com.example.paulawaite.adventureracetimingassistant;

import android.content.res.Resources;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulawaite.adventureracetimingassistant.entity.DataSetUp;
import com.example.paulawaite.adventureracetimingassistant.entity.GlobalVariables;
import com.example.paulawaite.adventureracetimingassistant.entity.Timing;
import com.example.paulawaite.adventureracetimingassistant.entity.TransitionArea;

import java.util.Calendar;

public class MainActivity extends FragmentActivity  {

    private FragmentTabHost tabHost;

    private AdminTab adminTabFragment;
    private TimingTab timingTabFragment;
    private ResultsTab resultsTabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create data for application - this will use a db at some point
        GlobalVariables globals = GlobalVariables.getInstance();
        globals.setEventData(new DataSetUp());

        // provide access to the images
        Resources resources = getResources();



        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        // set indicator can be used to add an image
        tabHost.addTab(tabHost.newTabSpec("Timing").setIndicator(setCustomIndicator("Timing", R.drawable.clipboard)), TimingTab.class, null);
        tabHost.addTab(tabHost.newTabSpec("Results").setIndicator(setCustomIndicator("Results", R.drawable.results)), ResultsTab.class, null);
        tabHost.addTab(tabHost.newTabSpec("Admin").setIndicator(setCustomIndicator("Admin", R.drawable.setup)), AdminTab.class, null);

        tabHost.setCurrentTab(0); //

        // put line back under tab
        tabHost.getTabWidget().setStripEnabled(true);
    }

    // set custom indicator

    public View setCustomIndicator(String inText, int resourceId) {
        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, null, false);// tells app what to load into tab
        ((TextView)tabIndicator.findViewById(R.id.title)).setText(inText); // sets the text on the tab
        ((ImageView)tabIndicator.findViewById(R.id.icon)).setImageResource(resourceId); // sets the image on the tab

        return tabIndicator;
    }


    @Override
    public void onAttachFragment(Fragment fragment){
        super.onAttachFragment(fragment);

        if (fragment.getClass() == TimingTab.class) {
            timingTabFragment = (TimingTab) fragment;
        } else if (fragment.getClass() == AdminTab.class) {
            adminTabFragment = (AdminTab) fragment;
        } else if (fragment.getClass() == ResultsTab.class) {
            resultsTabFragment = (ResultsTab) fragment;
        }
    }
}
