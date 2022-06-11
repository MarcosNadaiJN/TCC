package com.app.tccv3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;

public class EditAlarmActivity extends AppCompatActivity {

    EditText alarmTime;
    Bundle alarm;
    private final AlarmDAO alarmDAO = new AlarmDAO();
    private MaterialTimePicker picker = alarmDAO.getTeste();

    private String time;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_alarm);
        initializingFields();
        configureSetAlarmButton();
        configurePickTimeButton();
        configureCancelAlarmButton();
        getAlarmInformation();
    }

    private void initializingFields() {
        alarmTime = findViewById(R.id.editTextAlarmTimeEditor);
    }

    private void configureSetAlarmButton() {
        Button add = findViewById(R.id.buttonSetAlarmEditor);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void configurePickTimeButton() {
        Button pickTime = findViewById(R.id.buttonPickTimeEditor);
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void configureCancelAlarmButton() {
        Button pickTime = findViewById(R.id.buttonDeleteAlarmEditor);
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getAlarmInformation() {
        Integer hour = picker.getHour();
        Integer minute = picker.getMinute();

        time = String.format("%02d",hour)+":"+String.format("%02d",minute);

        alarmTime.setText(time);
    }
}
