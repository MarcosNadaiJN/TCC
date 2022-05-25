package com.app.tccv3;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewAlarmActivity extends AppCompatActivity {

    EditText alarmTime;
    EditText bookTitle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_alarm);
        InitializingFields();

//        ConfigureCancelButton();
//        ConfigureAddButton(DAO);
    }

    private void InitializingFields() {
        alarmTime = findViewById(R.id.edittext_alarmTime);
        bookTitle = findViewById(R.id.edittext_alarmTitle);
    }
}
