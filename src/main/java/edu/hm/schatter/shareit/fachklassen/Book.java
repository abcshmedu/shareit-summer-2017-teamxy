package edu.hm.schatter.shareit.fachklassen;

public class Book {
    private final String author;
    private final String isbn;

    private Book() {
        author = "";
        isbn = "";
    }

    public Book(String author, String isbn) {
        if (author == null){
            throw new IllegalArgumentException("author must not be null");
        }

        if (isbn == null){
            throw new IllegalArgumentException("isbn must not be null");
        }

        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!author.equals(book.author)) return false;
        return isbn.equals(book.isbn);

    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + isbn.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{author='" + author + '\'' + ", isbn='" + isbn + '\'' + '}';
    }
}