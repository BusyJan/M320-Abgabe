// /src/main/java/ch/tbz/library/BookTitleComparator.java
package ch.tbz.library;

import java.util.Comparator;

// Alternative Ordnung: nur nach Titel
public class BookTitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        return a.title().compareToIgnoreCase(b.title());
    }
}
