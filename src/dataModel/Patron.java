package dataModel;
import java.util.ArrayList;
import java.util.List;

import enums.UserType;
import managers.BookManager;
import managers.ReservationManager;
import managers.TransactionManager;
import managers.UserManager;

public class Patron extends User {
	private BookManager bookManager;
	private TransactionManager transactionManager;
	private ReservationManager reservationManager;
	UserManager userManager;
	
	public List<String> checkoutHistory = new ArrayList<>();
	public List<String> currentLoans = new ArrayList<>();
	
	public Patron() {
		super();
		setUserType(UserType.PATRON);
	}
	
	public Patron(String userId, String username, String password, String name, String email, String phone){
		super(userId, username, password, name, email, phone, UserType.PATRON);
	}
	
	public void setUserMan(UserManager um) {this.userManager = um;}
	public void setBookMan(BookManager bm) {this.bookManager = bm;}
	public void setTransactionMan(TransactionManager tm) {this.transactionManager = tm;}
	public void setReservationMan(ReservationManager rm) {this.reservationManager = rm;}
	
	
	public List<String> getCheckoutHistory() { return checkoutHistory; }
	public List<String> getCurrentLoans() { return currentLoans; }


	public List<Book> searchBooks(String query, Filters filters) { return bookManager.searchBooks(query, filters); }
	
	public void viewBookDetails(String bookId) {
	    Book b = bookManager.searchBookById(bookId);
	    if(b==null) return;
	    
	    System.out.println("Title: " + b.getTitle());
	    System.out.println("Author: " + b.getAuthor());
	    System.out.println("Genre: " + b.getGenre());
	    System.out.println("Year: " + b.getPublicationYear());
	    System.out.println("Summary: " + b.getSummary());
	    System.out.println("Status: " + b.getStatus());
	}
	public List<Transaction> viewCheckoutHistory() { return transactionManager.getPatronHistory(getUserId()); }
	public List<Transaction> ViewCurrentLoans() { return transactionManager.getActiveLoans(getUserId());}
	
	public boolean renewBook(String bookId) {
		return transactionManager.renewBook(getUserId(), bookId);
	}
	
	public boolean reserveBook(String bookId) { return reservationManager.reserveBook(getUserId(), bookId); }
	
	public void updateUsername(String username) {
	    super.setUsername(username);
	    userManager.updateUser(this);
	}
	public void updatePassword(String password) {
	    super.setPassword(password);
	    userManager.updateUser(this);
	}
	public void updateName(String name) {
	    super.setName(name);
	    userManager.updateUser(this);
	}
	public void updatePhone(String phone) {
	    super.setPhone(phone);
	    userManager.updateUser(this);
	}
	public void updateEmail(String email) {
	    super.setEmail(email);
	    userManager.updateUser(this);
	}

	
	public void addToHistory(String bookId) {
        checkoutHistory.add(bookId);
    }

    public void addCurrentLoan(String bookId) {
        currentLoans.add(bookId);
    }

    public void removeCurrentLoan(String bookId) {
        currentLoans.remove(bookId);
    }
}













