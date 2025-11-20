package dataModel;
import java.util.ArrayList;
import java.util.List;

import enums.UserType;

public class Patron extends User {
	
	public List<String> checkoutHistory = new ArrayList<>();
	public List<String> currentLoans = new ArrayList<>();
	
	public Patron() {super();}
	
	public Patron(String userId, String username, String password, String name, String email, String phone){
		super(userId, username, password, name, email, phone, UserType.PATRON);
	}
	
	public List<String> getCheckoutHistory() { return checkoutHistory; }
	public List<String> getCurrentLoans() { return currentLoans; }


	public List<Book> searchBooks(String query) { return new ArrayList<>(); }
	public List<String> viewCheckoutHistory() { return checkoutHistory; }
	public boolean renewBook(String bookId) { return false; }
	public boolean reserveBook(String bookId) { return false; }
	
	public void updateUsername(String username) {super.setUsername(username);}
	public void updatePassword(String password) {super.setPassword(password);}
	public void updateName(String name) {super.setName(name);}
}
