package com.app.tccv3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int TotalBooks;
    private int FinishedBooks;
    private int LeftBooks;
    private int TotalPages;
    private int ReadPages;
    private int LeftPages;

    private FloatingActionButton newlist;
    BookListDAO DAO = new BookListDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newlist = (FloatingActionButton) findViewById(R.id.floatingActionButton_newList);
        newlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openNewListActivity();}
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView BookListView = findViewById(R.id.listview_listofbooks);
        BookListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                DAO.allLists()));
    }

    public void openNewListActivity() {
        Intent intent = new Intent(this, newListActivity.class);
        startActivity(intent);
    }
}