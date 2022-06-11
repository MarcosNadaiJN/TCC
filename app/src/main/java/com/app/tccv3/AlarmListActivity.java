package com.app.tccv3;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AlarmListActivity extends AppCompatActivity {
    TextView Title;
    ListView listAlarmlist;
    FloatingActionButton newAlarm;

    private final AlarmDAO DAO = new AlarmDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmlist);
        InitializingFields();
        ConfigureNewAlarmButton();
    }

    @Override
    protected void onResume() {

        super.onResume();
        ConfigureCurrentAlarmList();
    }

    private void InitializingFields(){
        Title = findViewById(R.id.textview_titleAlarmList);
        listAlarmlist = findViewById(R.id.listview_listOfAlarms);
        newAlarm = findViewById(R.id.floatingActionButton_newAlarm);
    }

    private void ConfigureNewAlarmButton(){
        newAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewAlarmActivity();
            }
        });
    }

    private void openNewAlarmActivity(){
        Intent intent = new Intent(this, NewAlarmActivity.class);
        startActivity(intent);
    }


    private void ConfigureCurrentAlarmList() {
        ListView AlarmListView = findViewById(R.id.listview_listOfAlarms);
        final List<PendingIntent> AlarmList = DAO.AllCurrentAlarms();
        AlarmListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                AlarmList));
        AlarmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PendingIntent chosenAlarm = AlarmList.get(position);
                Intent OpenAlarmEditor = new Intent(AlarmListActivity.this, EditAlarmActivity.class);
                OpenAlarmEditor.putExtra("alarm", chosenAlarm);
                startActivity(OpenAlarmEditor);
            }
        });
    }
}
