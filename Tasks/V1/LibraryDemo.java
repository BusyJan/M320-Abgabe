// /src/main/java/ch/tbz/library/LibraryDemo.java
package ch.tbz.library;

import java.util.*;

public class LibraryDemo {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.addAll(List.of(
                new Book("978-3-16-148410-0","Clean Code","Robert C. Martin",2008),
                new Book("978-0-13-468599-1","Effective Java","Joshua Bloch",2018),
                new Book("978-0-596-52068-7","Head First Design Patterns","Eric Freeman",2004),
                new Book("978-1-59327-584-6","The Linux Command Line","William Shotts",2012),
                new Book("978-1-491-92465-1","Designing Data-Intensive Applications","Martin Kleppmann",2017)
        ));

        // 1) Comparable-Demo (natürliche Ordnung: Jahr -> Titel)
        System.out.println("=== Sort (Comparable: year, then title) ===");
        List<Book> byYear = new ArrayList<>(lib);
        Collections.sort(byYear); // nutzt Book.compareTo
        byYear.forEach(System.out::println);

        // 2) Comparator-Demo (nur Titel)
        System.out.println("\n=== Sort (Comparator: title) ===");
        List<Book> byTitle = new ArrayList<>(lib);
        byTitle.sort(new BookTitleComparator());
        byTitle.forEach(System.out::println);

        // 3) Set & Map
        System.out.println("\n=== Unique authors (Set) ===");
        System.out.println(lib.uniqueAuthors());

        System.out.println("\n=== ISBN index (Map) ===");
        Map<String, Book> index = lib.toIsbnIndex();
        System.out.println(index.get("978-0-13-468599-1"));

        // 4) Eigener Iterator (nur Bücher ab 2010)
        System.out.println("\n=== Recent books (Iterator from 2010) ===");
        Iterator<Book> it = lib.recentIterator(2010);
        while (it.hasNext()) System.out.println(it.next());
    }
}
