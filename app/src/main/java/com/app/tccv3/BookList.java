package com.app.tccv3;

import java.util.ArrayList;
import java.util.UUID;


public class BookList {

    BookList(String ListTitle){
        this.IDBookList = UUID.randomUUID().toString();
        this.ListTitle = ListTitle;
    }

    private String IDBookList;
    private String ListTitle;
    private int totalPages;
    private int currentPage;
    private int readPages;
    private int leftPages;
    private ArrayList<String> IDBooks;

    public String getIDBookList() {
        return IDBookList;
    }

    @Override
    public String toString() {
        return ListTitle;
    }
}
