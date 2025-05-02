package Service;
import java.util.*;

import EntityPackage.*;
import RepositoryPackage.*;

public class LibraryService 
{
	
	LibraryRepository repo;

	public LibraryService(LibraryRepository repo) 
    {
        this.repo = repo;
    }

    public void registerUser(String name, String id, String email, String contact) 
    {
    	if(repo.getUser(id)==null) {
            User user = new User(name, id, email, contact);
            repo.addUser(user);
            }
        	else {
        		System.out.println("User with UserId "+id+ " already exists");
        	}
    }

    public void addNewBook(String name, String author, String library, String bookID,int price, double rating) 
    {
    	if(repo.getBook(bookID) == null) {
    		 
            Book book = new Book(name, author, library, bookID, price, rating);
            repo.addBook(book);
            System.out.println("Book added successfully!");
        }
      	else {
        		System.out.println("Book with BookId "+bookID+ " already exists");
      	}

    }

    public void addLibrary(String name, String address) 
    {
        Library library = new Library(name, address);
        repo.addLibrary(library);
    }

    public void assignLibrarian(String name, String email, String empId) 
    {
        Librarian librarian = new Librarian(name, email, empId);
        repo.addLibrarian(librarian);
    }
    
    public  Book findBook(String bookID) 
    {
        if(repo.getBook(bookID)==null) {
    		System.out.println("Book with Book Id : "+bookID+" is not found.");
    		return null;
    	}
        return repo.getBook(bookID);

    }

    public  User findUser(String userID) 
    {
    	if(repo.getUser(userID)==null) {
    		System.out.println("User with User Id : "+userID+" is not found.");
    		return null;
    	}
        return repo.getUser(userID);

    }

    public boolean issueBook(String bookID, String userID) {
        Book book = repo.getBook(bookID);
        User user = repo.getUser(userID);
        
        if (book == null || user == null) {
            System.out.println("Either Book or User does not exist.");
            return false;
        }

        if (repo.isBookIssued(bookID)) {
            System.out.println("Book already issued to User ID: " + repo.getIssuedTo(bookID));
            return false;
            
        }

        repo.issueBook(bookID, userID);
        System.out.println("Book issued successfully!");
        return true;
    }

    public boolean returnBook(String bookID, String userID) {
        if (!repo.isBookIssued(bookID)) {
            System.out.println("Book was not issued to anyone.");
            return false;
        }

        String issuedTo = repo.getIssuedTo(bookID);
        if (!issuedTo.equals(userID)) {
            System.out.println("This book was issued to a different user.");
            return false;
        }

        repo.returnBook(bookID);
        System.out.println("Book returned successfully!");
        return true;
    }
    
    public void printAllUsersAndIssuedBooks() {
        Map<String, User> users = repo.getAllUsers();
        Map<String, String> issuedBooks = repo.getIssuedBooks();

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (Map.Entry<String, User> entry : users.entrySet()) {
            String userId = entry.getKey();
            User user = entry.getValue();
            System.out.println("\nUser: " + user.name + " (ID: " + user.id + ")");
            boolean hasBooks = false;

            for (Map.Entry<String, String> issuedEntry : issuedBooks.entrySet()) {
                if (issuedEntry.getValue().equals(userId)) {
                    Book book = repo.getBook(issuedEntry.getKey());
                    System.out.println("   - Issued Book: " + book.name + " (ID: " + book.bookId + ")");
                    hasBooks = true;
                }
            }

            if (!hasBooks) {
                System.out.println("   - No books issued.");
            }
        }
    }

    public void printAllBooks() {
        Map<String, Book> books = repo.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        System.out.println("\nAll Books in Library:");
        for (Book book : books.values()) {
            System.out.println(" - " + book.name + " by " + book.author + " (ID: " + book.bookId + ")");
        }
    }

    public void printAllUsers() {
        Map<String, User> users = repo.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("\nAll Registered Users:");
        for (User user : users.values()) {
            System.out.println(" - " + user.name + " (ID: " + user.id + ", Email: " + user.email + ")");
        }
    }
    
    public List<Book> getBooks() {
        return new ArrayList<>(repo.books.values());
    }
   
}
