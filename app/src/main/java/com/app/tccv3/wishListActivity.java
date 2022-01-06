package com.app.tccv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class wishListActivity extends AppCompatActivity {
    TextView Title;
    TextView TotalBooks;
    TextView TotalBooksValue;
    ListView listWishlist;
    Button FinishedList;
    Button CurrentList;
    Button wishlist;
    FloatingActionButton newbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        InitializingFields();
        ConfigureNewBookButton();
    }

    private void InitializingFields(){
        Title = findViewById(R.id.textview_titlebooklistscreenWishList);
        TotalBooks = findViewById(R.id.textview_totalbooks_booklistscreenWishList);
        TotalBooksValue = findViewById(R.id.textview_totalbooksValue_booklistscreenWishList);
        listWishlist = findViewById(R.id.listview_listofbooks_wishlist);
        FinishedList = findViewById(R.id.button_finished_wishlist);
        CurrentList = findViewById(R.id.button_current_wishlist);
        wishlist = findViewById(R.id.button_wishlist_wishlist);
        newbook = findViewById(R.id.floatingActionButton_newBook_wishlist);
    }

    private void ConfigureNewBookButton(){
        newbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewBookActivity();
            }
        });
    }

    private void openNewBookActivity(){
        Intent intent = new Intent(this, newBookActivity.class);
        startActivity(intent);
    }
}
