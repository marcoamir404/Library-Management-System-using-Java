package ui;

import enums.UserType;
import managers.UserManager;

import javax.swing.*;
import java.awt.*;

public class PatronDashboard extends JFrame {

    private static final long serialVersionUID = 1L;

    private UserManager userManager;
    private String thisPatronID;

    public PatronDashboard(UserManager userManager ,String id) {
        
    	this.userManager = userManager;
    	this.thisPatronID = id; 
    	
    	setSize(700, 500); 
    	setLocationRelativeTo(null);
        setTitle("Patron Dashboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(44, 62, 80));
        header.setPreferredSize(new Dimension(700, 60));

        JLabel title = new JLabel("Library Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(new Color(236, 240, 241));

        JLabel welcome = new JLabel("Welcome to Your Library!", SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcome.setForeground(new Color(44, 62, 80));
        welcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        center.add(welcome, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 2, 30, 15));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 50, 100));
        buttonsPanel.setBackground(new Color(236, 240, 241));

        JButton btnSearchBooks = new JButton("Search Books");
        btnSearchBooks.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnSearchBooks.setBackground(new Color(46, 134, 193));
        btnSearchBooks.setForeground(Color.WHITE);
        btnSearchBooks.setFocusPainted(false);
        btnSearchBooks.setPreferredSize(new Dimension(200, 60));
        
        JButton btnBorrowHistory = new JButton("Borrow History");
        btnBorrowHistory.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnBorrowHistory.setBackground(new Color(46, 134, 193));
        btnBorrowHistory.setForeground(Color.WHITE);
        btnBorrowHistory.setFocusPainted(false);
        btnBorrowHistory.setPreferredSize(new Dimension(200, 60));
        
        JButton btnCurrentLoans = new JButton("Current Loans");
        btnCurrentLoans.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnCurrentLoans.setBackground(new Color(46, 134, 193));
        btnCurrentLoans.setForeground(Color.WHITE);
        btnCurrentLoans.setFocusPainted(false);
        btnCurrentLoans.setPreferredSize(new Dimension(200, 60));
        
        JButton btnReserveBook = new JButton("Reserve Book");
        btnReserveBook.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnReserveBook.setBackground(new Color(46, 134, 193));
        btnReserveBook.setForeground(Color.WHITE);
        btnReserveBook.setFocusPainted(false);
        btnReserveBook.setPreferredSize(new Dimension(200, 60));
        
        JButton btnUpdateAccount = new JButton("Update Account");
        btnUpdateAccount.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnUpdateAccount.setBackground(new Color(46, 134, 193));
        btnUpdateAccount.setForeground(Color.WHITE);
        btnUpdateAccount.setFocusPainted(false);
        btnUpdateAccount.setPreferredSize(new Dimension(200, 60));
        
        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLogOut.setBackground(new Color(231, 76, 60));
        btnLogOut.setForeground(Color.WHITE);
        btnLogOut.setFocusPainted(false);
        btnLogOut.setPreferredSize(new Dimension(200, 60));

        
        buttonsPanel.add(btnSearchBooks);
        buttonsPanel.add(btnBorrowHistory);
        buttonsPanel.add(btnCurrentLoans);
        buttonsPanel.add(btnReserveBook);
        buttonsPanel.add(btnUpdateAccount);
        buttonsPanel.add(btnLogOut);

        center.add(buttonsPanel, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);


        btnSearchBooks.addActionListener(evt -> {
            try {
                new BookSearchUI().setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Cannot open Search Books page: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnBorrowHistory.addActionListener(evt -> {
            try {
                	new TransactionsUI(this,userManager.searchUsers(id)).setVisible(true); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Cannot open Borrow History page: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCurrentLoans.addActionListener(evt -> {
            try {
                new PatronCurrentLoans(id).setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Cannot open Current Loans page: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUpdateAccount.addActionListener(evt -> {
            try {
                new UserFormUI(this, userManager, userManager.searchUserById(id), true).setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Cannot open Update Account page: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnReserveBook.addActionListener(evt -> {
            try {
                ReservationsUI reservationUI = new ReservationsUI(this, userManager.searchUsers(id));
                reservationUI.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Cannot open Reservations page: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLogOut.addActionListener(evt -> {
            try {
                LoginUI loginUI = new LoginUI();
                loginUI.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Cannot open Login page: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

	public String getPatronId() {
		return thisPatronID;
	}
}
