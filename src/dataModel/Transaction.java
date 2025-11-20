package dataModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Transaction {
	
    public static ArrayList<Transaction> transactions = new ArrayList<>();

	private String transactionId;
	private String patronId;
	private String bookId;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	
	public Transaction() {super();}
	
	public Transaction(String transactionId, String patronId, String bookId, LocalDate checkoutDate, LocalDate dueDate) {
		setTransactionId(transactionId);
		setPatronId(patronId);
		setBookId(bookId);
		setCheckoutDate(checkoutDate);
		setDueDate(dueDate);
	}
	
	public String getTransactionId() {return transactionId;}
	public void setTransactionId(String transactionId ) {this.transactionId = transactionId;}
	public String getPatronId() {return patronId;}
	public void setPatronId(String patronId) {this.patronId = patronId;}
	public String getBookId() {return bookId;}
	public void setBookId(String bookId) {this.bookId = bookId;}
	public LocalDate getCheckoutDate() {return checkoutDate;}
	public void setCheckoutDate(LocalDate checkoutDate) {this.checkoutDate = checkoutDate;}
	public LocalDate getDueDate() {return dueDate;}
	public void setDueDate(LocalDate dueDate) {this.dueDate = dueDate;}
	public LocalDate getReturnDate() {return returnDate;}
	public void setReturnDate(LocalDate returnDate) {this.returnDate = returnDate;}
	
	public boolean isOverdue() {
		if(returnDate != null) {return returnDate.isAfter(dueDate); }
		return LocalDate.now().isAfter(dueDate);
	}
}
