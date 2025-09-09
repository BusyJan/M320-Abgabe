// /src/main/java/ch/tbz/library/Library.java
package ch.tbz.library;

import java.util.*;
import java.util.function.Predicate;

public class Library extends ArrayList<Book> {

    // Liefert ein Set aller eindeutigen Autor:innen
    public Set<String> uniqueAuthors() {
        Set<String> authors = new HashSet<>();
        for (Book b : this) authors.add(b.author());
        return authors;
    }

    // Baut eine Map ISBN -> Book (schneller Lookup)
    public Map<String, Book> toIsbnIndex() {
        Map<String, Book> map = new HashMap<>();
        for (Book b : this) map.put(b.isbn(), b);
        return map;
    }

    // Standard-Iterator kommt von ArrayList; zusätzlich bieten wir einen gefilterten Iterator an:
    public Iterator<Book> recentIterator(int fromYear) {
        return new FilterIterator(this.iterator(), b -> b.year() >= fromYear);
    }

    // Innere Klasse: eigener Iterator mit Prädikats-Filter
    private static class FilterIterator implements Iterator<Book> {
        private final Iterator<Book> base;
        private final Predicate<Book> filter;
        private Book nextMatch;

        private FilterIterator(Iterator<Book> base, Predicate<Book> filter) {
            this.base = base;
            this.filter = filter;
            advance();
        }

        private void advance() {
            nextMatch = null;
            while (base.hasNext()) {
                Book candidate = base.next();
                if (filter.test(candidate)) {
                    nextMatch = candidate;
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() { return nextMatch != null; }

        @Override
        public Book next() {
            if (nextMatch == null) throw new NoSuchElementException();
            Book result = nextMatch;
            advance();
            return result;
        }
    }
}
