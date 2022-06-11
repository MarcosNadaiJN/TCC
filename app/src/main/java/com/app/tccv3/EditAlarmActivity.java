package com.app.tccv3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.Calendar;

public class EditAlarmActivity extends AppCompatActivity {

    EditText alarmTime;
    Bundle alarm;
    private final AlarmDAO alarmDAO = new AlarmDAO();
    private MaterialTimePicker picker = alarmDAO.getTeste();

    private String time;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_alarm);
        InitializingFields();
        ConfigureSetAlarmButton(alarmDAO);
        ConfigurePickTimeButton();
        ConfigureCancelAlarmButton();
        getAlarmInformation();
    }

    private void getAlarmInformation() {
        Integer hour = picker.getHour();
        Integer minute = picker.getMinute();

        time = String.format("%02d",hour)+":"+String.format("%02d",minute);

        alarmTime.setText(time);
    }

    private void InitializingFields() {
        alarmTime = findViewById(R.id.edittext_alarmTime);
    }

    private void ConfigureSetAlarmButton(AlarmDAO DAO) {
        Button add = findViewById(R.id.button_setAlarm);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Book newBook = createBook();
//                save(newBook, DAO);

                finish();
            }
        });
    }

    private void ConfigurePickTimeButton() {
        Button pickTime = findViewById(R.id.button_pickTime);
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void ConfigureCancelAlarmButton() {
        Button pickTime = findViewById(R.id.button_deleteAlarm);
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
