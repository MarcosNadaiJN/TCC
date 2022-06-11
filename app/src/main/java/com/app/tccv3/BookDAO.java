package com.app.tccv3;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private final static List<Book> bookList = new ArrayList<>();

    private final static List<Book> finishedBookList = new ArrayList<>();

    private final static List<BookWishList> wishList = new ArrayList<>();

    public static int iDCounter = 1;

    public static void initDadosTest() {

        Book book1 = new Book("teste nf", "aut 1", 100, 10);
        Book book2 = new Book("teste nf2", "aut 2", 200, 20);

        Book book3 = new Book("teste f", "aut 2", 60, 60);
        Book book4 = new Book("teste f2", "aut 3", 70, 70);

        save(book1);
        save(book2);
        save(book3);
        save(book4);
    }

    public static void save(Book book) {

        if (book.getFinished()) {
            book.setId(iDCounter);
            finishedBookList.add(book);
            iDCounter++;
        } else {
            book.setId(iDCounter);
            bookList.add(book);
            iDCounter++;
        }
    }

    public static void save(BookWishList bookWishList) {

        bookWishList.setId(iDCounter);
        wishList.add(bookWishList);
        iDCounter++;
    }

    public static void edit(Book book) {

        Book chosenBook = null;
        boolean willRemove = false;
        int positionOfBookForRemoval = 0;

        for (Book a :
                bookList) {
            if (a.getId() == book.getId()) {
                chosenBook = a;
                if (book.getFinished()) {
                    finishedBookList.add(book);
                    positionOfBookForRemoval = bookList.indexOf(chosenBook);
                    willRemove = true;
                } else {
                    int position = bookList.indexOf(chosenBook);
                    bookList.set(position, book);
                }
            }
        }
        if (willRemove) {
            bookList.remove(positionOfBookForRemoval);
            willRemove = false;
        }

        for (Book b :
                finishedBookList) {
            if (b.getId() == book.getId()) {
                chosenBook = b;
                if (book.getFinished()) {
                    int position = finishedBookList.indexOf(chosenBook);
                    finishedBookList.set(position, book);
                } else {
                    bookList.add(book);
                    positionOfBookForRemoval = finishedBookList.indexOf(chosenBook);
                    willRemove = true;
                }
            }
        }
        if (willRemove) {
            finishedBookList.remove(positionOfBookForRemoval);
            willRemove = false;
        }
    }

    public static void delete(Integer ID, Integer flag) {

        Book chosenBook = null;
        boolean willDelete = false;

        if (flag == 1){
            for (Book a : bookList) {
                if (a.getId() == ID){
                    chosenBook = a;
                    willDelete = true;
                }
            }
            if (willDelete) {
                bookList.remove(chosenBook);
                willDelete = false;
            }
        } else {
            for (Book a : finishedBookList) {
                if (a.getId() == ID){
                    willDelete = true;
                    chosenBook = a;
                }
            }
            if (willDelete) {
                finishedBookList.remove(chosenBook);
                willDelete = false;
            }
        }
    }

    public static void delete(Integer ID) {
        BookWishList chosenBook = null;
        wishList.removeIf(a -> a.getId() == ID);
    }

    public static List infoHeaderCurrentBooks() {

        int totalBooks = bookList.size();
        int totalPages = 0;
        int readPages = 0;
        List<Integer> values = new ArrayList<>();

        int i = 0;
        while (i < totalBooks) {
            Book atual = bookList.get(i);
            totalPages += atual.getTotalPages();
            readPages += atual.getCurrentPage();
            i++;
        }
        values.add(totalBooks);
        values.add(totalPages);
        values.add(readPages);

        return values;
    }

    public static List infoHeaderFinishedBooks() {

        int totalBooks = finishedBookList.size();
        int totalPages = 0;
        int readPages = 0;
        List<Integer> values = new ArrayList<>();

        int i = 0;
        while (i < totalBooks) {
            Book atual = finishedBookList.get(i);
            totalPages += atual.getTotalPages();
            readPages += atual.getCurrentPage();
            i++;
        }
        values.add(totalBooks);
        values.add(totalPages);
        values.add(readPages);

        return values;
    }

    public static List<Book> allCurrentBooks() {
        return new ArrayList<>(bookList);
    }

    public static List<Book> allFinishedBooks() {
        return new ArrayList<>(finishedBookList);
    }

    public static List<BookWishList> allWishListBooks(){
        return new ArrayList<>(wishList);
    }

}
