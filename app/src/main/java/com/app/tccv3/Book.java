package com.app.tccv3;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class Book implements Serializable {

    private static int ID;

    Book(String name, String Author, String BookCategory, int totalPages, int CurrentPage,
         int leftPages){
        //this.IDBook = UUID.randomUUID().toString();
        this.name = name;
        this.finished = false;
    }

    //private String IDBook;
    private int BookID = 0;
    private Uri BookImage;
    private String name;
    private String Author;
    private String BookCategory;
    private int totalPages;
    private int currentPage;
    private int readPages;
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

    public void setBookCategory(String bookCategory) {
        BookCategory = bookCategory;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setReadPages(int readPages) {
        this.readPages = readPages;
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
