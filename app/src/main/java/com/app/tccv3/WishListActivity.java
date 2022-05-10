package com.app.tccv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WishListActivity extends AppCompatActivity {
    TextView Title;
    TextView TotalBooks;
    TextView TotalBooksValue;
    ListView listWishlist;
    Button returnButton;
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
        newbook = findViewById(R.id.floatingActionButton_newBook_wishlist);
    }

    private void ConfigureNewBookButton(){
        newbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewBookWishListActivity();
            }
        });
    }

    private void openNewBookWishListActivity(){
        Intent intent = new Intent(this, NewBookWishListActivity.class);
        startActivity(intent);
    }

}
