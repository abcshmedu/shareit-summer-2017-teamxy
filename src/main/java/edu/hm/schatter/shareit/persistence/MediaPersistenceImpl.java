package edu.hm.schatter.shareit.persistence;

import edu.hm.hibernate.HibernateUtils;
import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MediaPersistenceImpl implements MediaPersistence {

    private SessionFactory sessionFactory;
    private Session entityManager;
    private Transaction tx;

    private void init() {
        sessionFactory = HibernateUtils.getSessionFactory();
        entityManager = sessionFactory.getCurrentSession();
        tx = entityManager.beginTransaction();
    }

    @Override
    public void createBook(Book book) {
        init();
        entityManager.persist(book);
        tx.commit();
    }

    @Override
    public Book[] getBooks() {
        init();
        final String queryString = "from Book";
        final Query<Book> query = entityManager.createQuery(queryString);
        final List<Book> result = query.list();
        tx.commit();

        // result.toArray() doesn't work properly, so we need to do this by hand...
        final Book[] books = new Book[result.size()];
        for (int i = 0; i < result.size(); i++) {
            books[i] = result.get(i);
        }

        return books;
    }

    @Override
    public Book getBook(String isbn){
        init();
        final String queryString = "from Book b where b.isbn = " + isbn;
        final Query<Book> query = entityManager.createQuery(queryString);
        final List<Book> result = query.list();

        final Book book = result.size() == 1 ? result.get(0) : null;
        tx.commit();

        return book;
    }

    @Override
    public void updateBook(String isbn, Book book) {
        final Book oldBook = getBook(isbn);

        init();
        entityManager.delete(oldBook);
        entityManager.flush();

        entityManager.persist(book);
        tx.commit();
    }

    @Override
    public Disc[] getDiscs() {
        init();
        final String queryString = "from Disc";
        final Query<Disc> query = entityManager.createQuery(queryString);
        final List<Disc> result = query.list();
        tx.commit();

        // result.toArray() doesn't work properly, so we need to do this by hand...
        final Disc[] discs = new Disc[result.size()];
        for (int i = 0; i < result.size(); i++) {
            discs[i] = result.get(i);
        }

        return discs;
    }

    @Override
    public Disc getDisc(String barcode) {
        init();
        final String queryString = "from Disc d where d.barcode = " + barcode;
        final Query<Disc> query = entityManager.createQuery(queryString);
        final List<Disc> result = query.list();

        final Disc disc = result.size() == 1 ? result.get(0) : null;
        tx.commit();

        return disc;
    }

    @Override
    public void createDisc(Disc disc) {
        init();
        entityManager.persist(disc);
        tx.commit();
    }

    @Override
    public void updateDisc(String barcode, Disc disc) {
        final Disc oldDisc = getDisc(barcode);

        init();
        entityManager.delete(oldDisc);
        entityManager.flush();

        entityManager.persist(disc);
        tx.commit();
    }
}
