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
    TextView titleTextView;
    TextView totalBooksTextView;
    TextView totalBooksValueTextView;
    ListView wishList_ListView;
    FloatingActionButton newBookFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        initializingFields();
        configureNewBookButton();
        configureTotalBooksValue();
    }

    @Override
    protected void onResume() {

        super.onResume();
        configureCurrentBookWishList();
        configureTotalBooksValue();
    }

    private void initializingFields(){
        titleTextView = findViewById(R.id.textViewTitleWishListActivity);
        totalBooksTextView = findViewById(R.id.textViewTotalBooksTitleWishList);
        totalBooksValueTextView = findViewById(R.id.textViewTotalBooksValueWishList);
        wishList_ListView = findViewById(R.id.listViewBookList);
        newBookFloatingActionButton = findViewById(R.id.floatingActionButtonNewWishListBook);
    }

    private void configureNewBookButton(){
        newBookFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewBookWishListActivity();
            }
        });
    }

    private void configureTotalBooksValue() {

        Integer totalBooksValue = BookDAO.allWishListBooks().size();
        totalBooksValueTextView.setText(Integer.toString(totalBooksValue));
    }

    private void openNewBookWishListActivity(){
        Intent intent = new Intent(this, NewBookWishListActivity.class);
        startActivity(intent);
    }

    private void configureCurrentBookWishList() {
        ListView bookListView = findViewById(R.id.listViewWishList);
        final List<BookWishList> bookLists = BookDAO.allWishListBooks();

        bookListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bookLists));

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookWishList chosenBook = bookLists.get(position);
                Intent openBookEditorIntent = new Intent(WishListActivity.this, EditBookWishListActivity.class);
                openBookEditorIntent.putExtra("book", chosenBook);
                startActivity(openBookEditorIntent);
            }
        });
    }
}
