package com.library.main;

import java.util.List;
import java.util.Scanner;

import com.library.models.Book;
import com.library.models.BookService;
import com.library.models.Member;
import com.library.models.MemberService;
import com.library.models.Transaction;
import com.library.models.TransactionService;
import com.library.utils.IdGenerator;

public class LibraryManagementSystem {
    private static BookService bookService = new BookService();
    private static MemberService memberService = new MemberService();
    private static TransactionService transactionService = new TransactionService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View All Books");
            System.out.println("4. Register Member");
            System.out.println("5. Remove Member");
            System.out.println("6. View All Members");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. View All Transactions");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    removeBook(scanner);
                    break;
                case 3:
                    viewAllBooks();
                    break;
                case 4:
                    registerMember(scanner);
                    break;
                case 5:
                    removeMember(scanner);
                    break;
                case 6:
                    viewAllMembers();
                    break;
                case 7:
                    borrowBook(scanner);
                    break;
                case 8:
                    returnBook(scanner);
                    break;
                case 9:
                    viewAllTransactions();
                    break;
                case 10:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter number of copies: ");
        int copies = scanner.nextInt();
        
        Book book = new Book();
        book.setBookId(IdGenerator.generateBookId());
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setAvailableCopies(copies);
        
        bookService.addBook(book);
        System.out.println("Book added successfully!");
    }


    private static void removeBook(Scanner scanner) {
        System.out.print("Enter book ID to remove: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        bookService.removeBook(bookId);
        System.out.println("Book removed successfully.");
    }

    private static void viewAllBooks() {
        List<Book> books = bookService.getAllBooks();
        System.out.println("\nAll Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void registerMember(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        
        Member member = new Member();
        member.setMemberId(IdGenerator.generateMemberId()); // Ensure unique memberId
        member.setName(name);
        member.setPhoneNumber(phoneNumber);
        
        memberService.registerMember(member);
        System.out.println("Member registered successfully!");
    }


    private static void removeMember(Scanner scanner) {
        System.out.print("Enter member ID to remove: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        memberService.removeMember(memberId);
        System.out.println("Member removed successfully.");
    }

    private static void viewAllMembers() {
        List<Member> members = memberService.getAllMembers();
        System.out.println("\nAll Members:");
        for (Member member : members) {
            System.out.println(member);
        }
    }

    private static void borrowBook(Scanner scanner) {
        System.out.print("Enter book ID to borrow: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // Check book availability before borrowing
        Book book = bookService.findBookById(bookId);
        if (book != null && book.getAvailableCopies() > 0) {
            transactionService.borrowBook(bookId, memberId);
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Book not available for borrowing.");
        }
    }


    private static void returnBook(Scanner scanner) {
        System.out.print("Enter book ID to return: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        transactionService.returnBook(bookId, memberId);
        System.out.println("Book returned successfully!");
    }

    private static void viewAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("All Transactions:");
            transactions.forEach(System.out::println);
        }
    }

}
