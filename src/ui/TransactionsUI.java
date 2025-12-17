package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import dataModel.Patron;
import dataModel.Transaction;
import dataModel.User;
import dataModel.Book;
import managers.BookManager;
import managers.TransactionManager;
import managers.UserManager;

public class TransactionsUI extends JDialog {

    private List<User> patrons;
    private BookManager bookManager;
    private UserManager userManager;
    private TransactionManager transactionManager;
    

    public TransactionsUI(Frame parent, List<User> list) {
        super(parent, "Borrowing History", true);
        this.patrons = list;
        this.bookManager = new BookManager();
        this.userManager = new  UserManager();
        this.transactionManager = new TransactionManager(userManager, bookManager, null);

        setSize(700, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Header
        JLabel title = new JLabel("Borrowing History", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        // Table
        String[] cols = {"Book ID", "Patron ID", "Patron Name", "Title", "Checkout Date", "Return Date"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        List<Transaction> history = new ArrayList<>();
        
        for(User u : patrons) {
        	if (u instanceof Patron) {
                Patron p = (Patron) u;
                history.addAll(transactionManager.getPatronHistory(p.getUserId()));
            }  
        }
        
        for (Transaction t : history) {
            Book b = bookManager.searchBookById(t.getBookId());
            Patron p = (Patron)userManager.searchUserById(t.getPatronId());
            
            String bookTitle = (b != null) ? b.getTitle() : "Unknown Title";
            String patronName = (p != null) ? p.getName() : "Unknown Title";

            
            String returnDate = (t.getReturnDate() == null) ? "Not Returned Yet" : t.getReturnDate().toString();

            model.addRow(new Object[]{
                t.getBookId(),
                t.getPatronId(),
                patronName,
                bookTitle,
                t.getCheckoutDate(),
                returnDate
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