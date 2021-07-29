package com.app.tccv3;

import java.util.ArrayList;
import java.util.UUID;

public class BookList {

    BookList(){
        String ID = UUID.randomUUID().toString();
    }

    private String ID;
    private String ListName;
    private int totalPages;
    private int currentPage;
    private int readPages;
    private int leftPages;
    private ArrayList<String> IDBooksList;
}
