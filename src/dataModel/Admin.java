package dataModel;

import java.util.List;

import enums.BookStatus;
import enums.UserType;
import managers.UserManager;
import managers.BookManager;

public class Admin extends User {
	
	private UserManager userManager;
	private BookManager bookManager;
	
	public Admin() {
		super();
		setUserType(UserType.ADMIN);
		
	}
	
	public Admin(String userId, String username, String password, String name, String email, String phone) {
		super(userId, username, password, name, email, phone, UserType.ADMIN);

	}
	
	 public void setUserManager(UserManager um) { this.userManager = um; }
	 public void setBookManager(BookManager bm) { this.bookManager = bm; }
		
	public void createLibrarian(Librarian librarian) { userManager.addUser(librarian);}
	public void deleteLibrarian(String librarianId) { userManager.deleteUser(librarianId);}
	public void updateLibrarian(Librarian librarian) { userManager.updateUser(librarian);}
	public List<User> searchLibrarian(String query) {
		List<User> result = userManager.searchLibrarian(query);
		return result;
	}
	
	public void createPatron(Patron patron) {userManager.addUser(patron); }
	public void deletePatron(String patronId) { userManager.deleteUser(patronId);}
	public void updatePatron(Patron patron) { userManager.updateUser(patron);}
	public List<User> searchPatron(String query) { 
		List<User> result = userManager.searchPatron(query);
		return result;
	}
	
	public void updateCredentials(String newUsername, String newPassword) {
		setUsername(newUsername);
		setPassword(newPassword);
	}


	public void addBook(Book book) { bookManager.addBook(book); }
	public void updateBook(Book book) { bookManager.updateBook(book); }
	public void removeBook(String bookId) { bookManager.removeBook(bookId); }
	public BookStatus trackBookStatus(String bookId) {return bookManager.trackBookStatus(bookId);}
	
}



























