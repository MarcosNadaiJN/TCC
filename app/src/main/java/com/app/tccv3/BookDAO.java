package com.app.tccv3;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private final static List<book> BookList = new ArrayList<>();


    public void save(book book) {
        BookList.add(book);
    }

    public List<book> AllBooks() {
        return new ArrayList<>(BookList);
    }

}
