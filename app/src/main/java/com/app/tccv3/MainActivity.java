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

public class MainActivity extends AppCompatActivity {

    TextView TotalBooks;
    private int TotalBooks_value;
    TextView TotalPages;
    private int TotalPages_value;
    TextView ReadPages;
    private int ReadPages_value;
    TextView LeftPages;
    private int LeftPages_value;
    TextView FinishedBooks;
    private int FinishedBooks_value;
    TextView LeftBooks;
    private int LeftBooks_value;

    TextView ProgressValue;
    ProgressBar progressBar;

    private FloatingActionButton newBook;

    private Button currentBooks;
    private Button finishedBooks;
    private Button WishList;

    private final BookDAO DAO = new BookDAO();

    private static Integer flagBookList = 0; // 1 for currentBookList   2 for FinishedBookList




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConfigNewBookButton();
        ConfigCurrentBooksButton();
        flagBookList = 1;
        ConfigFinishedBooksButton();
        ConfigTextViews();
        ConfigureHeaderBooks();
        ConfigToolBar();
        ConfigWishListButton();

        DAO.initDadosTest();

    }

    private void ConfigureProgressBar() {
        progressBar.setProgress(ReadPages_value);
        progressBar.setMax(TotalPages_value);
    }

    private void ConfigureProgressValue(){
        ProgressValue.setText((ReadPages_value*100)/TotalPages_value + "%");
    }

    private void ConfigToolBar() {

        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void ConfigTextViews() {

        TotalBooks = findViewById(R.id.textview_TotalBooksValue_WishList);
        TotalPages = findViewById(R.id.textView_totalpagesvalue_booklistscreen);
        ReadPages = findViewById(R.id.textView_readpagesvalue_booklistscreen);
        LeftPages = findViewById(R.id.textView_leaftpagesvalue_booklistscreen);
        FinishedBooks = findViewById(R.id.textview_totalbooksValue2_booklistscreen);
        LeftBooks = findViewById(R.id.textview_totalbooksValue3_booklistscreen);
        ProgressValue = findViewById(R.id.textView_percentage_booklistscreen);
        progressBar = findViewById(R.id.progressBar_booklistscreen);
    }

    private void ConfigWishListButton(){
        WishList = findViewById(R.id.button_wishlist);
        WishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWishListActivity();
            }
        });
    }

    public void openWishListActivity() {

        Intent intent = new Intent(this, WishListActivity.class);
        startActivity(intent);
    }


    private void ConfigCurrentBooksButton() {

        currentBooks = findViewById(R.id.button_current_bookList);
        currentBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConfigureHeaderBooks();
                ConfigureCurrentBookList();
            }
        });
    }

    private void ConfigFinishedBooksButton() {

        finishedBooks = findViewById(R.id.button_finished_bookList);
        finishedBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConfigureHeaderBooks();
                ConfigureFinishedBookList();
            }
        });
    }

    private void ConfigNewBookButton() {

        newBook = findViewById(R.id.floatingActionButton_newBook);
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
        ConfigureCurrentBookList();
        ConfigureHeaderBooks();
    }


    private void ConfigureHeaderBooks() {

        List<Integer> ValuesCurrentBooks = DAO.infoHeaderCurrentBooks();
        List<Integer> ValuesFinishedBooks = DAO.infoHeaderFinishedBooks();


        TotalBooks_value = ValuesCurrentBooks.get(0)+ValuesFinishedBooks.get(0);
        TotalBooks.setText(Integer.toString(TotalBooks_value));

        FinishedBooks_value = ValuesFinishedBooks.get(0);
        FinishedBooks.setText(Integer.toString(FinishedBooks_value));

        TotalPages_value = ValuesCurrentBooks.get(1);
        TotalPages.setText(Integer.toString(TotalPages_value));

        ReadPages_value  = ValuesCurrentBooks.get(2);
        ReadPages.setText(Integer.toString(ReadPages_value));

        LeftPages_value = TotalPages_value - ReadPages_value;
        LeftPages.setText(Integer.toString(LeftPages_value));


        LeftBooks_value = TotalBooks_value - FinishedBooks_value;
        LeftBooks.setText(Integer.toString(LeftBooks_value));

        ConfigureProgressBar();
        ConfigureProgressValue();
    }

    private void ConfigureCurrentBookList() {
        ListView BookListView = findViewById(R.id.listview_listofbooks);
        final List<Book> bookLists = DAO.AllCurrentBooks();
        BookListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bookLists));
        flagBookList = 1;
        BookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book chosenBook = bookLists.get(position);
                Intent OpenBookEditor = new Intent(MainActivity.this, EditBookActivity.class);
                OpenBookEditor.putExtra("book", chosenBook);
                OpenBookEditor.putExtra("flag", flagBookList);
                startActivity(OpenBookEditor);

            }
        });
    }

    private void ConfigureFinishedBookList() {
        ListView BookListView = findViewById(R.id.listview_listofbooks);
        final List<Book> bookLists = DAO.AllFinishedBooks();
        BookListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bookLists));
        flagBookList = 2;
        BookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book chosenBook = bookLists.get(position);
                Intent OpenBookEditor = new Intent(MainActivity.this, EditBookActivity.class);
                OpenBookEditor.putExtra("book", chosenBook);
                OpenBookEditor.putExtra("flag", flagBookList);
                startActivity(OpenBookEditor);

            }
        });
    }

    public void openNewBookActivity() {

        Intent intent = new Intent(this, NewBookActivity.class);
        startActivity(intent);
    }

}
