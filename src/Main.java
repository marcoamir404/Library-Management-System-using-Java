
import dataModel.*;
import enums.*;
import managers.*;

import ui.LoginUI; // لاحظ هنا استدعينا شاشة الدخول
import ui.UpdatePatronAccountUI;

import javax.swing.SwingUtilities;

import ui.AddBookForm;
import ui.BookDetailsUI;
import ui.BookSearchUI;
import ui.EditBookForm;
public class Main {

    public static void main(String[] args) {
/*
        System.out.println("==== LIBRARY MANAGEMENT SYSTEM TEST ====\n");

        // Managers
        */UserManager userManager = new UserManager();
        /*BookManager bookManager = new BookManager();
        TransactionManager transactionManager = new TransactionManager();
        ReservationManager reservationManager = new ReservationManager();
        
        // ----------------------------
        // 1. Create Admin, Librarian, Patron
        // ----------------------------
        
        Admin admin = new Admin("A1", "admin1", "1234", "Admin User", "admin@mail.com", "01000");
        Librarian librarian = new Librarian("L1", "lib1", "abcd", "Librarian User", "lib@mail.com", "01111");
        */Patron p1 = new Patron("P1", "p1", "1111", "Ahmed Ali", "ahmed@mail.com", "01222");
        /*Patron p2 = new Patron("P2", "p2", "2222", "Sara Mohamed", "sara@mail.com", "01333");

        userManager.addUser(admin);
        userManager.addUser(librarian);
        userManager.addUser(p1);
        userManager.addUser(p2);

        System.out.println("Users Added.");
        System.out.println("----------------------------------");


        // ----------------------------
        // 2. Admin adds books
        // ----------------------------
        Book b1 = new Book("B1", "Clean Code", "Robert Martin", "Programming", 2008, "A book about writing clean code.");
        Book b2 = new Book("B2", "Harry Potter", "J.K. Rowling", "Fantasy", 1997, "Magic world adventure.");
        Book b3 = new Book("B3", "Data Structures", "Weiss", "Education", 2000, "DSA fundamentals.");

        admin.addBook(b1);
        admin.addBook(b2);
        admin.addBook(b3);

        System.out.println("Books Added by Admin.");
        System.out.println("----------------------------------");


        // ----------------------------
        // 3. Librarian checks out a book for a patron
        // ----------------------------
        System.out.println("Librarian checkout B1 for P1:");
        boolean checkout1 = librarian.checkoutBook("P1", "B1");
        System.out.println("Checkout success? " + checkout1);
        System.out.println("B1 Status: " + b1.getStatus());
        System.out.println("----------------------------------");


        // ----------------------------
        // 4. Patron P2 reserves the same book
        // ----------------------------
        System.out.println("P2 reserves B1:");
        boolean reserve = librarian.reserveBook("P2", "B1");
        System.out.println("Reservation success? " + reserve);
        System.out.println("----------------------------------");


        // ----------------------------
        // 5. Librarian returns the book → triggers auto notification
        // ----------------------------
        System.out.println("Librarian returns B1 for P1:");
        boolean returnSuccess = librarian.returnBook("P1", "B1");
        System.out.println("Return success? " + returnSuccess);
        System.out.println("B1 Status: " + b1.getStatus());
        System.out.println("----------------------------------");


        // ----------------------------
        // 6. Librarian manually notifies a patron
        // ----------------------------
        System.out.println("Librarian manually notifies P2:");
        librarian.notifyPatron("P2", "Your book is ready for pickup.");
        System.out.println("----------------------------------");


        // ----------------------------
        // 7. Patron searches for books using filters
        // ----------------------------
        System.out.println("Patron P1 searches for programming books:");
        Filters f = new Filters();
        f.genre = "Programming";
        var searchResult = p1.searchBooks("", f);

        for (Book b : searchResult) {
            System.out.println("Found: " + b.getTitle());
        }
        System.out.println("----------------------------------");


        // ----------------------------
        // 8. Patron views checkout history + current loans
        // ----------------------------
        System.out.println("P1 Checkout History:");
        for (Transaction t : p1.viewCheckoutHistory()) {
            System.out.println("Book: " + t.getBookId() + ", Checkout: " + t.getCheckoutDate());
        }

        System.out.println("\nP1 Current Loans:");
        for (Transaction t : p1.ViewCurrentLoans()) {
            System.out.println("Loan Book: " + t.getBookId());
        }
        System.out.println("----------------------------------");
     // ============================================================
        // الجزء الجديد: تشغيل الواجهة الرسومية (GUI)
        // ============================================================
        System.out.println("\n>> Launching Login Application...");
        System.out.println(">> Hint: Try logging in with Username: p1 / Password: 1111");
*/
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // فتح شاشة تسجيل الدخول بدلاً من شاشة البحث مباشرة
                //new LoginUI().setVisible(true);
                new BookSearchUI().setVisible(true);
                
            	//new EditBookForm(null, null, null, null, null, null, null).setVisible(true);
            	//new AddBookForm().setVisible(true);
            	
            }
            });

        System.out.println("==== TEST COMPLETED ====");
    }
}
