package com.app.tccv3;

import java.util.ArrayList;
import java.util.List;

public class BookListDAO {

    private final static List<BookList> BookLists = new ArrayList<>();


    public void salva(BookList booklist) {
        BookLists.add(booklist);
    }

    public List<BookList> allLists() {
        return new ArrayList<>(BookLists);
    }

}
