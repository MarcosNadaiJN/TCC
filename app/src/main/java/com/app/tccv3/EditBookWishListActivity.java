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

    ImageView Book_Image;
    EditText Book_Name;
    EditText Book_Author;
    TextView Book_TotalPages;
    EditText Book_TotalPages_Value;

    BookWishList book;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_wishlist_editor);
        InitializingFields();
        BookDAO DAO = new BookDAO();
        ConfigureCancelButton();
        ConfigureReadButton(DAO);
        ConfigureDeleteButton(DAO);
        ImagePicker();
        getBookInformation();

    }

    private void getBookInformation() {

        Intent data = getIntent();
        book = (BookWishList) data.getSerializableExtra("book");
        Book_Image.setImageURI(book.getBookImage());
        Book_Name.setText(book.getName());
        Book_Author.setText(book.getAuthor());
        Book_TotalPages_Value.setText(String.valueOf(book.getTotalPages()));

        Intent flag = getIntent();
    }

    private void ImagePicker() {

        Book_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.Companion.with(EditBookWishListActivity.this)
                        //.galleryOnly()
                        .crop()
                        .start();
            }
        });
    }

    private void InitializingFields() {

        Book_Image = findViewById(R.id.BookImage_WishList);
        Book_Name = findViewById(R.id.edittext_title_book_WishList);
        Book_Author = findViewById(R.id.edittext_author_book_WishList);
        Book_TotalPages = findViewById(R.id.textview_total_pages_book_WishList);
        Book_TotalPages_Value = findViewById(R.id.edittext_total_pages_book_value_wishList);
    }

    private void ConfigureCancelButton() {

        Button cancel = (Button) findViewById(R.id.button_cancel_book_WishList);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void ConfigureReadButton(BookDAO DAO) {

        Button read = (Button) findViewById(R.id.button_read_book_WishList);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenAddNewBook = new Intent(EditBookWishListActivity.this,
                        NewBookActivity.class);
                OpenAddNewBook.putExtra("book", book);
                startActivity(OpenAddNewBook);
            }
        });
    }

    private void ConfigureDeleteButton(BookDAO DAO) {

        Button add = (Button) findViewById(R.id.button_delete_WishList);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DAO.delete(book.getID());

                finish();
            }
        });
    }

    private void AttBook() {

        String bookName = Book_Name.getText().toString();
        String bookAuthor = Book_Author.getText().toString();
        String bookTotalPages = Book_TotalPages_Value.getText().toString();
        int book_TotalPages_value = Integer.parseInt(bookTotalPages);

        book.setName(bookName);
        book.setAuthor(bookAuthor);
        book.setTotalPages(book_TotalPages_value);
    }

    //Sets PickedImage as BookImage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        Book_Image.setImageURI(uri);
    }
}
