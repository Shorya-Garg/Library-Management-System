package EntityPackage;
import java.util.Comparator;

public class PriceComparator implements Comparator<Book>{
	public int compare(Book a, Book b) {
        return Integer.compare(a.price, b.price);
    }
}
