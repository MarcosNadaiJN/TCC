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

public class EditBookWishListActivity extends AppCompatActivity {

    ImageView bookImageView;
    EditText bookNameEditText;
    EditText bookAuthorEditText;
    EditText bookTotalPagesValueEditText;
    TextView bookTotalPagesTextView;
    BookWishList bookWishList;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_wishlist_editor);
        initializingFields();
        configureReadButton();
        configureCancelButton();
        configureDeleteButton();
        imagePicker();
        getBookInformation();

    }

    private void initializingFields() {

        bookImageView = findViewById(R.id.BookImageWishList);
        bookNameEditText = findViewById(R.id.editTextBookTitleWishList);
        bookAuthorEditText = findViewById(R.id.editTextBookAuthorWishList);
        bookTotalPagesTextView = findViewById(R.id.textViewTotalPagesWishList);
        bookTotalPagesValueEditText = findViewById(R.id.editTextTotalPagesValueWishList);
    }

    private void configureReadButton() {

        Button readButton = (Button) findViewById(R.id.buttonReadBookWishList);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openAddNewBookIntent = new Intent(EditBookWishListActivity.this,
                        NewBookActivity.class);
                openAddNewBookIntent.putExtra("book", convertBookWishListToBook(bookWishList));
                finish();
                startActivity(openAddNewBookIntent);
                BookDAO.delete(bookWishList.getId());
            }
        });
    }

    private void configureCancelButton() {

        Button cancelButton = (Button) findViewById(R.id.buttonCancelWishList);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void configureDeleteButton() {

        Button deleteButton = (Button) findViewById(R.id.buttonDeleteWishList);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BookDAO.delete(bookWishList.getId());
                finish();
            }
        });
    }

    private void imagePicker() {

        bookImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.Companion.with(EditBookWishListActivity.this)
                        //.galleryOnly()
                        .crop()
                        .start();
            }
        });
    }

    private void getBookInformation() {

        Intent data = getIntent();
        bookWishList = (BookWishList) data.getSerializableExtra("book");
        bookImageView.setImageURI(bookWishList.getBookImage());
        bookNameEditText.setText(bookWishList.getName());
        bookAuthorEditText.setText(bookWishList.getAuthor());
        bookTotalPagesValueEditText.setText(String.valueOf(bookWishList.getTotalPages()));
    }

    private Book convertBookWishListToBook(BookWishList bookWishList) {

        return new Book(bookWishList.getName(), bookWishList.getAuthor(),
                bookWishList.getTotalPages(), 0);
    }

    //Sets PickedImage as BookImage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        bookImageView.setImageURI(uri);
    }
}
