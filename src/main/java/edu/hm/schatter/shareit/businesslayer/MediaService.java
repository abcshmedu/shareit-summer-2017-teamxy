package edu.hm.schatter.shareit.businesslayer;

import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;

public interface MediaService {

    MediaServiceResult addBook(Book book);

    MediaServiceResult addDisc(Disc disc);

    Book[] getBooks();

    Disc[] getDiscs();

    MediaServiceResult updateBook(String isbn, Book book);

    MediaServiceResult updateDisc(String barcode, Disc disc);

    Book getBookByISBN(String isbn);

    Disc getDiscByBarcode(String barcode);
}
