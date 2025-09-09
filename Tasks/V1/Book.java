// /src/main/java/ch/tbz/library/Book.java
package ch.tbz.library;

import java.util.Objects;

public class Book implements Comparable<Book> {
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;

    public Book(String isbn, String title, String author, int year) {
        this.isbn = Objects.requireNonNull(isbn);
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
        this.year = year;
    }

    public String isbn() { return isbn; }
    public String title() { return title; }
    public String author() { return author; }
    public int year() { return year; }

    // Natürliche Ordnung: zuerst Jahr (neuere später), dann Titel alphabetisch
    @Override
    public int compareTo(Book other) {
        int byYear = Integer.compare(this.year, other.year);
        if (byYear != 0) return byYear;
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return "%s — %s (%d) [%s]".formatted(author, title, year, isbn);
    }
}
