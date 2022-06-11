package com.app.tccv3;

import android.app.PendingIntent;

import com.google.android.material.timepicker.MaterialTimePicker;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmDAO {

    private final static ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

    private static DateTime localTime = new DateTime();

    private static MaterialTimePicker teste = new MaterialTimePicker();

    public static int IDCounter = 1;

    public void save(PendingIntent pendingIntent) {

        intentArray.add(pendingIntent);
        IDCounter++;
    }

    public void remove() {

    }

    public ArrayList<PendingIntent> AllCurrentAlarms() {
        return new ArrayList<>(intentArray);
    }

    public int CurrentIDCounter() {
        return IDCounter;
    }

    public DateTime localTime() {
        return localTime;
    }

    public MaterialTimePicker getTeste() {
        return teste;
    }

}
