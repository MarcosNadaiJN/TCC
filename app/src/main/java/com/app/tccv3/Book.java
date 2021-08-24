package com.app.tccv3;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class Book implements Serializable {

    Book(String name){
        //this.IDBook = UUID.randomUUID().toString();
        this.name = name;
        this.finished = false;
    }

    //private String IDBook;
    private Uri BookImage;
    private String name;
    private String Author;
    private String BookCategory;
    private int totalPages;
    private int currentPage;
    private int readPages;
    private int leftPages;
    private boolean finished;

    public Uri getBookImage() {
        return BookImage;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return Author;
    }

    public String getBookCategory() {
        return BookCategory;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getReadPages() {
        return readPages;
    }

    public int getLeftPages() {
        return leftPages;
    }

    @Override
    public String toString() {
        return name;
    }
}
