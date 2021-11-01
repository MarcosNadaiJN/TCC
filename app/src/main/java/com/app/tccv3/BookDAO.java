package com.app.tccv3;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private final static List<Book> BookList = new ArrayList<>();

    private static int IDCounter = 1;


    public void save(Book book) {
        Book.setID(IDCounter);
        BookList.add(book);
        IDCounter++;
        Log.i("livro criado", "" + book + IDCounter);
    }

    public void edit(Book book) {
        Book foundedBook = null;
        for (Book a :
                BookList) {
            if (a.getID() == book.getID()) {
                foundedBook = a;
            }
        }
        if (foundedBook != null) {
            int BookPosition = BookList.indexOf(foundedBook);
            BookList.set(BookPosition, book);
        }
    }

    public static List infoHeader() {
        int TotalBooks = BookList.size();
        int TotalPages = 0;
        int ReadPages = 0;
        int test = 0;

        List<Integer> Values = new ArrayList<>();

        int i = 0;
        while (i < TotalBooks) {
            Book atual = BookList.get(i);
            TotalPages += atual.getTotalPages();
            ReadPages += atual.getCurrentPage();
            i++;
        }
        Values.add(TotalBooks);
        Values.add(TotalPages);
        Values.add(ReadPages);

        return Values;
    }

    public List<Book> AllBooks() {
        return new ArrayList<>(BookList);
    }

}
