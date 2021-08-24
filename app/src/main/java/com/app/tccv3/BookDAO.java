package com.app.tccv3;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private final static List<Book> BookList = new ArrayList<>();


    public void save(Book book) {
        BookList.add(book);
    }

    public List<Book> AllBooks() {
        return new ArrayList<>(BookList);
    }

}
