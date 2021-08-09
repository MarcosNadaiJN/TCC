package com.app.tccv3;

import java.util.UUID;

public class books{

    books(){
       String IDBook = UUID.randomUUID().toString();
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

}
