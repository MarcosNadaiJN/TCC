package com.app.tccv3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected final BetterActivityResult<Intent, ActivityResult> activityLaucher = BetterActivityResult.registerActivityForResult(this);

    private FloatingActionButton newlist;

    private int TotalBooks;
    private int FinishedBooks;
    private int LeftBooks;
    private int TotalPages;
    private int ReadPages;
    private int LeftPages;
    private ArrayList<String> IDBookLists;


//    ActivityResultLauncher<String> GetListID = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//        @Override
//        public void onActivityResult(Uri uri){
//            System.out.println(uri);
//        }
//    });


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


    public void openNewListActivity() {
        Intent intent = new Intent(this, newListActivity.class);
        startActivity(intent);
    }

    public void openSomeActivityForResult(){
        Intent intent = new Intent(this, newListActivity.class);
        activityLaucher.launch(intent, result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                // There are no request codes
                Intent data = result.getData();
            }
        });
    }


    public void addIDBookLists(String IDBookLists) {
        this.IDBookLists.add(IDBookLists);
    }
}