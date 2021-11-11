package com.app.tccv3;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private final static List<Book> BookList = new ArrayList<>();

    private final static List<Book> FinishedBookList = new ArrayList<>();

    private static int IDCounter = 1;


    public void save(Book book) {

        if (book.getFinished() == true) {
            Book.setID(IDCounter);
            FinishedBookList.add(book);
            IDCounter++;
        } else {
            Book.setID(IDCounter);
            BookList.add(book);
            IDCounter++;
        }
    }

    public void edit(Book book) {
        Book foundedBook = null;
        for (Book a :
                BookList) {
            if (a.getID() == book.getID()) {
                foundedBook = a;
            }
        }
        if (foundedBook != null) {
            int BookPosition = BookList.indexOf(foundedBook);
            if (book.getFinished() == true) {
                FinishedBookList.set(BookPosition, book);
            } else {
                BookList.set(BookPosition, book);
            }
        }
    }

    public static List infoHeaderCurrentBooks() {
        int TotalBooks = BookList.size();
        int TotalPages = 0;
        int ReadPages = 0;
        int test = 0;

        List<Integer> Values = new ArrayList<>();

        int i = 0;
        while (i < TotalBooks) {
            Book atual = BookList.get(i);
            TotalPages += atual.getTotalPages();
            ReadPages += atual.getCurrentPage();
            i++;
        }
        Values.add(TotalBooks);
        Values.add(TotalPages);
        Values.add(ReadPages);

        return Values;
    }

    public static List infoHeaderFinishedBooks() {
        int TotalBooks = FinishedBookList.size();
        int TotalPages = 0;
        int ReadPages = 0;
        int test = 0;

        List<Integer> Values = new ArrayList<>();

        int i = 0;
        while (i < TotalBooks) {
            Book atual = FinishedBookList.get(i);
            TotalPages += atual.getTotalPages();
            ReadPages += atual.getCurrentPage();
            i++;
        }
        Values.add(TotalBooks);
        Values.add(TotalPages);
        Values.add(ReadPages);

        return Values;
    }



//    public Integer attFinishedBooks() {
//        int i = 0;
//            while (i < BookList.size()) {
//                Book atual = BookList.get(i);
//                if (atual.getFinished() == true) {
//                    FinishedBookList.add(atual);
//                    if (BookList.size() > 0) {
//                        BookList.remove(i);
//                    }
//                } else {
//                    if (atual.getFinished() == false){
//                        BookList.add(atual);
//                        if (FinishedBookList.size() > 0) {
//                            FinishedBookList.remove(i);
//                        }
//                    }
//                }
//                i++;
//            }
//        Integer TotalFinishedBooks = FinishedBookList.size();
//        return TotalFinishedBooks;
//    }

    public List<Book> AllCurrentBooks() {
        return new ArrayList<>(BookList);
    }

    public List<Book> AllFinishedBooks() {
        return new ArrayList<>(FinishedBookList);
    }

}
