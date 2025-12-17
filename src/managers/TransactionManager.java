package managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataModel.Book;
import enums.BookStatus;
import filemanager.BookFileHandler;
import filemanager.TransactionFileHandler;
import dataModel.Patron;
import dataModel.Reservation;
import dataModel.Transaction;

public class TransactionManager {
	
	private TransactionFileHandler tf = new TransactionFileHandler();
	private BookFileHandler bf = new BookFileHandler();
	
    private List<Transaction> transactions = Transaction.transactions;

    private UserManager userManager;
    private BookManager bookManager;
    private ReservationManager reservationManager;

    public TransactionManager(UserManager um, BookManager bm, ReservationManager rm) {
        this.userManager = um;
        this.bookManager = bm;
        this.reservationManager = rm;
        
		if (transactions.isEmpty()) {
			transactions.addAll(tf.loadTransactions());
    }
	}
	
    public boolean checkoutBook(String patronId, String bookId) {

        Book targetBook = bookManager.searchBookById(bookId);
        if (targetBook == null) return false;

        if (targetBook.getStatus() == BookStatus.CHECKED_OUT) return false;

        Patron targetPatron = (Patron) userManager.searchUserById(patronId);
        if (targetPatron == null) return false;

        // ===== RESERVATION CHECK =====
        Reservation earliestReservation = null;

        for (Reservation r : Reservation.reservations) {
            if (r.getBookId().equalsIgnoreCase(bookId)) {
                if (earliestReservation == null ||
                    r.getReservationDate().isBefore(earliestReservation.getReservationDate())) {
                    earliestReservation = r;
                }
            }
        }

        // If the book is reserved by someone else → deny checkout
        if (earliestReservation != null &&
            !earliestReservation.getPatronId().equalsIgnoreCase(patronId)) {
            return false;
        }

        // ===== CREATE TRANSACTION =====
        String transactionId = "T-" + (transactions.size() + 1);
        LocalDate checkoutDate = LocalDate.now();
        LocalDate dueDate = checkoutDate.plusDays(14);

        Transaction t = new Transaction(transactionId, patronId, bookId, checkoutDate, dueDate);
        transactions.add(t);

        // ===== UPDATE STATES =====
        targetBook.setStatus(BookStatus.CHECKED_OUT);
        targetPatron.addCurrentLoan(bookId);
        targetPatron.addToHistory(bookId);


        bf.saveBooks(Book.books);
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
		
		Patron patron = (Patron)userManager.searchUserById(patronId);
        if (patron != null) {
            patron.removeCurrentLoan(bookId);
        }
        
        reservationManager.notifyPatronIfAvailable(bookId);
		bf.saveBooks(Book.books);
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





































