package com.app.tccv3;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class Book implements Serializable {


    Book(String bookImage, String name, String Author, int totalPages, int CurrentPage){
        this.bookImage = bookImage;
        this.name = name;
        this.author = Author;
        this.totalPages = totalPages;
        this.currentPage = CurrentPage;
        this.leftPages = totalPages - CurrentPage;
        if (totalPages == CurrentPage) this.finished = true;
        else this.finished = false;
        this.id = BookDAO.iDCounter;
    }
    Book(String name, String Author, int totalPages, int CurrentPage){
        this.name = name;
        this.author = Author;
        this.totalPages = totalPages;
        this.currentPage = CurrentPage;
        this.leftPages = totalPages - CurrentPage;
        if (totalPages == CurrentPage) this.finished = true;
        else this.finished = false;
        this.id = BookDAO.iDCounter;
    }

    private int id;
    private String bookImage;
    private String name;
    private String author;
    private int totalPages;
    private int currentPage;
    private int leftPages;
    private boolean finished;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImageUri) {
        this.bookImage = bookImageUri;
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLeftPages() {
        return leftPages;
    }

    public void setLeftPages(int leftPages) {
        this.leftPages = leftPages;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return name;
    }
}
