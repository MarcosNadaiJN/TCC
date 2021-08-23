package com.app.tccv3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class newBookActivity extends AppCompatActivity {

    EditText BookName;
    EditText BookAuthor;
    TextView BookCategory;
    TextView BookTotalPages;
    EditText BookTotalPages_Value;
    TextView CurrentPage_Book;
    EditText CurrentPage_Book_Value;
    TextView ReadPages_Book;
    TextView ReadPages_Book_Value;
    TextView LeftPages_Book;
    TextView LeftPages_Book_Value;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_book);

        InitializingFields();

        BookDAO DAO = new BookDAO();

        ConfigureCancelButton();

        ConfigureAddButton(DAO,BookName);

    }

    private void InitializingFields() {
        BookName = (EditText) findViewById(R.id.edittext_title_book);
        BookAuthor = (EditText) findViewById(R.id.edittext_author_book);
        BookCategory = (TextView) findViewById(R.id.textview_book_category);
        BookTotalPages = (TextView) findViewById(R.id.textview_total_pages_book);
        BookTotalPages_Value = (EditText) findViewById(R.id.edittext_total_pages_book_value);
        CurrentPage_Book = (TextView) findViewById(R.id.textview_currentpage_book);
        CurrentPage_Book_Value = (EditText) findViewById(R.id.edittext_currentpage_book_value);
        ReadPages_Book = (TextView) findViewById(R.id.textview_readpages_book);
        ReadPages_Book_Value = (TextView) findViewById(R.id.textview_readpages_book_value);
        LeftPages_Book = (TextView) findViewById(R.id.textview_leftpages_book);
        LeftPages_Book_Value = (TextView) findViewById(R.id.textview_leftpages_book_value);
    }

    private void ConfigureCancelButton() {
        Button cancel = (Button) findViewById(R.id.button_add_book);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ConfigureAddButton(BookDAO DAO, EditText BookName) {
        Button add = (Button) findViewById(R.id.button_cancel_book);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book newBook = createBook(BookName);
                save(newBook, DAO);

                finish();
            }
        });
    }

    private void save(book List, BookDAO DAO) {
        DAO.save(List);
    }

    @NonNull
    private book createBook(EditText BookName) {
        String bookName = BookName.getText().toString();
        book newBook = new book(bookName);
        return newBook;
    }
}
