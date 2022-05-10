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
    TextView Book_TotalPages;
    EditText Book_TotalPages_Value;
    TextView Book_CurrentPage;
    EditText Book_CurrentPage_Value;
    TextView Book_LeftPages;
    TextView Book_LeftPages_Value;
    TextView Book_is_finished_value;

    Book book;
    Integer flagBookList;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_editor);
        InitializingFields();
        BookDAO DAO = new BookDAO();
        ConfigureCancelButton();
        ConfigureSaveButton(DAO);
        ConfigureDeleteButton(DAO);
        ImagePicker();
        getBookInformation();

    }

    private void getBookInformation() {

        Intent data = getIntent();
        book = (Book) data.getSerializableExtra("book");
        Book_Image.setImageURI(book.getBookImage());
        Book_Name.setText(book.getName());
        Book_Author.setText(book.getAuthor());
        Book_TotalPages_Value.setText(String.valueOf(book.getTotalPages()));
        Book_CurrentPage_Value.setText(String.valueOf(book.getCurrentPage()));
        Book_LeftPages_Value.setText(String.valueOf(book.getLeftPages()));
        if (book.getFinished() == false) Book_is_finished_value.setText("Não");
        else Book_is_finished_value.setText("Sim");

        Intent flag = getIntent();
        flagBookList = (Integer) flag.getSerializableExtra("flag");
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

        Book_Image = findViewById(R.id.BookImage_editor);
        Book_Name = findViewById(R.id.edittext_title_book_editor);
        Book_Author = findViewById(R.id.edittext_author_book_editor);
        Book_TotalPages = findViewById(R.id.textview_total_pages_book_editor);
        Book_TotalPages_Value = findViewById(R.id.edittext_total_pages_book_value_editor);
        Book_CurrentPage = findViewById(R.id.textview_currentpage_book_editor);
        Book_CurrentPage_Value = findViewById(R.id.edittext_currentpage_book_value_editor);
        Book_LeftPages = findViewById(R.id.textview_leftpages_book_editor);
        Book_LeftPages_Value = findViewById(R.id.textview_leftpages_book_value_editor);
        Book_is_finished_value = findViewById(R.id.textView_book_is_finished_value);
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

    private void ConfigureSaveButton(BookDAO DAO) {

        Button delete = (Button) findViewById(R.id.button_save_book);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttBook();
                DAO.edit(book);

                finish();
            }
        });
    }

    private void ConfigureDeleteButton(BookDAO DAO) {

        Button add = (Button) findViewById(R.id.button_delete);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DAO.delete(book.getID(), flagBookList);

                finish();
            }
        });
    }

    private void AttBook() {

        String bookName = Book_Name.getText().toString();
        String bookAuthor = Book_Author.getText().toString();
        String bookTotalPages = Book_TotalPages_Value.getText().toString();
        int book_TotalPages_value = Integer.parseInt(bookTotalPages);
        String bookCurrentPages = Book_CurrentPage_Value.getText().toString();
        int book_CurrentPages_value = Integer.parseInt(bookCurrentPages);
        int book_LeftPages_value = book_TotalPages_value - book_CurrentPages_value;

        book.setName(bookName);
        book.setAuthor(bookAuthor);
        book.setTotalPages(book_TotalPages_value);
        book.setCurrentPage(book_CurrentPages_value);
        book.setLeftPages(book_LeftPages_value);
        book.setLeftPages(book_LeftPages_value);
        book.setFinished(book_TotalPages_value == book_CurrentPages_value);
    }

    private void DeleteBook() {


    }

    //Sets PickedImage as BookImage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        Book_Image.setImageURI(uri);
    }
}
