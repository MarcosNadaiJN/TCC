package com.app.tccv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class newListActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlist_of_books);

        EditText ListTitle = (EditText) findViewById(R.id.edittext_listtitle_addlistscreen);

        Button cancel = (Button) findViewById(R.id.button_cancel_addlist);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button add = (Button) findViewById(R.id.button_add_addlist);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = ListTitle.getText().toString();

                BookList List = new BookList(Title);
                String IDtoAdd = List.getIDBookList();


                Intent data = new Intent();
                data.setData(Uri.parse(IDtoAdd));


                finish();
            }
        });

    }


    public void add(View view) {
    }
}
