package edu.hm.schatter.shareit.businesslayer;

import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;

import java.util.ArrayList;
import java.util.List;

public class MediaServiceImpl implements MediaService {

    private static final List<Book> BOOKS = new ArrayList<>();
    private static final List<Disc> DISCS = new ArrayList<>();

    @Override
    public MediaServiceResult addBook(Book book) {
        final MediaServiceResult result;

        // prüfen ob buch valid ist

        // prüfen ob buch schon vorhanden ist

        BOOKS.add(book);
        result = MediaServiceResult.OK;

        return result;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        final MediaServiceResult result;

        // prüfen ob disc valid ist

        // prüfen ob disc schon vorhanden ist

        DISCS.add(disc);
        result = MediaServiceResult.OK;

        return result;
    }

    @Override
    public Book[] getBooks() {
        return BOOKS.toArray(new Book[BOOKS.size()]);
    }

    @Override
    public Disc[] getDiscs() {
        return DISCS.toArray(new Disc[DISCS.size()]);
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
        return null;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        return null;
    }
}
