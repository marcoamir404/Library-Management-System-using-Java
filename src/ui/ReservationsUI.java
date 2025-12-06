package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import dataModel.Patron;
import dataModel.Reservation;
import dataModel.Transaction;
import dataModel.User;
import dataModel.Book;
import managers.BookManager;
import managers.ReservationManager;
import managers.TransactionManager;
import managers.UserManager;

public class ReservationsUI extends JDialog {

    private List<User> patrons;
    private BookManager bookManager;
    private UserManager userManager;
    private ReservationManager reservationManager;
    
    

    public ReservationsUI(Frame parent, List<User> list) {
        super(parent, "Reservation History", true);
        this.patrons = list;
        this.bookManager = new BookManager();
        this.userManager = new  UserManager();
        this.reservationManager = new ReservationManager();
        

        setSize(700, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Header
        JLabel title = new JLabel("Reservation History", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        // Table
        String[] cols = {"Book ID", "Patron ID", "Patron Name", "Title", "Reservation Date", "Notified"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        List<Reservation> history = new ArrayList<>();
        
        for(User u : patrons) {
        	if (u instanceof Patron) {
                Patron p = (Patron) u;
                history.addAll(reservationManager.getReservationsByPatron(p.getUserId()));
            }  
        }
        
        for (Reservation s : history) {
            Book b = bookManager.searchBookById(s.getBookId());
            Patron p = userManager.searchPatronById(s.getPatronId());
            
            String bookTitle = (b != null) ? b.getTitle() : "Unknown Title";
            String patronName = (p != null) ? p.getName() : "Unknown Title";

            
            model.addRow(new Object[]{
                s.getBookId(),
                s.getPatronId(),
                patronName,
                bookTitle,
                s.getReservationDate(),
                s.isNotified()
            });
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());
        JPanel footer = new JPanel();
        footer.add(btnClose);
        add(footer, BorderLayout.SOUTH);
    }
}