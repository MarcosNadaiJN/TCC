package com.app.tccv3;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class Book implements Serializable {

    private static int ID;

    Book(String name, String Author, int totalPages, int CurrentPage){
//        this.BookImage = Image;
        this.name = name;
        this.Author = Author;
        this.totalPages = totalPages;
        this.currentPage = CurrentPage;
        this.leftPages = totalPages - CurrentPage;
        if (totalPages == CurrentPage) this.finished = true;
        else this.finished = false;
    }

    private int BookID = 0;
    private Uri BookImage;
    private String name;
    private String Author;
    private int totalPages;
    private int currentPage;
    private int leftPages;
    private boolean finished;


    public static int getID() {
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

    public int getCurrentPage() {
        return currentPage;
    }

    public int getLeftPages() {
        return leftPages;
    }

    public boolean getFinished() {
        return finished;
    }

    public static void setID(int id) {
        Book.ID = id;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
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

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setLeftPages(int leftPages) {
        this.leftPages = leftPages;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return name;
    }
}
