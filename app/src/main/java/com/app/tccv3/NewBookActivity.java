package com.app.tccv3;

import android.content.Intent;
import android.graphics.Bitmap;
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

public class NewBookActivity extends AppCompatActivity {

    EditText bookNameEditText;
    EditText bookAuthorEditText;
    EditText bookTotalPagesValueEditText;
    EditText bookCurrentPageValueEditText;
    TextView bookTotalPagesTextView;
    TextView bookCurrentPageTextView;
    TextView bookLeftPagesTextView;
    TextView bookLeftPagesValueTextView;
    String tempImage;
    ImageView bookImageImageView;
    Book book;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_book);
        initializingFields();
        configureAddButton();
        configureCancelButton();
        imagePicker();
        getBookInformation();
    }

    private void initializingFields() {
        bookImageImageView = findViewById(R.id.BookImage);
        bookNameEditText = findViewById(R.id.editTextBookTitle);
        bookAuthorEditText = findViewById(R.id.editTextBookAuthor);
        bookTotalPagesTextView = findViewById(R.id.textViewTotalPages);
        bookTotalPagesValueEditText = findViewById(R.id.editTextTotalPagesValue);
        bookCurrentPageTextView = findViewById(R.id.textViewCurrentPage);
        bookCurrentPageValueEditText = findViewById(R.id.editTextCurrentPageValue);
        bookLeftPagesTextView = findViewById(R.id.textViewLeftPages);
        bookLeftPagesValueTextView = findViewById(R.id.textViewLeftPagesValue);
    }

    private void configureAddButton() {
        Button addButton = findViewById(R.id.buttonAddBook);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book newBook = createBook();
                BookDAO.save(newBook);

                finish();
            }
        });
    }

    private void configureCancelButton() {
        Button cancelButton = findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
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
                ImagePicker.Companion.with(NewBookActivity.this)
//                        .galleryOnly()
                        .crop()
                        .start();
            }
        });
    }

    private void getBookInformation() {

        Intent data = getIntent();
        book = (Book) data.getSerializableExtra("book");
        if (data.getSerializableExtra("book") != null) {
            if(book.getBookImage() != null){
                bookImageImageView.setImageURI(Uri.parse(book.getBookImage()));
            }
            bookNameEditText.setText(book.getName());
            bookAuthorEditText.setText(book.getAuthor());
            bookTotalPagesValueEditText.setText(String.valueOf(book.getTotalPages()));
        }
    }

    @NonNull
    private Book createBook() {
        String bookImage = tempImage;
        String bookName = bookNameEditText.getText().toString();
        String bookAuthor = this.bookAuthorEditText.getText().toString();
        int bookTotalPages = Integer.parseInt(bookTotalPagesValueEditText.getText().toString());
        int bookCurrentPage = Integer.parseInt(bookCurrentPageValueEditText.getText().toString());

        return new Book(bookImage, bookName, bookAuthor, bookTotalPages, bookCurrentPage);
    }

    //Sets PickedImage as BookImage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        bookImageImageView.setImageURI(uri);
        tempImage = uri.toString();
    }
}
