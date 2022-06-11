package com.app.tccv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class NewBookWishListActivity extends AppCompatActivity {

    ImageView bookImageView;
    EditText bookNameEditText;
    EditText bookAuthorEditText;
    EditText bookTotalPagesValueEditText;
    TextView bookTotalPagesTextView;
    Uri bookCoverImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_book_wishlist);
        initializingFields();
        configureAddButton();
        configureCancelButton();
        imagePicker();
    }

    private void initializingFields() {

        bookImageView = findViewById(R.id.BookImageWishList);
        bookNameEditText = findViewById(R.id.editTextBookTitleWishList);
        bookAuthorEditText = findViewById(R.id.editTextBookAuthorWishList);
        bookTotalPagesTextView = findViewById(R.id.textViewTotalPagesWishList);
        bookTotalPagesValueEditText = findViewById(R.id.editTextTotalPagesValueWishList);
    }

    private void configureAddButton() {

        Button add = findViewById(R.id.buttonAddBookWishList);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookWishList newBookWishList = createBook();
                BookDAO.save(newBookWishList);

                finish();
            }
        });
    }

    private void configureCancelButton() {

        Button cancel = findViewById(R.id.buttonCancelWishList);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void imagePicker() {

        bookImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(NewBookWishListActivity.this)
                        //.galleryOnly()
                        .crop()
                        .start();
            }
        });
    }

    private BookWishList createBook() {

        String bookName = bookNameEditText.getText().toString();
        String bookAuthor = bookAuthorEditText.getText().toString();
        int bookTotalPages = Integer.parseInt(bookTotalPagesValueEditText.getText().toString());
        return new BookWishList(bookName, bookAuthor, bookTotalPages);
    }

    //Sets PickedImage as BookImage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        bookCoverImage = uri;
        bookImageView.setImageURI(uri);
    }
}
