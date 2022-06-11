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

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.Calendar;

public class NewAlarmActivity extends AppCompatActivity {

    EditText alarmTime;

    private final AlarmDAO alarmDAO = new AlarmDAO();
    private MaterialTimePicker picker = alarmDAO.getTeste();
    private Calendar calendar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_alarm);
        InitializingFields();
        ConfigureSetAlarmButton(alarmDAO);
        ConfigurePickTimeButton();

    }

    private void InitializingFields() {
        alarmTime = findViewById(R.id.edittext_alarmTime);
    }

    private void ConfigureSetAlarmButton(AlarmDAO DAO) {
        Button add = findViewById(R.id.button_setAlarm);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlarmManager mgrAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
                int requestCode = alarmDAO.CurrentIDCounter();

                Intent intent = new Intent(NewAlarmActivity.this, AlarmReceiver.class);
                PendingIntent alarmIntent = PendingIntent.getBroadcast(NewAlarmActivity.this,
                        requestCode, intent, 0);
//                mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                        SystemClock.elapsedRealtime() + 30000 * requestCode, alarmIntent);

                mgrAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);

                alarmDAO.save(alarmIntent);

                finish();
            }
        });
    }

    private void ConfigurePickTimeButton() {
        Button pickTime = findViewById(R.id.button_pickTime);
        pickTime.setOnClickListener(new View.OnClickListener() {
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
