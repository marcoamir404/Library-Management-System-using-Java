package dataModel;

import enums.UserType;
import managers.TransactionManager;
import managers.UserManager;
import managers.ReservationManager;

public class Librarian extends User{
	private TransactionManager transactionManager;
	private ReservationManager reservationManager;
	private UserManager userManager;
	
	public Librarian() {
		super();
		setUserType(UserType.LIBRARIAN);
		transactionManager = new TransactionManager();
		reservationManager = new ReservationManager();
		userManager = new UserManager();
	}
	
	public Librarian(String userId, String username, String password, String name, String email, String phone){
		super(userId, username, password, name, email, phone, UserType.LIBRARIAN);
		transactionManager = new TransactionManager();
		reservationManager = new ReservationManager();
		userManager = new UserManager();
	}
	
	
	public boolean checkoutBook(String patronId, String bookId) {
		return transactionManager.checkoutBook(patronId, bookId);
	}
	
	public boolean returnBook(String patronId, String bookId) {
		boolean result = transactionManager.returnBook(patronId, bookId);

        if (result) {
            reservationManager.notifyPatronIfAvailable(bookId);
        }

        return result;
	}
	public boolean reserveBook(String patronId, String bookId) { return reservationManager.reserveBook(patronId, bookId); }
	
	public void notifyPatron(String patronId, String message) { 
		 if (patronId == null || patronId.isEmpty()) return;

			Patron p = userManager.searchPatronById(patronId);

	        if (p == null) {
	            System.out.println("Invalid patron ID.");
	            return;
	        }

	        System.out.println("MESSAGE TO " + p.getName() + " (" + p.getEmail() + "): " + message);

	}
}