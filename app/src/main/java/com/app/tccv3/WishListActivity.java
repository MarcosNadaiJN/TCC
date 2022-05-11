package com.app.tccv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class WishListActivity extends AppCompatActivity {
    TextView Title;
    TextView TotalBooks;
    TextView TotalBooksValue;
    ListView listWishlist;
    FloatingActionButton newbook;

    private final BookDAO DAO = new BookDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        InitializingFields();
        ConfigureNewBookButton();
    }

    @Override
    protected void onResume() {

        super.onResume();
        ConfigureCurrentBookWishList();

    }

    private void InitializingFields(){
        Title = findViewById(R.id.textview_titlebooklistscreenWishList);
        TotalBooks = findViewById(R.id.textview_totalbooks_booklistscreenWishList);
        TotalBooksValue = findViewById(R.id.textview_totalbooksValue_booklistscreenWishList);
        listWishlist = findViewById(R.id.listview_listofbooks);
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


    private void ConfigureCurrentBookWishList() {
        ListView BookListView = findViewById(R.id.listview_listofbooks_wishList);
        final List<BookWishList> bookLists = DAO.AllWishListBooks();
        BookListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bookLists));
        BookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookWishList chosenBook = bookLists.get(position);
                Intent OpenBookEditor = new Intent(WishListActivity.this, EditBookWishListActivity.class);
                OpenBookEditor.putExtra("book", chosenBook);
                startActivity(OpenBookEditor);
            }
        });
    }
}
