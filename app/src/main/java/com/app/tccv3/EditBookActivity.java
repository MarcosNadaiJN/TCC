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

public class EditBookActivity extends AppCompatActivity {

    ImageView Book_Image;
    EditText Book_Name;
    EditText Book_Author;
    TextView Book_Category;
    TextView Book_TotalPages;
    EditText Book_TotalPages_Value;
    TextView Book_CurrentPage;
    EditText Book_CurrentPage_Value;
    TextView Book_ReadPages;
    TextView Book_ReadPages_Value;
    TextView Book_LeftPages;
    TextView Book_LeftPages_Value;

    Book book;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_editor);
        InitializingFields();
        BookDAO DAO = new BookDAO();
        ConfigureCancelButton();
        ConfigureSaveButton(DAO, Book_Name);
        ImagePicker();

        Intent data = getIntent();
        book = (Book) data.getSerializableExtra("book");
        Book_Image.setImageURI(book.getBookImage());
        Book_Name.setText(book.getName());
        Book_Author.setText(book.getAuthor());
        //BookCategory.setText(book.getBookCategory());
        Book_TotalPages_Value.setText(String.valueOf(book.getTotalPages()));
        Book_CurrentPage_Value.setText(String.valueOf(book.getCurrentPage()));
        Book_ReadPages_Value.setText(String.valueOf(book.getReadPages()));
        Book_LeftPages_Value.setText(String.valueOf(book.getLeftPages()));

    }

    private void ImagePicker() {
        Book_Image.setOnClickListener(new View.OnClickListener() {
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
        Book_Image = (ImageView) findViewById(R.id.BookImage_editor);
        Book_Name = (EditText) findViewById(R.id.edittext_title_book_editor);
        Book_Author = (EditText) findViewById(R.id.edittext_author_book_editor);
        Book_Category = (TextView) findViewById(R.id.textview_book_category_editor);
        Book_TotalPages = (TextView) findViewById(R.id.textview_total_pages_book_editor);
        Book_TotalPages_Value = (EditText) findViewById(R.id.edittext_total_pages_book_value_editor);
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

    private void ConfigureSaveButton(BookDAO DAO, EditText BookName) {
        Button add = (Button) findViewById(R.id.button_save_book);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Book newBook = createBook(BookName);
//                save(newBook, DAO);
                AttBook();
                DAO.edit(book);

                finish();
            }
        });
    }

    private void save(Book List, BookDAO DAO) {
        DAO.save(List);
    }

    private void AttBook() {
//        Book newBook = new Book(bookName);
//        return newBook;

        String bookName = Book_Name.getText().toString();
        String bookAuthor = Book_Author.getText().toString();
        String bookCategory = Book_Category.getText().toString();
        String bookTotalPages = Book_TotalPages_Value.getText().toString();
        int book_TotalPages_value = Integer.parseInt(bookTotalPages);
        String bookCurrentPages = Book_CurrentPage_Value.getText().toString();
        int book_CurrentPages_value = Integer.parseInt(bookCurrentPages);
        String book_ReadPages = Book_ReadPages_Value.getText().toString();
        int book_ReadPages_value = Integer.parseInt(book_ReadPages);
        String book_LeftPages = Book_LeftPages_Value.getText().toString();
        int book_LeftPages_value = Integer.parseInt(book_LeftPages);

        book.setName(bookName);
        book.setAuthor(bookAuthor);
        book.setBookCategory(bookCategory);
        book.setTotalPages(book_TotalPages_value);
        book.setCurrentPage(book_CurrentPages_value);
        book.setReadPages(book_ReadPages_value);
        book.setLeftPages(book_LeftPages_value);
    }

    //Sets PickedImage as BookImage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        Book_Image.setImageURI(uri);
    }
}
