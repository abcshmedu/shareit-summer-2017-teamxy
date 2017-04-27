package edu.hm.schatter.shareit.businesslayer;

import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;

public interface MediaService {

    public MediaServiceResult addBook(Book book);

    public MediaServiceResult addDisc(Disc disc);

    public Book[] getBooks();

    public Disc[] getDiscs();

    public MediaServiceResult updateBook(String isbn, Book book);

    public MediaServiceResult updateDisc(Disc disc);
}
