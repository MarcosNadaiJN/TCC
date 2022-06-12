package com.app.tccv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView totalBooksTextView;
    TextView totalPagesTextView;
    private int totalPagesValue;
    TextView readPagesTextView;
    private int readPagesValue;
    TextView leftPagesTextView;
    TextView finishedBooksTextView;
    TextView leftBooksTextView;
    TextView progressValueTextView;
    ProgressBar progressBar;
    public static String alarmTime = "00:00";
    private static Integer flagBookList = 0; // 1 for currentBookList   2 for FinishedBookList
    public static Integer currentActivity = 0; // 1 = EditAlarm -- 0 = NewAlarm


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNewBookButton();
        configNewAlarmButton();
        configCurrentBooksButton();
        flagBookList = 1;
        configFinishedBooksButton();
        configTextViews();
        configureHeaderBooks();
        configToolBar();
        configWishListButton();
        BookDAO.initDadosTest();
    }

    @Override
    protected void onResume() {

        super.onResume();
        configureCurrentBookList();
        configureHeaderBooks();
        configureProgressBar();
        configureProgressValue();
    }

    private void configNewBookButton() {

        FloatingActionButton newBookFloatingActionButton = findViewById(R.id.floatingActionButtonNewBook);
        newBookFloatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openNewBookActivity();
            }
        });
    }

    private void configNewAlarmButton() {

        FloatingActionButton newAlarmFloatingActionButton = findViewById(R.id.floatingActionButtonNewAlarm);
        newAlarmFloatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openEditAlarmActivity();
            }
        });
    }

    private void configCurrentBooksButton() {

        Button currentBooksButton = findViewById(R.id.buttonCurrentBooksList);
        currentBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                configureHeaderBooks();
                configureCurrentBookList();
            }
        });
    }

    private void configFinishedBooksButton() {

        Button finishedBooksButton = findViewById(R.id.buttonFinishedBooksList);
        finishedBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                configureHeaderBooks();
                configureFinishedBookList();
            }
        });
    }

    private void configTextViews() {

        totalBooksTextView = findViewById(R.id.textViewTotalBooksValue);
        totalPagesTextView = findViewById(R.id.textViewTotalPagesValue);
        readPagesTextView = findViewById(R.id.textViewReadPagesValue);
        leftPagesTextView = findViewById(R.id.textViewLeftPagesValueMainActivity);
        finishedBooksTextView = findViewById(R.id.textViewFinishedBooksValue);
        leftBooksTextView = findViewById(R.id.textViewLeftBooksValue);
        progressValueTextView = findViewById(R.id.textViewPercentageMainActivity);
        progressBar = findViewById(R.id.progressBarMainActivity);
    }

    private void configureHeaderBooks() {

        List<Integer> ValuesCurrentBooks = BookDAO.infoHeaderCurrentBooks();
        List<Integer> ValuesFinishedBooks = BookDAO.infoHeaderFinishedBooks();

        int totalBooksValue = ValuesCurrentBooks.get(0) + ValuesFinishedBooks.get(0);
        totalBooksTextView.setText(Integer.toString(totalBooksValue));

        int finishedBooksValue = ValuesFinishedBooks.get(0);
        finishedBooksTextView.setText(Integer.toString(finishedBooksValue));

        totalPagesValue = ValuesCurrentBooks.get(1);
        totalPagesTextView.setText(Integer.toString(totalPagesValue));

        readPagesValue = ValuesCurrentBooks.get(2);
        readPagesTextView.setText(Integer.toString(readPagesValue));

        int leftPagesValue = totalPagesValue - readPagesValue;
        leftPagesTextView.setText(Integer.toString(leftPagesValue));


        int leftBooksValue = totalBooksValue - finishedBooksValue;
        leftBooksTextView.setText(Integer.toString(leftBooksValue));

    }

    private void configToolBar() {

        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    private void configWishListButton(){

        Button wishListButton = findViewById(R.id.buttonWishList);
        wishListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWishListActivity();
            }
        });
    }

    private void configureProgressBar() {

        progressBar.setProgress(readPagesValue);
        progressBar.setMax(totalPagesValue);
    }

    private void configureProgressValue(){

        if (totalPagesValue > 0) {
            progressValueTextView.setText((readPagesValue *100)/ totalPagesValue + "%");
        } else {
            progressValueTextView.setText("0%");
        }
    }


    private void configureCurrentBookList() {

        ListView bookListView = findViewById(R.id.listViewBookList);
        final List<Book> bookLists = BookDAO.allCurrentBooks();

        bookListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bookLists));

        flagBookList = 1;

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book chosenBook = bookLists.get(position);
                Intent openBookEditorIntent = new Intent(MainActivity.this, EditBookActivity.class);
                openBookEditorIntent.putExtra("book", chosenBook);
                openBookEditorIntent.putExtra("flag", flagBookList);
                startActivity(openBookEditorIntent);

            }
        });
    }

    private void configureFinishedBookList() {

        ListView bookListView = findViewById(R.id.listViewBookList);
        final List<Book> bookLists = BookDAO.allFinishedBooks();

        bookListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bookLists));

        flagBookList = 2;

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book chosenBook = bookLists.get(position);
                Intent openBookEditorIntent = new Intent(MainActivity.this, EditBookActivity.class);
                openBookEditorIntent.putExtra("book", chosenBook);
                openBookEditorIntent.putExtra("flag", flagBookList);
                startActivity(openBookEditorIntent);

            }
        });
    }

    public void openNewBookActivity() {

        Intent intent = new Intent(this, NewBookActivity.class);
        startActivity(intent);
    }

    public void openWishListActivity() {

        Intent intent = new Intent(this, WishListActivity.class);
        startActivity(intent);
    }

    private void openEditAlarmActivity() {

        Intent intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);
    }

}
