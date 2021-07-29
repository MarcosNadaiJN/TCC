package com.app.tccv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton newlist;

    private int TotalBooks;
    private int FinishedBooks;
    private int LeftBooks;
    private int TotalPages;
    private int ReadPages;
    private int LeftPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        newlist = (FloatingActionButton) findViewById(R.id.floatingActionButton_newbook);
        newlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewListActivity();
            }
        });

        //lista de exemplo
        ListView bookList = (ListView) findViewById(R.id.listview_listofbooks);
        ArrayList<String> books = inflateData();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books);
        bookList.setAdapter(arrayAdapter);
    }

    public void openNewListActivity() {
        Intent intent = new Intent(this, newListActivity.class);
        startActivity(intent);
    }

    private ArrayList<String> inflateData() {
        ArrayList<String> dados = new ArrayList<String>();
        dados.add("livro 1");
        dados.add("livro 2");
        dados.add("livro 3");
        dados.add("livro 4");
        dados.add("livro 5");
        return dados;
    }
}