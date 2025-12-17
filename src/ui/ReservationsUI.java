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
    private DefaultTableModel model;
    

    public ReservationsUI(Frame parent, List<User> list) {
        super(parent, "Reservation History", true);
        this.patrons = list;
        this.bookManager = new BookManager();
        this.userManager = new  UserManager();
        this.reservationManager = new ReservationManager();
        

        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());

        
        JLabel title = new JLabel("Reservation History", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBorder(new EmptyBorder(15, 0, 15, 0));
        getContentPane().add(title, BorderLayout.NORTH);

        
        String[] cols = {"Book ID", "Patron ID", "Patron Name", "Title", "Reservation Date", "Notified"};
        model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        

        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> this.dispose());
        
        JPanel footer = new JPanel();
        footer.add(btnClose);
        getContentPane().add(footer, BorderLayout.SOUTH);
        
        JButton btnViewMessages = new JButton("View Messages");
        btnViewMessages.setBackground(new Color(255, 128, 64));
        btnViewMessages.addActionListener(e -> {
        	if (parent instanceof PatronDashboard) {
                String patronId = ((PatronDashboard) parent).getPatronId();
                new PatronMessagesUI(this, patronId, reservationManager).setVisible(true);
            }
        });
        
        JButton btnReserve = new JButton("Reserve Book");
        btnReserve.setBackground(new Color(255, 128, 64));
        btnReserve.addActionListener(e -> {
        	BorrowReturnDialog dialog = new BorrowReturnDialog(this, "Reserve Book");
            dialog.setVisible(true);

            if(dialog.isSubmitted()) {
                String patronId = dialog.getPatronId();
                String bookId = dialog.getBookId();

                if (reservationManager.reserveBook(patronId, bookId)) {
                    JOptionPane.showMessageDialog(this, "Book Reserved.");
                    loadReservations();
                } else {
                    JOptionPane.showMessageDialog(this, "Reservation failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        if(parent instanceof PatronDashboard) {
        	footer.add(btnViewMessages);
        	footer.add(btnReserve);
        }
        
        loadReservations();
    }
    
    private void loadReservations() {
    	List<Reservation> history = new ArrayList<>();
        
        for(User u : patrons) {
        	if (u instanceof Patron) {
                Patron p = (Patron) u;
                history.addAll(reservationManager.getReservationsByPatron(p.getUserId()));
            }  
        }
        
        for (Reservation s : history) {
            Book b = bookManager.searchBookById(s.getBookId());
            Patron p = (Patron)userManager.searchUserById(s.getPatronId());
            
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
    }
}