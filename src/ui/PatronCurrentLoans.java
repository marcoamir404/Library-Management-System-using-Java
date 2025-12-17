package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dataModel.Book;
import dataModel.Patron;
import dataModel.Transaction;
import managers.BookManager;
import managers.ReservationManager;
import managers.TransactionManager;
import managers.UserManager;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.List;

public class PatronCurrentLoans extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable table;
    private String patronId;
    private TransactionManager transactionManager;
    private UserManager userManager;
    private BookManager bookManager;
    private ReservationManager reservationManager;
    private DefaultTableModel model;

	public PatronCurrentLoans(String patronId) {
    	this.userManager = new UserManager();
    	this.bookManager = new BookManager();
    	this.reservationManager = new ReservationManager();
        this.transactionManager = new TransactionManager(userManager, bookManager, reservationManager);

        this.patronId = patronId;
        
        setTitle("Current Loans");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(44, 62, 80));
        header.setPreferredSize(new Dimension(700, 60));

        JLabel title = new JLabel("Your Borrowed Books - Track & Renew", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(15, 15));
        center.setBackground(new Color(236, 240, 241));
        center.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel lbl = new JLabel("My Borrowed Books", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbl.setForeground(new Color(44, 62, 80));
        center.add(lbl, BorderLayout.NORTH);

        String[] columns = {
                "Book ID", "Title", "Checkout Date", "Due Date", "Days Left"
        };

         model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // table is read-only
            }
        };

        this.table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setSelectionBackground(new Color(46, 134, 193));
        table.setSelectionForeground(Color.WHITE);

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(44, 62, 80));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        center.add(scrollPane, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new BorderLayout());
        buttons.setBackground(center.getBackground());

        JButton btnRenewBook = new JButton("Renew Book");
        btnRenewBook.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnRenewBook.setBackground(new Color(46, 204, 113));
        btnRenewBook.setForeground(Color.WHITE);
        btnRenewBook.setFocusPainted(false);
        btnRenewBook.setPreferredSize(new Dimension(170, 40));

        JButton btnBack = new JButton("Back to Dashboard");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnBack.setBackground(new Color(46, 134, 193));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setPreferredSize(new Dimension(180, 40));

        buttons.add(btnRenewBook, BorderLayout.WEST);
        buttons.add(btnBack, BorderLayout.EAST);

        center.add(buttons, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);


        btnBack.addActionListener(e -> {
            this.dispose();
        });

        btnRenewBook.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this,
                        "Please select a book first!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                String bookId = table.getValueAt(selectedRow, 0).toString();
                
				if (transactionManager.renewBook(patronId, bookId)) {
                    JOptionPane.showMessageDialog(this, "Book Renewed.");
                } else {
                    JOptionPane.showMessageDialog(this, "Renewing failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            loadLoans();
        });

        
        loadLoans();
        
    }
	
	private void loadLoans() {
		model.setRowCount(0);
		List<Transaction> currentLoans = transactionManager.getActiveLoans(patronId);
    	
   	 	for (Transaction t : currentLoans) {
            Book b = bookManager.searchBookById(t.getBookId());
            Patron p = (Patron)userManager.searchUserById(t.getPatronId());
            
            String bookTitle = (b != null) ? b.getTitle() : "Unknown Title";

            model.addRow(new Object[]{
                t.getBookId(),
                bookTitle,
                t.getCheckoutDate(),
                t.getDueDate(),
                ChronoUnit.DAYS.between(LocalDate.now(), t.getDueDate())
                });}
	}

}
