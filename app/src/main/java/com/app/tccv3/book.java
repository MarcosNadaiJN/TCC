package com.app.tccv3;

import java.util.UUID;

public class book {

    book(String name){
        this.IDBook = UUID.randomUUID().toString();
        this.name = name;
    }

    private String IDBook;
    private String name;
    private String Author;
    private String listID;
    private String IDCurrentBookList;
    private int totalPages;
    private int currentPage;
    private int readPages;
    private int leftPages;
    private boolean finished;

    @Override
    public String toString() {
        return name;
    }
}
