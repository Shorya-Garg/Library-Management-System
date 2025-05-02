package EntityPackage;

import java.util.Comparator;

public class RatingComparator implements Comparator<Book>{
	public int compare(Book a, Book b) {
        return Double.compare(a.rating, b.rating);
    }
}