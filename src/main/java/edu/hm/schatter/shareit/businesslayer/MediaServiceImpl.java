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

        if (book.getAuthor().equals("") || book.getTitle().equals("")) {
            result = MediaServiceResult.INVALID_INFORMATION;

        } else if (doesISBNexist(book.getIsbn())) {
            result = MediaServiceResult.ALREADY_EXISTS;

        } else {
            BOOKS.add(book);
            result = MediaServiceResult.OK;
        }

        return result;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        final MediaServiceResult result;

        if (disc.getDirector().equals("") || disc.getTitle().equals("") || disc.getFsk() < 0 || disc.getFsk() > 18) {
            result = MediaServiceResult.INVALID_INFORMATION;

        } else if (doesBarcodeExist(disc.getBarcode())) {
            result = MediaServiceResult.ALREADY_EXISTS;

        } else {
            DISCS.add(disc);
            result = MediaServiceResult.OK;
        }

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

    private boolean doesISBNexist(String isbn) {
        boolean isbnExists = false;

        for (Book book : BOOKS) {
            if (book.getIsbn().equals(isbn)) {
                isbnExists = true;
                break;
            }
        }

        return isbnExists;
    }

    private boolean doesBarcodeExist(String barcode) {
        boolean barcodeExists = false;

        for (Disc disc : DISCS) {
            if (disc.getBarcode().equals(barcode)) {
                barcodeExists = true;
                break;
            }
        }

        return barcodeExists;
    }
}
