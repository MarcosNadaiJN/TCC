package com.app.tccv3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class newListActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlist_of_books);

        BookListDAO DAO = new BookListDAO();

        EditText ListTitle = (EditText) findViewById(R.id.edittext_listtitle_addlistscreen);

        ConfigureCancelButton();
        ConfigureAddButton(DAO, ListTitle);

    }

    private void ConfigureCancelButton() {
        Button cancel = (Button) findViewById(R.id.button_cancel_addlist);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ConfigureAddButton(BookListDAO DAO, EditText ListTitle) {
        Button add = (Button) findViewById(R.id.button_add_addlist);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookList List = createBookList(ListTitle);
                save(List, DAO);

                finish();
            }
        });
    }

    private void save(BookList List, BookListDAO DAO) {
        DAO.save(List);
    }

    @NonNull
    private BookList createBookList(EditText ListTitle) {
        String Title = ListTitle.getText().toString();
        BookList List = new BookList(Title);
        return List;
    }
}
