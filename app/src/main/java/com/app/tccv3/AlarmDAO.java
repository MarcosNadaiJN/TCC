package com.app.tccv3;

import android.app.PendingIntent;

import java.util.ArrayList;
import java.util.List;

public class AlarmDAO {

    private final static ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

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

}
