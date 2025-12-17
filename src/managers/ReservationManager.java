package managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataModel.Book;
import dataModel.Patron;
import dataModel.Reservation;
import dataModel.User;
import enums.BookStatus;
import filemanager.ReservationFileHandler;

public class ReservationManager {
	
	private ReservationFileHandler rf = new ReservationFileHandler();
	
	private List<Reservation> reservations = Reservation.reservations;
	
	BookManager bookManager = new BookManager();
	UserManager userManager = new UserManager();
	
	public ReservationManager() {
		if (reservations.isEmpty()) {
			reservations.addAll(rf.loadReservations());
    }
	}
	
	public boolean reserveBook(String patronId, String bookId) {
		Book targetBook = bookManager.searchBookById(bookId);
		if(targetBook == null) return false;
		
		Patron targetPatron = (Patron)userManager.searchUserById(patronId);
		if(targetPatron == null) return false;
		
		for(Reservation r : Reservation.reservations) {
			if(r.getBookId().equalsIgnoreCase(bookId)&&r.getPatronId().equalsIgnoreCase(patronId)) {
				return false;
			}
		}
		
		String reservationId = "R-" + (Reservation.reservations.size()+1);
		Reservation reservation = new Reservation(reservationId, patronId, bookId, LocalDate.now());
		
		Reservation.reservations.add(reservation);
		
		if (targetBook.getStatus() == BookStatus.AVAILABLE) {
            notifyPatronIfBookCurrentAvailable(targetPatron, reservation);
        }
		rf.saveReservations(reservations);
		return true;
	}
	
	public List<Reservation> getReservationsByPatron(String patronId) {
        List<Reservation> list = new ArrayList<>();

        for (Reservation r : Reservation.reservations) {
            if (r.getPatronId().equals(patronId)) {
                list.add(r);
            }
        }

        return list;
    }
	
	public List<Reservation> getReservationsByBook(String bookId) {
        List<Reservation> list = new ArrayList<>();

        for (Reservation r : Reservation.reservations) {
            if (r.getBookId().equals(bookId)) {
                list.add(r);
            }
        }

        return list;
    }
	
	public void notifyPatronIfAvailable(String bookId) {

        Book target = bookManager.searchBookById(bookId);

        if (target == null) return;

        if (target.getStatus() != BookStatus.AVAILABLE) {
            return;
        }

        Reservation earliest = null;

        for (Reservation r : Reservation.reservations) {
            if (r.getBookId().equals(bookId) && !r.isNotified()) {
                if (earliest == null || r.getReservationDate().isBefore(earliest.getReservationDate())) {
                    earliest = r;
                }
            }
        }
        
        if (earliest != null) {
            Patron targetPatron = (Patron)userManager.searchUserById(earliest.getPatronId());
            notifyPatronIfBookCurrentAvailable(targetPatron, earliest);
            
            
        }
    }
	
	private void notifyPatronIfBookCurrentAvailable(Patron p, Reservation r) {
        r.setNotificationStatus(true);
        bookManager.searchBookById(r.getBookId()).setStatus(BookStatus.RESERVED);
        rf.saveReservations(reservations);
    }
	
	
}
