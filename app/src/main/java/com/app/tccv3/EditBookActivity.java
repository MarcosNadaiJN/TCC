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

public class EditBookActivity extends AppCompatActivity {

    ImageView BookImage;
    EditText BookName;
    EditText BookAuthor;
    TextView BookCategory;
    TextView BookTotalPages;
    EditText BookTotalPages_Value;
    TextView Book_CurrentPage;
    EditText Book_CurrentPage_Value;
    TextView Book_ReadPages;
    TextView Book_ReadPages_Value;
    TextView Book_LeftPages;
    TextView Book_LeftPages_Value;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_editor);
        InitializingFields();
        BookDAO DAO = new BookDAO();
        ConfigureCancelButton();
        ConfigureAddButton(DAO,BookName);
        ImagePicker();

        Intent data = getIntent();
        Book book = (Book) data.getSerializableExtra("book");
        BookImage.setImageURI(book.getBookImage());
        BookName.setText(book.getName());
        BookAuthor.setText(book.getAuthor());
        //BookCategory.setText(book.getBookCategory());
        BookTotalPages_Value.setText(String.valueOf(book.getTotalPages()));
        Book_CurrentPage_Value.setText(String.valueOf(book.getCurrentPage()));
        Book_ReadPages_Value.setText(String.valueOf(book.getReadPages()));
        Book_LeftPages_Value.setText(String.valueOf(book.getLeftPages()));

    }

    private void ImagePicker() {
        BookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(EditBookActivity.this)
                        //.galleryOnly()
                        .crop()
                        .start();
            }
        });
    }

    private void InitializingFields() {
        BookImage = (ImageView) findViewById(R.id.BookImage_editor);
        BookName = (EditText) findViewById(R.id.edittext_title_book_editor);
        BookAuthor = (EditText) findViewById(R.id.edittext_author_book_editor);
        BookCategory = (TextView) findViewById(R.id.textview_book_category_editor);
        BookTotalPages = (TextView) findViewById(R.id.textview_total_pages_book_editor);
        BookTotalPages_Value = (EditText) findViewById(R.id.edittext_total_pages_book_value_editor);
        Book_CurrentPage = (TextView) findViewById(R.id.textview_currentpage_book_editor);
        Book_CurrentPage_Value = (EditText) findViewById(R.id.edittext_currentpage_book_value_editor);
        Book_ReadPages = (TextView) findViewById(R.id.textview_readpages_book_editor);
        Book_ReadPages_Value = (TextView) findViewById(R.id.textview_readpages_book_value_editor);
        Book_LeftPages = (TextView) findViewById(R.id.textview_leftpages_book_editor);
        Book_LeftPages_Value = (TextView) findViewById(R.id.textview_leftpages_book_value_editor);
    }

    private void ConfigureCancelButton() {
        Button cancel = (Button) findViewById(R.id.button_cancel_book);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ConfigureAddButton(BookDAO DAO, EditText BookName) {
        Button add = (Button) findViewById(R.id.button_add_book);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book newBook = createBook(BookName);
                save(newBook, DAO);


                finish();
            }
        });
    }

    private void save(Book List, BookDAO DAO) {
        DAO.save(List);
    }

    @NonNull
    private Book createBook(EditText BookName) {
        String bookName = BookName.getText().toString();
        Book newBook = new Book(bookName);
        return newBook;
    }

    //Sets PickedImage as BookImage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        BookImage.setImageURI(uri);
    }
}
