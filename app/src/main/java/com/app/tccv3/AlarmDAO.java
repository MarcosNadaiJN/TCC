package com.app.tccv3;

import android.app.PendingIntent;

import com.google.android.material.timepicker.MaterialTimePicker;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class AlarmDAO {

    private final static ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();


    public static void save(PendingIntent pendingIntent) {

        intentArray.add(pendingIntent);
    }

    public static void remove() {
        intentArray.clear();
    }

    public static ArrayList<PendingIntent> AllCurrentAlarms() {
        return new ArrayList<>(intentArray);
    }

}
