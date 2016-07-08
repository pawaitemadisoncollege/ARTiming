package com.example.paulawaite.adventureracetimingassistant.entity;

import android.app.Application;

/**
 * Created by paulawaite on 7/6/16.
 */
public class GlobalVariables extends Application {

    private static GlobalVariables instance;

    private DataSetUp eventData;

    private GlobalVariables(){}

    public DataSetUp getEventData() {
        if (eventData == null) {
            eventData = new DataSetUp();
        }
        return eventData;
    }

    public void setEventData(DataSetUp eventData) {
        this.eventData = eventData;
    }

    public static synchronized GlobalVariables getInstance(){
        if (instance == null){
            instance = new GlobalVariables();
        }
        return instance;
    }

}
