package EntityPackage;

import java.util.Comparator;

public class TitleComparator implements Comparator<Book>{
	public int compare(Book a, Book b) {
        return a.name.compareToIgnoreCase(b.name);
    }
}