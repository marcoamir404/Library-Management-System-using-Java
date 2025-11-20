package dataModel;
import enums.UserType;

public class Librarian extends User{
	
	public Librarian() {super();}
	
	public Librarian(String userId, String username, String password, String name, String email, String phone){
		super(userId, username, password, name, email, phone, UserType.LIBRARIAN);
	}
	
	public boolean checkoutBook(String patronId, String bookId) { return false; }
	public boolean returnBook(String patronId, String bookId) { return false; }
	public boolean reserveBook(String patronId, String bookId) { return false; }
	public void notifyPatron(String patronId, String message) { }
}