package edu.hm.schatter.shareit.businesslayer;

import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;
import edu.hm.schatter.shareit.persistence.MediaPersistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock for the media service interface.
 */
public class MediaPersistenceMock implements MediaPersistence {

    private static final List<Book> BOOKS = new ArrayList<>();
    private static final List<Disc> DISCS = new ArrayList<>();

    @Override
    public void createBook(Book book) {
        BOOKS.add(book);
    }

    @Override
    public Book[] getBooks() {
        return BOOKS.toArray(new Book[BOOKS.size()]);
    }

    @Override
    public Book getBook(String isbn){
        for (Book book : BOOKS) {
            if (isbn.equals(book.getIsbn())) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void updateBook(String isbn, Book book) {
        for (Book bookToBeRemoved : BOOKS) {
            if (isbn.equals(bookToBeRemoved.getIsbn())) {
                BOOKS.remove(bookToBeRemoved);
                BOOKS.add(book);
            }
        }
    }

    @Override
    public Disc[] getDiscs() {
        return DISCS.toArray(new Disc[DISCS.size()]);
    }

    @Override
    public Disc getDisc(String barcode) {
        for (Disc disc : DISCS) {
            if (barcode.equals(disc.getBarcode())) {
                return disc;
            }
        }
        return null;
    }

    @Override
    public void createDisc(Disc disc) {
        DISCS.add(disc);
    }

    @Override
    public void updateDisc(String barcode, Disc disc) {
        for (Disc discToBeRemoved : DISCS) {
            if (barcode.equals(discToBeRemoved.getBarcode())) {
                DISCS.remove(discToBeRemoved);
                DISCS.add(disc);
            }
        }
    }

    /**
     * Resets the storage.
     */
    void resetStorage() {
        BOOKS.clear();
        DISCS.clear();
    }
}