package managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataModel.Book;
import enums.BookStatus;
import filemanager.TransactionFileHandler;
import dataModel.Patron;
import dataModel.Transaction;

public class TransactionManager {
	
	private TransactionFileHandler tf = new TransactionFileHandler();
	
    private List<Transaction> transactions = Transaction.transactions;

	private UserManager userManager = new UserManager();
	private BookManager bookManager = new BookManager();
	
	
	public TransactionManager () {}
	
	public boolean checkoutBook(String patronId, String bookId) { 
		Book targetBook = bookManager.searchBookById(bookId);
		if(targetBook == null) {return false;}
		
		if(targetBook.getStatus() != BookStatus.AVAILABLE) {return false;}
		
		Patron targetPatron = userManager.searchPatronById(patronId);
		if(targetPatron == null) {return false;}
		
		
		String transactionId = "T-"+ (transactions.size()+1);
		LocalDate checkoutDate = LocalDate.now();
		LocalDate dueDate = checkoutDate.plusDays(14);
		
		Transaction t = new Transaction(transactionId, patronId, bookId, checkoutDate, dueDate);
		transactions.add(t);
		
		targetBook.setStatus(BookStatus.CHECKED_OUT);
		targetPatron.addCurrentLoan(bookId);
		targetPatron.addToHistory(bookId);
		
		tf.saveTransactions(transactions);
		return true;
	}
	
	
	public boolean returnBook(String patronId, String bookId) { 
		Transaction activeTrans = null;
		for(Transaction t : transactions) {
			if (t.getBookId().equalsIgnoreCase(bookId)&&
				t.getPatronId().equalsIgnoreCase(patronId)&&
				t.getReturnDate()== null) {
					
					activeTrans = t;
				}
			}
		
		if(activeTrans == null) {return false;}
		
		activeTrans.setReturnDate(LocalDate.now());
				
		bookManager.searchBookById(bookId).setStatus(BookStatus.AVAILABLE);
		
		Patron patron = userManager.searchPatronById(patronId);
        if (patron != null) {
            patron.removeCurrentLoan(bookId);
        }
        
        tf.saveTransactions(transactions);
        return true;
	}
	
	
	public boolean renewBook(String patronId, String bookId) { 
		for(Transaction t : transactions) {
			if (t.getBookId().equalsIgnoreCase(bookId)&&
				t.getPatronId().equalsIgnoreCase(patronId)&&
				t.getReturnDate()== null) {
				
				t.setDueDate(t.getDueDate().plusDays(7));
				tf.saveTransactions(transactions);
				return true;
			}
		}
		return false;
	}
	
	
	public List<Transaction> getPatronHistory(String patronId) {
		List<Transaction> history = new ArrayList<>();
		for(Transaction t : transactions) {
			if(t.getPatronId().equalsIgnoreCase(patronId)) {
				history.add(t);
			}
		}
		return history;
	}
	
	
	public List<Transaction> getActiveLoans(String patronId){
		List<Transaction> result = new ArrayList<>();
		for(Transaction t : transactions) {
			if(t.getPatronId().equalsIgnoreCase(patronId) && t.getReturnDate() == null) {
				result.add(t);
			}
		}
		return result;
	}
}





































