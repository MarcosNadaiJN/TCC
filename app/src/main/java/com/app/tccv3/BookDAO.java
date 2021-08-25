package com.app.tccv3;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private final static List<Book> BookList = new ArrayList<>();

    private static int IDCounter = 1;


    public void save(Book book) {
        Book.setID(IDCounter);
        BookList.add(book);
        IDCounter++;
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

    public List<Book> AllBooks() {
        return new ArrayList<>(BookList);
    }

}
