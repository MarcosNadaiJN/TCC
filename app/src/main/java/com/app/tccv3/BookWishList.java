package com.app.tccv3;

import android.net.Uri;

import java.io.Serializable;

public class BookWishList implements Serializable {

    BookWishList(String name, String Author, int totalPages){

//        this.BookImage = Image;
        this.name = name;
        this.author = Author;
        this.totalPages = totalPages;
        this.id = BookDAO.iDCounter;
    }

    private int id;
    private Uri BookImage;
    private String name;
    private String author;
    private int totalPages;


    public Uri getBookImage() {
        return BookImage;
    }

    public void setBookImage(Uri bookImage) {
        BookImage = bookImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
