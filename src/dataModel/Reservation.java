package dataModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reservation {
	
	public static ArrayList<Reservation> reservations = new ArrayList<>();
	
	private String reservationId;
	private String patronId;
	private String bookId;
	private LocalDate reservationDate;
	private boolean notified;
	
	public Reservation() {}
	
	public Reservation(String reservationId, String patronId, String bookId, LocalDate reservationDate) {
		setReservationId(reservationId);
		setPatronId(patronId);
		setBookId(bookId);
		setReservationDate(reservationDate);
		this.notified = false;
	}
	
	public String getReservationId() {return reservationId;}
	public void setReservationId(String reservationId) {this.reservationId = reservationId;}
	public String getPatronId() {return patronId;}
	public void setPatronId(String patronId) {this.patronId = patronId;}
	public String getBookId() {return bookId;}
	public void setBookId(String bookId) {this.bookId = bookId;}
	public LocalDate getReservationDate() {return reservationDate;}
	public void setReservationDate(LocalDate reservationDate) {this.reservationDate = reservationDate;}
	public boolean isNotified() {return notified;}
	public void setNotificationStatus(boolean status ) {this.notified = status;}
	
}
