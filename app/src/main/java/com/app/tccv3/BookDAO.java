package com.app.tccv3;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private final static List<Book> BookList = new ArrayList<>();

    private final static List<Book> FinishedBookList = new ArrayList<>();

    private final static List<BookWishList> WishList = new ArrayList<>();

    public static int IDCounter = 1;

    public void initDadosTest () {

        Book book1 = new Book("teste nf", "aut", 100, 10);
        Book book2 = new Book("teste f", "aut 2", 50, 50);

        save(book1);
        save(book2);
    }


    public void save(Book book) {

        if (book.getFinished() == true) {
            book.setID(IDCounter);
            FinishedBookList.add(book);
            IDCounter++;
        } else {
            book.setID(IDCounter);
            BookList.add(book);
            IDCounter++;
        }
    }

    public void save(BookWishList bookWishList) {
        bookWishList.setID(IDCounter);
        WishList.add(bookWishList);
        IDCounter++;
    }

    public void edit(Book book) {
        Book chosenbook = null;
        boolean willremove = false;
        int positionOfBookForRemoval = 0;
        for (Book a :
                BookList) {
            if (a.getID() == book.getID()) {
                chosenbook = a;
                if (book.getFinished() == true) {
                    FinishedBookList.add(book);
                    positionOfBookForRemoval = BookList.indexOf(chosenbook);
                    willremove = true;
                } else {
                    int position = BookList.indexOf(chosenbook);
                    BookList.set(position, book);
                }
            }
        }
        if (willremove == true) {
            BookList.remove(positionOfBookForRemoval);
            willremove = false;
        }

        for (Book b :
                FinishedBookList) {
            if (b.getID() == book.getID()) {
                chosenbook = b;
                if (book.getFinished() == true) {
                    int position = FinishedBookList.indexOf(chosenbook);
                    FinishedBookList.set(position, book);
                } else {
                    BookList.add(book);
                    positionOfBookForRemoval = FinishedBookList.indexOf(chosenbook);
                    willremove = true;
                }
            }
        }
        if (willremove == true) {
            FinishedBookList.remove(positionOfBookForRemoval);
            willremove = false;
        }

        }

        public void edit(BookWishList book) {

        }

    public void delete(Integer ID, Integer flag) {
        Book chosenBook = null;
        boolean willDelete = false;
        if (flag == 1){
            for (Book a : BookList) {
                if (a.getID() == ID){
                    chosenBook = a;
                    willDelete = true;
                }
            }
            if (willDelete == true) {
                BookList.remove(chosenBook);
                willDelete = false;
            }
        } else {
            for (Book a : FinishedBookList) {
                if (a.getID() == ID){
                    willDelete = true;
                    chosenBook = a;
                }
            }
            if (willDelete == true) {
                FinishedBookList.remove(chosenBook);
                willDelete = false;
            }
        }

    }

    public void delete(Integer ID) {
        BookWishList chosenBook = null;
        WishList.removeIf(a -> a.getID() == ID);
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

    public List<Book> AllCurrentBooks() {
        return new ArrayList<>(BookList);
    }

    public List<Book> AllFinishedBooks() {
        return new ArrayList<>(FinishedBookList);
    }

    public List<BookWishList> AllWishListBooks(){
        return new ArrayList<>(WishList);
    }

}
