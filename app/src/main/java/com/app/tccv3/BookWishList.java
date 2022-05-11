package com.app.tccv3;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class BookWishList implements Serializable {

    BookWishList(String name, String Author, int totalPages){
//        this.BookImage = Image;
        this.name = name;
        this.Author = Author;
        this.totalPages = totalPages;
        this.ID = BookDAO.IDCounter;
    }

    private int ID;
    private Uri BookImage;
    private String name;
    private String Author;
    private int totalPages;


    public int getID() {
        return ID;
    }

    public Uri getBookImage() {
        return BookImage;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return Author;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public void setBookImage(Uri bookImage) {
        BookImage = bookImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return name;
    }
}
