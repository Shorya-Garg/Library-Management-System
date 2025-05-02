package ControlPackage;

import EntityPackage.*;
import RepositoryPackage.*;
import Service.*;

import java.util.*;

public class LibraryController {

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        LibraryRepository repository = new LibraryRepository();
        LibraryService service = new LibraryService(repository);

        service.addLibrary("ABESEC Library", "Lal Kua, Ghaziabad");
        service.assignLibrarian("Tushar Sir", "sirtushar@gmail.com", "LIB007");
        service.registerUser("Shorya Garg", "U001", "gshorya41@gmail.com", "9999999999");
        service.registerUser("Anshika", "U002", "anshika41@gmail.com", "99990086");
        service.addNewBook("Java Basics", "Tushar", "ABESEC Library", "B001",399, 4.6);
        service.addNewBook("Harry Potter", "JK Rowling", "ABESEC Library", "B003", 254, 5.0);
        service.addNewBook("Do Epic Shit", "XYZ", "ABESEC Library", "B004", 999, 5.0);
        service.addNewBook("Ramayana", "Valmiki", "ABESEC Library", "B005", 888 , 4.1);
       

        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the Library System!");
            System.out.println("1. Login as Librarian");
            System.out.println("2. Login as User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    librarianMenu(sc, service);
                    break;
                case 2:
                    userMenu(sc, service);
                    break;
                case 3:
                    System.out.println("Thank you for using the Library System.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }

    
    private static void librarianMenu(Scanner sc, LibraryService service) {
        while (true) {
            System.out.println("\n-- Librarian Menu --");
            System.out.println("1. Add New Book");
            System.out.println("2. View All Books");
            System.out.println("3. View All Users");
            System.out.println("4. View All Users and Their Issued Books");
            System.out.println("5. Search Book by ID");
            System.out.println("6. Update User Details");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Name: ");
                    String bookName = sc.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Library Name: ");
                    String library = sc.nextLine();
                    System.out.print("Enter Book ID: ");
                    String bookID = sc.nextLine();
                    System.out.print("Enter Book Price: ");
                    int price = sc.nextInt();
                    System.out.print("Enter Book Rating: ");
                    double rating = sc.nextDouble();
                    service.addNewBook(bookName, author, library, bookID, price, rating);
                    break;

                case 2:
                    service.printAllBooks();
                    break;

                case 3:
                    service.printAllUsers();
                    break;

                case 4:
                    service.printAllUsersAndIssuedBooks();
                    break;

                case 5:
                    System.out.print("Enter Book ID to search: ");
                    String searchID = sc.nextLine();
                    Book book = service.findBook(searchID);
                    if (book != null) {
                        System.out.println("Book Found:");
                        System.out.println(" - Name: " + book.name);
                        System.out.println(" - Author: " + book.author);
                        System.out.println(" - Library: " + book.library);
                        System.out.println(" - ID: " + book.bookId);
                    } else {
                        System.out.println("Book not found!");
                    }
                    break;
                    
                case 6:
                	System.out.println("Enter User Id");
                	String userID = sc.nextLine();
                	User user = service.findUser(userID);
                	if(user==null) {
                		System.out.println("No Such User Exists");
                	}else {
                		System.out.print("Enter New User Name:");
                		String userName = sc.nextLine();
                		user.name=userName;
                		System.out.print("Enter New User email:");
                		String userm = sc.nextLine();
                		user.email=userm;
                		System.out.print("Enter New User contact:");
                		String userc = sc.nextLine();
                		user.contact=userc;
                		System.out.println("User Details Update successfully!");
                	}
                	break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void userMenu(Scanner sc, LibraryService service) {
        while (true) {
            System.out.println("\n-- User Menu --");
            System.out.println("1. Register User");
            System.out.println("2. Search Book by ID");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. View Sorted Books");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter User Name: ");
                    String userName = sc.nextLine();
                    System.out.print("Enter User ID: ");
                    String userID = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Contact: ");
                    String contact = sc.nextLine();
                    service.registerUser(userName, userID, email, contact);
                    System.out.println("User registered successfully!");
                    break;
                case 2:
                    System.out.print("Enter Book ID: ");
                    String bookID = sc.nextLine();
                    Book book = service.findBook(bookID);
                    if (book != null) {
                        System.out.println("Book Found: " + book.name + " by " + book.author);
                    }
                    break;
                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    String issueBookID = sc.nextLine();
                    System.out.print("Enter Your User ID: ");
                    String issueUserID = sc.nextLine();
                    service.issueBook(issueBookID, issueUserID);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    String returnBookID = sc.nextLine();
                    System.out.print("Enter Your User ID: ");
                    String returnUserID = sc.nextLine();
                    service.returnBook(returnBookID, returnUserID);
                    break;
                case 6:
                    System.out.println("Sort books by:");
                    System.out.println("1. Price (Low to High)");
                    System.out.println("2. Rating (High to Low)");
                    System.out.println("3. Title (A-Z)");
                    System.out.print("Enter your choice: ");
                    int sortChoice = Integer.parseInt(sc.nextLine());

                    List<Book> books = new ArrayList<>(service.getBooks());

                    if (books.isEmpty()) {
                        System.out.println("No books available.");
                        break;
                    }

                    switch (sortChoice) {
                        case 1:
                            books.sort(new PriceComparator());
                            break;
                        case 2:
                            books.sort(new RatingComparator());
                            break;
                        case 3:
                            books.sort(new TitleComparator());
                            break;
                        default:
                            System.out.println("Invalid sort option.");
                            continue;
                    }

                    for (Book b : books) {
                        System.out.println(b.name + " by " + b.author + " | ₹" + b.price + " | Rating: " + b.rating);
                    }
                    break;
                case 5:
                    service.printAllBooks();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

}
