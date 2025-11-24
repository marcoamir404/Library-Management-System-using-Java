import dataModel.*;
import enums.*;
import managers.*;

public class Main {
	public static void main(String[] args) {
		 UserManager userManager = new UserManager();

	        System.out.println("===== TEST START =====");

	        // ------------------------------------------------------
	        // Create an admin
	        // ------------------------------------------------------
	        Admin admin = new Admin(
	                "A001", "admin", "1234", "System Admin",
	                "admin@lib.com", "0100000000"
	        );


	        System.out.println("\nAdmin created: " + admin.getName());

	        // ------------------------------------------------------
	        // Add Librarian
	        // ------------------------------------------------------
	        Librarian librarian1 = new Librarian(
	                "L001", "lib1", "pass1", "Ahmed",
	                "ahmed@library.com", "01011111111"
	        );

	        admin.createLibrarian(librarian1);

	        System.out.println("Added Librarian: " + librarian1.getName());

	        // ------------------------------------------------------
	        // Add Patron
	        // ------------------------------------------------------
	        Patron patron1 = new Patron(
	                "P001", "pat1", "pass2", "Sara",
	                "sara@mail.com", "01222222222"
	        );

	        admin.createPatron(patron1);

	        System.out.println("Added Patron: " + patron1.getName());


	        // ------------------------------------------------------
	        // Test Search Librarian
	        // ------------------------------------------------------
	        System.out.println("\nSearching Librarian: 'Ahmed'");

	        admin.searchLibrarian("Ahmed").forEach(lib -> {
	            System.out.println("Found Librarian → " + lib.getName());
	        });


	        // ------------------------------------------------------
	        // Test Search Patron
	        // ------------------------------------------------------
	        System.out.println("\nSearching Patron: 'Sara'");

	        admin.searchPatron("Sara").forEach(pat -> {
	            System.out.println("Found Patron → " + pat.getName());
	        });


	        // ------------------------------------------------------
	        // Test Login
	        // ------------------------------------------------------
	        System.out.println("\nTesting Login...");

	        User loginUser = userManager.login("lib1", "pass1");

	        if (loginUser != null) {
	            System.out.println("Login Success → " + loginUser.getName() + " (" + loginUser.getUserType() + ")");
	        } else {
	            System.out.println("Login Failed");
	        }


	        // ------------------------------------------------------
	        // Test Update User
	        // ------------------------------------------------------
	        System.out.println("\nUpdating Patron phone number...");

	        patron1.setPhone("01555555555");
	        admin.updatePatron(patron1);

	        System.out.println("Updated Patron Phone → " + patron1.getPhone());


	        // ------------------------------------------------------
	        // Test Delete User
	        // ------------------------------------------------------
	        System.out.println("\nDeleting Librarian L001");

	        admin.deleteLibrarian("L001");

	        if (admin.searchLibrarian("L001").isEmpty()) {
	            System.out.println("Librarian deleted successfully.");
	        } else {
	            System.out.println("Librarian was NOT deleted.");
	        }

	        // ------------------------------------------------------

	        System.out.println("\n===== TEST COMPLETE =====");
	    
	}

}
