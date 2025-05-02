package RepositoryPackage;
import EntityPackage.*;

import java.util.*;
public class LibraryRepository {
    public Map<String, Book> books = new HashMap<>();
    Map<String, User> users = new HashMap<>();
    Map<String, Library> libraries = new HashMap<>();
    Map<String, Librarian> librarians = new HashMap<>();
    Map<String, String> issuedBooks = new HashMap<>();

    public void addBook(Book book) {	
        books.put(book.bookId, book);
    }

    public void addUser(User user) {
        users.put(user.id, user);
    }

    public void addLibrary(Library library) {
        libraries.put(library.name, library);
    }

    public void addLibrarian(Librarian librarian) {
        librarians.put(librarian.employeeId, librarian);
    }

    public Book getBook(String bookID) {
        return books.get(bookID);
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public Library getLibrary(String name) {
        return libraries.get(name);
    }

    public Librarian getLibrarian(String employeeId) {
        return librarians.get(employeeId);
    }
    
    public void issueBook(String bookID, String userID) {
        issuedBooks.put(bookID, userID);
    }

    public void returnBook(String bookID) {
        issuedBooks.remove(bookID);
    }

    public boolean isBookIssued(String bookID) {
        return issuedBooks.containsKey(bookID);
    }

    public String getIssuedTo(String bookID) {
        return issuedBooks.get(bookID);
    }
    
    public Map<String, User> getAllUsers() {
        return users;
    }

    public Map<String, Book> getAllBooks() {
        return books;
    }

    public Map<String, String> getIssuedBooks() {
        return issuedBooks;
    }
    
}

