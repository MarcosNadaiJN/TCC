package com.app.tccv3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class EditAlarmActivity extends AppCompatActivity {

    EditText alarmTime;
    private MaterialTimePicker picker = new MaterialTimePicker();
    private Calendar calendar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_alarm);
        initializingFields();
        getAlarmInformation();
        configureSetAlarmButton();
        configureCancelAlarmButton();
        configurePickTimeButton();
    }

    private void initializingFields() {
        alarmTime = findViewById(R.id.editTextAlarmTimeEditor);
    }

    private void getAlarmInformation() {
        alarmTime.setText(MainActivity.alarmTime);

    }

    private void configureSetAlarmButton() {
        Button add = findViewById(R.id.buttonSetAlarmEditor);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlarmManager mgrAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);

                Intent intent = new Intent(EditAlarmActivity.this, AlarmReceiver.class);
                PendingIntent alarmIntent = PendingIntent.getBroadcast(EditAlarmActivity.this,
                        1, intent, 0);

                mgrAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);

                AlarmDAO.save(alarmIntent);
                MainActivity.alarmTime = (String.format("%02d",picker.getHour())+":"+String.format("%02d",picker.getMinute()));

                finish();
            }
        });
    }

    private void configureCancelAlarmButton() {
        Button pickTime = findViewById(R.id.buttonDeleteAlarmEditor);
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmDAO.remove();
                finish();
            }
        });
    }

    private void configurePickTimeButton() {
        Button pickTimeButton = findViewById(R.id.buttonPickTimeEditor);
        pickTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePicker();
            }
        });
    }

    private void showTimePicker() {

        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(0)
                .setMinute(0)
                .setTitleText("Selecione a Hora do Lembrete")
                .build();

        picker.show(getSupportFragmentManager(), "Leitura");

        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alarmTime.setText(String.format("%02d",picker.getHour())+":"+String.format("%02d",picker.getMinute()));

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar.set(Calendar.MINUTE, picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);

            }
        });
    }
}
