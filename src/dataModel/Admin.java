package dataModel;

import java.util.ArrayList;
import java.util.List;
import enums.UserType;

public class Admin extends User {
	public Admin() {
		super();
		setUserType(UserType.ADMIN);
	}
	
	public Admin(String userId, String username, String password, String name, String email, String phone) {
		super(userId, username, password, name, email, phone, UserType.ADMIN);
	}
	
	public void createLibrarian(Librarian librarian) { }
	public void deleteLibrarian(String librarianId) { }
	public void updateLibrarian(Librarian librarian) { }
	public List<Librarian> searchLibrarian(String query) { return new ArrayList<>(); }
	
	
	public void createPatron(Patron patron) { }
	public void deletePatron(String patronId) {  }
	public void updatePatron(Patron patron) {  }
	public List<Patron> searchPatron(String query) { return new ArrayList<>(); }


	public void addBook(Book book) {  }
	public void updateBook(Book book) {  }
	public void removeBook(String bookId) {  }
	public void categorizeBook(String bookId, String category) {  }
	public void trackBookStatus() { }
	
}
