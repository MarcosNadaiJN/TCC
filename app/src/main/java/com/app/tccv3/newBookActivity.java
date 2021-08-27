package com.app.tccv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class newBookActivity extends AppCompatActivity {

    ImageView BookImage;
    EditText BookName;
    EditText BookAuthor;
    TextView BookTotalPages;
    EditText BookTotalPages_Value;
    TextView Book_CurrentPage;
    EditText Book_CurrentPage_Value;
    TextView Book_LeftPages;
    TextView Book_LeftPages_Value;
    Uri BookCoverImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_book);
        InitializingFields();
        BookDAO DAO = new BookDAO();
        ConfigureCancelButton();
        ConfigureAddButton(DAO);
        ImagePicker();

    }

    private void ImagePicker() {
        BookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(newBookActivity.this)
                        //.galleryOnly()
                        .crop()
                        .start();
            }
        });
    }

    private void InitializingFields() {
        BookImage = findViewById(R.id.BookImage);
        BookName = findViewById(R.id.edittext_title_book);
        BookAuthor = findViewById(R.id.edittext_author_book);
        BookTotalPages = findViewById(R.id.textview_total_pages_book);
        BookTotalPages_Value = findViewById(R.id.edittext_total_pages_book_value);
        Book_CurrentPage = findViewById(R.id.textview_currentpage_book);
        Book_CurrentPage_Value = findViewById(R.id.edittext_currentpage_book_value);
        Book_LeftPages = findViewById(R.id.textview_leftpages_book);
        Book_LeftPages_Value = findViewById(R.id.textview_leftpages_book_value);
    }

    private void ConfigureCancelButton() {
        Button cancel = findViewById(R.id.button_cancel_book);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ConfigureAddButton(BookDAO DAO) {
        Button add = findViewById(R.id.button_add_book);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book newBook = createBook();
                save(newBook, DAO);


                finish();
            }
        });
    }

    private void save(Book List, BookDAO DAO) {
        DAO.save(List);
    }

    @NonNull
    private Book createBook() {
        String bookName = BookName.getText().toString();
        String bookAuthor = BookAuthor.getText().toString();
        int book_totalPages = Integer.parseInt(BookTotalPages_Value.getText().toString());
        int book_currentPage = Integer.parseInt(Book_CurrentPage_Value.getText().toString());
        //int book_leftPages = book_totalPages - book_currentPage;

        return new Book(bookName, bookAuthor, book_totalPages, book_currentPage);
    }

    //Sets PickedImage as BookImage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        BookCoverImage = uri;
        BookImage.setImageURI(uri);
    }
}
