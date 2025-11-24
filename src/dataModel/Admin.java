package dataModel;

import java.util.ArrayList;
import java.util.List;
import enums.UserType;
import managers.UserManager;

public class Admin extends User {
	
	private UserManager userManager = new UserManager();
	
	public Admin() {
		super();
		setUserType(UserType.ADMIN);
	}
	
	public Admin(String userId, String username, String password, String name, String email, String phone) {
		super(userId, username, password, name, email, phone, UserType.ADMIN);
	}
		
	public void createLibrarian(Librarian librarian) { userManager.addUser(librarian);}
	public void deleteLibrarian(String librarianId) { userManager.deleteUser(librarianId);}
	public void updateLibrarian(Librarian librarian) { userManager.updateUser(librarian);}
	public List<Librarian> searchLibrarian(String query) {
		List<Librarian> result = new ArrayList<>();
		List<User> matchs = userManager.searchUser(query);
		for(User u : matchs) {
			if (u instanceof Librarian){
				result.add((Librarian)u);
			}
		}
		return result;
	}
	
	
	public void createPatron(Patron patron) {userManager.addUser(patron); }
	public void deletePatron(String patronId) { userManager.deleteUser(patronId);}
	public void updatePatron(Patron patron) { userManager.updateUser(patron);}
	public List<Patron> searchPatron(String query) { 
		List<Patron> result = new ArrayList<>();
		List<User> matchs = userManager.searchUser(query);
		for(User u : matchs) {
			if (u instanceof Patron) {
				result.add((Patron) u);
			}
		}
		return result;
	}
	
	public void updateCredentials(String newUsername, String newPassword) {
		setUsername(newUsername);
		setPassword(newPassword);
	}


	public void addBook(Book book) {  }
	public void updateBook(Book book) {  }
	public void removeBook(String bookId) {  }
	public void categorizeBook(String bookId, String category) {  }
	public void trackBookStatus() { }
	
}
