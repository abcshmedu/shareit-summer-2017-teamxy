package edu.hm.schatter.shareit.persistence;

import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;

// TODO alles ändern

public interface MediaPersistence {

    Book[] getBooks();

    Book getBook(String isbn);

    void createBook(Book book);

    void updateBook(String isbn, Book book);

    // ----------------------------------------------

    Disc[] getDiscs();

    Disc getDisc(String barcode);

    void createDisc(Disc disc);

    void updateDisc(String barcode, Disc disc);
}
