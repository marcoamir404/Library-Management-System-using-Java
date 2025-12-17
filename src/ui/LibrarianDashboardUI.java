package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import managers.BookManager;
import managers.ReservationManager;
import managers.TransactionManager;
import managers.UserManager;


public class LibrarianDashboardUI extends JFrame {

	UserManager userManager = new UserManager();
	BookManager bookManager = new BookManager();
	ReservationManager reservationManager = new ReservationManager();

	TransactionManager transactionManager =
	        new TransactionManager(userManager, bookManager, reservationManager);

	private static final long serialVersionUID = 1L;
	private String thisLibrarianId;
	private final Color COLOR_PRIMARY = new Color(52, 73, 94); 

    public LibrarianDashboardUI(String Id) {
    	
    	UserManager userManager = new UserManager();
    	this.thisLibrarianId = Id;
    	
        setTitle("Librarian Workstation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_PRIMARY);
        header.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel lblTitle = new JLabel("Librarian Dashboard");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle, BorderLayout.WEST);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFocusPainted(false);
        btnLogout.setBackground(new Color(231, 76, 60));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.addActionListener(e -> {
            new LoginUI().setVisible(true);
            dispose();
        });
        header.add(btnLogout, BorderLayout.EAST);

        getContentPane().add(header, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        
        getContentPane().add(panel, BorderLayout.CENTER);

        JButton btnCheckout = new JButton("Checkout Book ");
        btnCheckout.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnCheckout.setBackground(new Color(39, 174, 96));
        btnCheckout.setForeground(Color.WHITE);
        btnCheckout.setPreferredSize(new Dimension(300, 80));
        GridBagConstraints gbc_btnCheckout = new GridBagConstraints();
        gbc_btnCheckout.insets = new Insets(0, 0, 5, 5);
        gbc_btnCheckout.gridx = 0;
        gbc_btnCheckout.gridy = 0;
        panel.add(btnCheckout, gbc_btnCheckout);
        
        
        JButton btnReturn = new JButton("Return Book");
        btnReturn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnReturn.setBackground(new Color(255, 128, 0));
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setPreferredSize(new Dimension(300, 80));
        GridBagConstraints gbc_btnReturn = new GridBagConstraints();
        gbc_btnReturn.insets = new Insets(0, 0, 5, 0);
        gbc_btnReturn.gridx = 1;
        gbc_btnReturn.gridy = 0;
        panel.add(btnReturn, gbc_btnReturn);
          

        JButton btnSearchBook = new JButton("Search Book ");
        btnSearchBook.setPreferredSize(new Dimension(300, 80));
        btnSearchBook.setForeground(Color.WHITE);
        btnSearchBook.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnSearchBook.setBackground(new Color(39, 174, 96));
        GridBagConstraints gbc_btnSearshBook = new GridBagConstraints();
        gbc_btnSearshBook.insets = new Insets(0, 0, 5, 5);
        gbc_btnSearshBook.gridx = 0;
        gbc_btnSearshBook.gridy = 1;
        panel.add(btnSearchBook, gbc_btnSearshBook);
        
         
	     JButton btnSearchUser = new JButton("Search User");
	     btnSearchUser.setPreferredSize(new Dimension(300, 80));
	     btnSearchUser.setForeground(Color.WHITE);
	     btnSearchUser.setFont(new Font("Segoe UI", Font.BOLD, 18));
	     btnSearchUser.setBackground(new Color(255, 128, 0));
	     GridBagConstraints gbc_btnSearshUser = new GridBagConstraints();
	     gbc_btnSearshUser.insets = new Insets(0, 0, 5, 0);
	     gbc_btnSearshUser.gridx = 1;
	     gbc_btnSearshUser.gridy = 1;
	     panel.add(btnSearchUser, gbc_btnSearshUser);
       
       
       JButton btnRenewBook = new JButton("Renew Book");
       btnRenewBook.setPreferredSize(new Dimension(300, 80));
       btnRenewBook.setForeground(Color.WHITE);
       btnRenewBook.setFont(new Font("Segoe UI", Font.BOLD, 18));
       btnRenewBook.setBackground(new Color(39, 174, 96));
       GridBagConstraints gbc_btnRenewBook = new GridBagConstraints();
       gbc_btnRenewBook.insets = new Insets(0, 0, 5, 5);
       gbc_btnRenewBook.gridx = 0;
       gbc_btnRenewBook.gridy = 2;
       panel.add(btnRenewBook, gbc_btnRenewBook);
       
       
       
       JButton btnReserveBook = new JButton("Reserve Book");
       btnReserveBook.setPreferredSize(new Dimension(300, 80));
       btnReserveBook.setForeground(Color.WHITE);
       btnReserveBook.setFont(new Font("Segoe UI", Font.BOLD, 18));
       btnReserveBook.setBackground(new Color(255, 128, 0));
       GridBagConstraints gbc_btnReserveBook = new GridBagConstraints();
       gbc_btnReserveBook.insets = new Insets(0, 0, 5, 0);
       gbc_btnReserveBook.gridx = 1;
       gbc_btnReserveBook.gridy = 2;
       panel.add(btnReserveBook, gbc_btnReserveBook);
       
       
       JButton btnViewTransactions = new JButton("View Transactions");
       btnViewTransactions.setPreferredSize(new Dimension(300, 80));
       btnViewTransactions.setForeground(Color.WHITE);
       btnViewTransactions.setFont(new Font("Segoe UI", Font.BOLD, 18));
       btnViewTransactions.setBackground(new Color(39, 174, 96));
       GridBagConstraints gbc_btnViewTransactions = new GridBagConstraints();
       gbc_btnViewTransactions.insets = new Insets(0, 0, 5, 5);
       gbc_btnViewTransactions.gridx = 0;
       gbc_btnViewTransactions.gridy = 3;
       panel.add(btnViewTransactions, gbc_btnViewTransactions);
       
           
       JButton btnViewReservatoins = new JButton("View Reservatoins");
       btnViewReservatoins.setPreferredSize(new Dimension(300, 80));
       btnViewReservatoins.setForeground(Color.WHITE);
       btnViewReservatoins.setFont(new Font("Segoe UI", Font.BOLD, 18));
       btnViewReservatoins.setBackground(new Color(255, 128, 0));
       GridBagConstraints gbc_btnViewReservatoins = new GridBagConstraints();
       gbc_btnViewReservatoins.insets = new Insets(0, 0, 5, 0);
       gbc_btnViewReservatoins.gridx = 1;
       gbc_btnViewReservatoins.gridy = 3;
       panel.add(btnViewReservatoins, gbc_btnViewReservatoins);
       
       JButton editMyAcc = new JButton("Edit Your Account");
       editMyAcc.setPreferredSize(new Dimension(300, 80));
       editMyAcc.setForeground(Color.WHITE);
       editMyAcc.setFont(new Font("Segoe UI", Font.BOLD, 18));
       editMyAcc.setBackground(new Color(39, 174, 96));
       GridBagConstraints gbc_editMyAcc = new GridBagConstraints();
       gbc_editMyAcc.insets = new Insets(0, 0, 0, 5);
       gbc_editMyAcc.gridx = 0;
       gbc_editMyAcc.gridy = 4;
       panel.add(editMyAcc, gbc_editMyAcc);
       
       
       btnRenewBook.addActionListener(e ->{
          	BorrowReturnDialog dialog = new BorrowReturnDialog(this, "Renew Book","");
              dialog.setVisible(true);

              if(dialog.isSubmitted()) {
                  String patronId = dialog.getPatronId();
                  String bookId = dialog.getBookId();
                  if (transactionManager.renewBook(patronId, bookId)) {
                      JOptionPane.showMessageDialog(this, "Book Renewed.");
                  } else {
                      JOptionPane.showMessageDialog(this, "Renewing failed!", "Error", JOptionPane.ERROR_MESSAGE);
                  }
              }
           });
       
       editMyAcc.addActionListener(e ->{
    	   new UserFormUI(this, userManager, userManager.searchUserById(thisLibrarianId), true).setVisible(true);
       });
       
       btnViewReservatoins.addActionListener(e ->{
       	new ReservationsUI(this, userManager.searchUsers("patron")).setVisible(true);
       });
       
       
        btnCheckout.addActionListener(e -> {
        	BorrowReturnDialog dialog = new BorrowReturnDialog(this, "Checkout Book","");
            dialog.setVisible(true);

            if(dialog.isSubmitted()) {
                String patronId = dialog.getPatronId();
                String bookId = dialog.getBookId();

                if (transactionManager.checkoutBook(patronId, bookId)) {
                    JOptionPane.showMessageDialog(this, "Book checked out.");
                } else {
                    JOptionPane.showMessageDialog(this, "Checkingout failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnReturn.addActionListener(e -> {
        	BorrowReturnDialog dialog = new BorrowReturnDialog(this, "Return Book","");
            dialog.setVisible(true);

            if(dialog.isSubmitted()) {
                String patronId = dialog.getPatronId();
                String bookId = dialog.getBookId();

                if (transactionManager.returnBook(patronId, bookId)) {
                    JOptionPane.showMessageDialog(this, "Book Reterned.");
                } else {
                    JOptionPane.showMessageDialog(this, "Returning failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnSearchBook.addActionListener(e ->{
        	new BookSearchUI().setVisible(true);
        });
        
        btnSearchUser.addActionListener(e ->{
        	new UserSearchUI(this).setVisible(true);
        });
        
        btnReserveBook.addActionListener(e ->{
        	BorrowReturnDialog dialog = new BorrowReturnDialog(this, "Reserve Book","");
            dialog.setVisible(true);

            if(dialog.isSubmitted()) {
                String patronId = dialog.getPatronId();
                String bookId = dialog.getBookId();

                if (reservationManager.reserveBook(patronId, bookId)) {
                    JOptionPane.showMessageDialog(this, "Book Reserved.");
                } else {
                    JOptionPane.showMessageDialog(this, "Reservation failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnViewTransactions.addActionListener(e ->{
        	new TransactionsUI(this, userManager.searchUsers("patron")).setVisible(true);
        });
    }


}

