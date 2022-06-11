package com.app.tccv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class EditBookActivity extends AppCompatActivity {

    ImageView bookImageImageView;
    EditText bookNameEditText;
    EditText bookAuthorEditText;
    EditText bookTotalPagesValueEditText;
    EditText bookCurrentPageValueEditText;
    TextView bookTotalPagesTextView;
    TextView bookCurrentPageTextView;
    TextView bookLeftPagesTextView;
    TextView bookLeftPagesValueTextView;
    TextView bookIsFinishedValueTextView;
    TextView bookProgressValueTextView;
    ProgressBar bookProgressBar;
    Book book;
    Integer flagBookList;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_editor);
        initializingFields();
        configureSaveButton();
        configureDeleteButton();
        configureCancelButton();
        imagePicker();
        getBookInformation();
    }

    private void initializingFields() {

        bookImageImageView = findViewById(R.id.BookImageBookEditor);
        bookNameEditText = findViewById(R.id.editTextBookTitleEditor);
        bookAuthorEditText = findViewById(R.id.editTextBookAuthorEditor);
        bookTotalPagesTextView = findViewById(R.id.textViewTotalPagesEditor);
        bookTotalPagesValueEditText = findViewById(R.id.editTextTotalPagesValueEditor);
        bookCurrentPageTextView = findViewById(R.id.textViewCurrentPageEditor);
        bookCurrentPageValueEditText = findViewById(R.id.editTextCurrentPageValueEditor);
        bookLeftPagesTextView = findViewById(R.id.textViewLeftPagesEditor);
        bookLeftPagesValueTextView = findViewById(R.id.textViewLeftPagesValueEditor);
        bookIsFinishedValueTextView = findViewById(R.id.textViewBooksIsFinishedValueEditor);
        bookProgressBar = findViewById(R.id.progressBarEditor);
        bookProgressValueTextView = findViewById(R.id.textViewPercentageValueEditor);
    }

    private void configureSaveButton() {

        Button save = (Button) findViewById(R.id.buttonSaveBookEditor);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                attBook();
                BookDAO.edit(book);
                finish();
            }
        });
    }

    private void configureDeleteButton() {

        Button add = (Button) findViewById(R.id.buttonDeleteEditor);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BookDAO.delete(book.getId(), flagBookList);
                finish();
            }
        });
    }

    private void configureCancelButton() {

        Button cancel = (Button) findViewById(R.id.buttonCancelBookEditor);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void imagePicker() {

        bookImageImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.Companion.with(EditBookActivity.this)
                        //.galleryOnly()
                        .crop()
                        .start();
            }
        });
    }

    private void getBookInformation() {

        Intent data = getIntent();
        book = (Book) data.getSerializableExtra("book");
        bookImageImageView.setImageURI(book.getBookImageUri());
        bookNameEditText.setText(book.getName());
        bookAuthorEditText.setText(book.getAuthor());
        bookTotalPagesValueEditText.setText(String.valueOf(book.getTotalPages()));
        bookCurrentPageValueEditText.setText(String.valueOf(book.getCurrentPage()));
        bookLeftPagesValueTextView.setText(String.valueOf(book.getLeftPages()));

        if (!book.getFinished()) bookIsFinishedValueTextView.setText("Não");
        else bookIsFinishedValueTextView.setText("Sim");

        configureProgressBar();
        configureProgressValue();

        Intent flag = getIntent();
        flagBookList = (Integer) flag.getSerializableExtra("flag");
    }

    private void configureProgressBar() {

        bookProgressBar.setProgress(book.getCurrentPage());
        bookProgressBar.setMax(book.getTotalPages());
    }

    private void configureProgressValue(){
        bookProgressValueTextView.setText(((book.getCurrentPage()*100)/book.getTotalPages()) + "%");
    }

    private void attBook() {

        String bookName = bookNameEditText.getText().toString();
        String bookAuthor = bookAuthorEditText.getText().toString();
        String bookTotalPages = bookTotalPagesValueEditText.getText().toString();
        String bookCurrentPages = bookCurrentPageValueEditText.getText().toString();
        int bookTotalPagesValue = Integer.parseInt(bookTotalPages);
        int bookCurrentPagesValue = Integer.parseInt(bookCurrentPages);
        int bookLeftPagesValue = bookTotalPagesValue - bookCurrentPagesValue;

        book.setName(bookName);
        book.setAuthor(bookAuthor);
        book.setTotalPages(bookTotalPagesValue);
        book.setCurrentPage(bookCurrentPagesValue);
        book.setLeftPages(bookLeftPagesValue);
        book.setLeftPages(bookLeftPagesValue);
        book.setFinished(bookTotalPagesValue == bookCurrentPagesValue);
    }

    //Sets PickedImage as BookImage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        bookImageImageView.setImageURI(uri);
    }
}
