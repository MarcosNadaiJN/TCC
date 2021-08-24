package com.app.tccv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int TotalBooks;
    private int FinishedBooks;
    private int LeftBooks;
    private int TotalPages;
    private int ReadPages;
    private int LeftPages;

    private FloatingActionButton newBook;

    private final BookDAO DAO = new BookDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newBook = (FloatingActionButton) findViewById(R.id.floatingActionButton_newBook);
        newBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewBookActivity();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ConfigureBookList();
    }

    private void ConfigureBookList() {
        ListView BookListView = findViewById(R.id.listview_listofbooks);
        final List<Book> bookLists = DAO.AllBooks();
        BookListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bookLists));
        BookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book chosenBook = bookLists.get(position);
                Intent OpenBookEditor = new Intent(MainActivity.this, EditBookActivity.class);
                OpenBookEditor.putExtra("book", chosenBook);
                startActivity(OpenBookEditor);

            }
        });
    }

    public void openNewBookActivity() {
        Intent intent = new Intent(this, newBookActivity.class);
        startActivity(intent);
    }
}