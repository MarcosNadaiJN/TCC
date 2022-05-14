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

    ImageView BookImage;
    EditText BookName;
    EditText BookAuthor;
    TextView BookTotalPages;
    EditText BookTotalPages_Value;
    Uri BookCoverImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_book_wishlist);
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
                ImagePicker.Companion.with(NewBookWishListActivity.this)
                        //.galleryOnly()
                        .crop()
                        .start();
            }
        });
    }

    private void InitializingFields() {
        BookImage = findViewById(R.id.BookImage_WishList);
        BookName = findViewById(R.id.edittext_title_book_WishList);
        BookAuthor = findViewById(R.id.edittext_author_book_WishList);
        BookTotalPages = findViewById(R.id.textview_total_pages_book_WishList);
        BookTotalPages_Value = findViewById(R.id.edittext_total_pages_book_value_wishList);
    }

    private void ConfigureCancelButton() {
        Button cancel = findViewById(R.id.button_cancel_book_WishList);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ConfigureAddButton(BookDAO DAO) {
        Button add = findViewById(R.id.button_read_book_WishList);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookWishList newBookWishList = createBook();
                save(newBookWishList, DAO);

                finish();
            }
        });
    }

    private void save(BookWishList List, BookDAO DAO) {
//        Intent data = getIntent();
//        Bundle extras = data.getExtras();
//        int listViewSize = extras.getInt("size");
//        System.out.println(listViewSize);

        DAO.save(List);
    }

    private BookWishList createBook() {
        String bookName = BookName.getText().toString();
        String bookAuthor = BookAuthor.getText().toString();
        int book_totalPages = Integer.parseInt(BookTotalPages_Value.getText().toString());
//        int book_currentPage = Integer.parseInt(Book_CurrentPage_Value.getText().toString());
        //int book_leftPages = book_totalPages - book_currentPage;

        return new BookWishList(bookName, bookAuthor, book_totalPages);
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
