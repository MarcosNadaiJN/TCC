package com.app.tccv3;

import java.util.UUID;

public class books{

    books(){
       String ID = UUID.randomUUID().toString();
       boolean finished = false;
    }

    private String ID;
    private String name;
    private String Author;
    private String listID;
    private int totalPages;
    private int currentPage;
    private int readPages;
    private int leftPages;
    private boolean finished;

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getListID() {
        return listID;
    }

    public void setListID(String listID) {
        this.listID = listID;
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

    public int getReadPages() {
        return readPages;
    }

    public void setReadPages(int readPages) {
        if (readPages <= getTotalPages()) {
            this.readPages = readPages;
            if (getReadPages() == getTotalPages())
                setFinished(true);
        } else {
            //throw Exception
        }
    }

    public int getLeftPages() {
        return leftPages;
    }

    public void setLeftPages(int leftPages) {
        this.leftPages = leftPages;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
