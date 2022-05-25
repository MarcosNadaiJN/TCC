package com.app.tccv3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditAlarmActivity extends AppCompatActivity {

    EditText alarmTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_alarm);
        InitializingFields();

//        AlarmDAO DAO = new AlarmDAO();

        ConfigureSetAlarmButton(DAO);
        ConfigurePickTimeButton();
        ConfigureCancelAlarmButton();
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
        Button pickTime = findViewById(R.id.button_pickTime);
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
