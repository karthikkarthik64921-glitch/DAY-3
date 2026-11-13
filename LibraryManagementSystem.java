
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

// Book class
class Book {
    String bookId;
    String bookName;
    boolean isAvailable;

    Book(String bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.isAvailable = true;
    }
}

// Issue History class
class IssueHistory {
    String bookId;
    String bookName;
    String studentName;
    String issueDate;
    String returnDate;
    String status;

    IssueHistory(String bookId, String bookName,
                 String studentName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.studentName = studentName;
        this.issueDate = LocalDateTime.now().toString();
        this.returnDate = "Not Returned";
        this.status = "Issued";
    }
}

// Library Management class
public class LibraryManagementSystem {

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<IssueHistory> history = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    // Add 10 default books
    static void addInitialBooks() {
        books.add(new Book("B101", "Java Programming"));
        books.add(new Book("B102", "Python Programming"));
        books.add(new Book("B103", "C Programming"));
        books.add(new Book("B104", "Data Structures"));
        books.add(new Book("B105", "Database Management"));
        books.add(new Book("B106", "Operating Systems"));
        books.add(new Book("B107", "Computer Networks"));
        books.add(new Book("B108", "Artificial Intelligence"));
        books.add(new Book("B109", "Machine Learning"));
        books.add(new Book("B110", "Web Development"));
    }

    // Check duplicate Book ID
    static boolean isDuplicate(String id) {
        for (Book b : books) {
            if (b.bookId.equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    // Add a new book
    static void addBook() {
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();

        if (isDuplicate(id)) {
            System.out.println("Book ID already exists!");
            return;
        }

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("Book name cannot be empty!");
            return;
        }

        books.add(new Book(id, name));

        System.out.println("Book added successfully!");
    }

    // Issue a book to student
    static void issueBook() {
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();

        for (Book b : books) {
            if (b.bookId.equalsIgnoreCase(id)) {

                if (!b.isAvailable) {
                    System.out.println("Book is already issued!");
                    return;
                }

                System.out.print("Enter Student Name: ");
                String student = sc.nextLine();

                if (student.trim().isEmpty()) {
                    System.out.println("Student name cannot be empty!");
                    return;
                }

                b.isAvailable = false;

                history.add(new IssueHistory(
                    b.bookId, b.bookName, student
                ));

                System.out.println("Book issued successfully!");
                return;
            }
        }

        System.out.println("Book not found!");
    }

    // Return a book
    static void returnBook() {
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();

        for (Book b : books) {
            if (b.bookId.equalsIgnoreCase(id)) {

                if (b.isAvailable) {
                    System.out.println("Book is already available!");
                    return;
                }

                // Find the latest active issue
                for (int i = history.size() - 1; i >= 0; i--) {
                    IssueHistory h = history.get(i);

                    if (h.bookId.equalsIgnoreCase(id)
                            && h.status.equals("Issued")) {

                        h.status = "Returned";
                        h.returnDate = LocalDateTime.now().toString();

                        b.isAvailable = true;

                        System.out.println("Book returned successfully!");
                        return;
                    }
                }
            }
        }

        System.out.println("Book not found or issue record unavailable!");
    }

    // Display all available books
    static void displayAvailableBooks() {
        System.out.println("\n----- AVAILABLE BOOKS -----");

        boolean found = false;

        for (Book b : books) {
            if (b.isAvailable) {
                System.out.println(
                    "Book ID: " + b.bookId +
                    " | Book Name: " + b.bookName
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books available!");
        }
    }

    // Display issued book history
    static void displayHistory() {
        System.out.println("\n----- ISSUED BOOK HISTORY -----");

        if (history.isEmpty()) {
            System.out.println("No issue history available!");
            return;
        }

        for (IssueHistory h : history) {
            System.out.println("----------------------------");
            System.out.println("Book ID: " + h.bookId);
            System.out.println("Book Name: " + h.bookName);
            System.out.println("Student Name: " + h.studentName);
            System.out.println("Issue Date: " + h.issueDate);
            System.out.println("Return Date: " + h.returnDate);
            System.out.println("Status: " + h.status);
        }
    }

    // Display all books
    static void displayAllBooks() {
        System.out.println("\n----- ALL BOOKS -----");

        for (Book b : books) {
            System.out.println(
                "Book ID: " + b.bookId +
                " | Name: " + b.bookName +
                " | Status: " +
                (b.isAvailable ? "Available" : "Issued")
            );
        }
    }

    // Main method
    public static void main(String[] args) {

        addInitialBooks();

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("   LIBRARY MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display Available Books");
            System.out.println("5. Display Issued Book History");
            System.out.println("6. Display All Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addBook();
                        break;

                    case 2:
                        issueBook();
                        break;

                    case 3:
                        returnBook();
                        break;

                    case 4:
                        displayAvailableBooks();
                        break;

                    case 5:
                        displayHistory();
                        break;

                    case 6:
                        displayAllBooks();
                        break;

                    case 7:
                        System.out.println(
                            "Thank you for using Library Management System!"
                        );
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {
                choice = 0;
                System.out.println("Please enter a valid number!");
            }

        } while (choice != 7);

        sc.close();
    }
}
