package com.app.tccv3;

import java.util.LinkedHashMap;

public class contextMenuSynchronizer {
//    private static List<Integer> bookListIDs = new ArrayList<>();
//
//    private static List<Integer> contextMenuBooksPositions = new ArrayList<>();
//
//    private static List<Integer> finishedBooksListIDs = new ArrayList<>();
//
//    private static List<Integer> contextMenuFinishedBooksPosistions = new ArrayList<>();

    private static LinkedHashMap<Integer, Integer> CurrentBooksMap = new LinkedHashMap<Integer,
            Integer>();

    private static LinkedHashMap<Integer, Integer> FinishedBooksMap = new LinkedHashMap<>();


    BookDAO DAO = new BookDAO();


    public void addNewBook(Book book) {

        Integer bookID = book.getID();
        boolean isFinished = book.getFinished();

//        if (isFinished) {
//            finishedBooksListIDs.add(bookID);
//            contextMenuFinishedBooksPosistions.add(DAO.AllFinishedBooks().size());
//        } else {
//            bookListIDs.add(bookID);
//            contextMenuBooksPositions.add(DAO.AllCurrentBooks().size());
//        }

        if (isFinished) {

            FinishedBooksMap.put(DAO.AllFinishedBooks().size(), bookID);
        } else {

            CurrentBooksMap.put(DAO.AllCurrentBooks().size(), bookID);
        }

    }

    public Integer removeBook(Integer listPosition, Integer flagBookList) {

        if (flagBookList == 2) {

            return FinishedBooksMap.get(listPosition);
        } else {

            return CurrentBooksMap.get(listPosition);
        }
    }

}
