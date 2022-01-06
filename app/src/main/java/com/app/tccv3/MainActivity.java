package com.app.tccv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView TotalBooks;
    private int TotalBooks_value;
    TextView TotalPages;
    private int TotalPages_value;
    TextView ReadPages;
    private int ReadPages_value;
    TextView LeftPages;
    private int LeftPages_value;
    TextView FinishedBooks;
    private int FinishedBooks_value;
    TextView LeftBooks;
    private int LeftBooks_value;

    private FloatingActionButton newBook;

    private Button currentBooks;
    private Button finishedBooks;
    private Button WishList;

    private final BookDAO DAO = new BookDAO();

    private static Integer flagBookList = 0; // 1 for currentBookList   2 for FinishedBookList


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConfigNewBookButton();
        ConfigcurrentBooksButton();
        flagBookList = 1;
        ConfigfinishedBooksButton();
//        ConfigWishListButton();
        ConfigTextViews();
        ConfigToolBar();
        ConfigContextMenu();



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.floating_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_delete:
//                Toast.makeText(this,"Deletar", Toast.LENGTH_SHORT).show();
                AdapterView.AdapterContextMenuInfo info =
                        (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Integer listPosition = info.position;
                Toast.makeText(this,listPosition.toString(), Toast.LENGTH_SHORT).show();
//                DAO.delete(listPosition+1, flagBookList);
                if (flagBookList == 1) {
                    ConfigureCurrentBookList();
                    ConfigureHeaderCurrentBooks();
                } else {
                    ConfigureHeaderFinishedBooks();
                    ConfigureFinishedBookList();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void ConfigContextMenu() {
        ListView listView = findViewById(R.id.listview_listofbooks_wishlist);
        registerForContextMenu(listView);
    }

    private void ConfigToolBar() {
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void ConfigTextViews() {
        TotalBooks = findViewById(R.id.textview_totalbooksValue_booklistscreenWishList);
        TotalPages = findViewById(R.id.textView_totalpagesvalue_booklistscreen);
        ReadPages = findViewById(R.id.textView_readpagesvalue_booklistscreen);
        LeftPages = findViewById(R.id.textView_leaftpagesvalue_booklistscreen);
        FinishedBooks = findViewById(R.id.textview_totalbooksValue2_booklistscreen);
        LeftBooks = findViewById(R.id.textview_totalbooksValue3_booklistscreen);
    }

//    private void ConfigWishListButton(){
//        WishList = findViewById(R.id.button_wishlist_wishlist);
//        WishList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openWishListActivity();
//            }
//        });
//    }

    private void ConfigcurrentBooksButton() {
        currentBooks = findViewById(R.id.button_current_wishlist);
        currentBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigureHeaderCurrentBooks();
                ConfigureCurrentBookList();
            }
        });
    }

    private void ConfigfinishedBooksButton() {
        finishedBooks = findViewById(R.id.button_finished_wishlist);
        finishedBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigureHeaderFinishedBooks();
                ConfigureFinishedBookList();
            }
        });
    }

    private void ConfigNewBookButton() {
        newBook = findViewById(R.id.floatingActionButton_newBook_wishlist);
        newBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewBookActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConfigureCurrentBookList();
        ConfigureHeaderCurrentBooks();

    }

    private void ConfigureHeaderCurrentBooks() {

        List<Integer> Values = DAO.infoHeaderCurrentBooks();

        TotalBooks_value = Values.get(0);
        TotalBooks.setText(Integer.toString(TotalBooks_value));

//        FinishedBooks.setText(Integer.toString(FinishedBooks_value));

        TotalPages_value = Values.get(1);
        TotalPages.setText(Integer.toString(TotalPages_value));

        ReadPages_value  = Values.get(2);
        ReadPages.setText(Integer.toString(ReadPages_value));

        LeftPages_value = TotalPages_value - ReadPages_value;
        LeftPages.setText(Integer.toString(LeftPages_value));

    }

    private void ConfigureHeaderFinishedBooks() {

        List<Integer> Values = DAO.infoHeaderFinishedBooks();

        TotalBooks_value = Values.get(0);
        TotalBooks.setText(Integer.toString(TotalBooks_value));

//        FinishedBooks.setText(Integer.toString(FinishedBooks_value));

        TotalPages_value = Values.get(1);
        TotalPages.setText(Integer.toString(TotalPages_value));

        ReadPages_value  = Values.get(2);
        ReadPages.setText(Integer.toString(ReadPages_value));

        LeftPages_value = TotalPages_value - ReadPages_value;
        LeftPages.setText(Integer.toString(LeftPages_value));

    }

    private void ConfigureCurrentBookList() {
        ListView BookListView = findViewById(R.id.listview_listofbooks_wishlist);
        final List<Book> bookLists = DAO.AllCurrentBooks();
        BookListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bookLists));
        flagBookList = 1;
        BookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book chosenBook = bookLists.get(position);
                Intent OpenBookEditor = new Intent(MainActivity.this, EditBookActivity.class);
                OpenBookEditor.putExtra("book", chosenBook);
                startActivity(OpenBookEditor);

            }
        });
    }

    private void ConfigureFinishedBookList() {
        ListView BookListView = findViewById(R.id.listview_listofbooks_wishlist);
        final List<Book> bookLists = DAO.AllFinishedBooks();
        BookListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bookLists));
        flagBookList = 2;
        BookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book chosenBook = bookLists.get(position);
                Intent OpenBookEditor = new Intent(MainActivity.this, EditBookActivity.class);
                OpenBookEditor.putExtra("book", chosenBook);
                startActivity(OpenBookEditor);

            }
        });
    }

    public void openNewBookActivity() {
        Intent intent = new Intent(this, newBookActivity.class);
        startActivity(intent);
    }

//    public void openWishListActivity() {
//        Intent intent = new Intent(this, WishList.class);
//        startActivity(intent);
//    }
}
