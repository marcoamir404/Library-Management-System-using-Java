package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataModel.Book;
import managers.BookManager;

public class BookManagementUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private BookManager bookManager;

	
	public BookManagementUI() {		
	    super("Library System");
		this.bookManager = new BookManager();
	    setSize(1200, 700);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(new BorderLayout());
	    

	    JPanel LabelForTitle = new JPanel();
	    LabelForTitle.setBackground(new Color(44, 62, 80));

	    JLabel Title = new JLabel("Library System");
	    Title.setFont(new Font("Segoe UI", Font.BOLD, 36));
	    Title.setForeground(Color.WHITE);
	    LabelForTitle.add(Title);

	    add(LabelForTitle, BorderLayout.NORTH);

	    String[] columns = {"ID","Title","Author","Genre","Status","Year"};
	    model = new javax.swing.table.DefaultTableModel(columns, 0);
	    JTable BookTable = new JTable(model);
	    BookTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    BookTable.setRowHeight(24);

	    JScrollPane scrollPane = new JScrollPane(BookTable);
	    add(scrollPane, BorderLayout.CENTER);

	    JPanel buttonsPanel = new JPanel(new FlowLayout());

	    JButton AddBtn = new JButton("Add");
	    AddBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    AddBtn.setBackground(new Color(44,62,80));
	    AddBtn.setForeground(Color.WHITE);

	    JButton EditBtn = new JButton("Edit");
	    EditBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    EditBtn.setBackground(new Color(255,190,0));
	    EditBtn.setForeground(Color.WHITE);

	    JButton RemoveBtn = new JButton("Remove");
	    RemoveBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    RemoveBtn.setBackground(Color.RED);
	    RemoveBtn.setForeground(Color.WHITE);
	    
	    JButton SearchBtn = new JButton("Search");
	    SearchBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    SearchBtn.setBackground(new Color(44,62,80));
	    SearchBtn.setForeground(Color.WHITE);
	    
	    JButton Details = new JButton("Details");
	    Details.setFont(new Font("Segoe UI", Font.BOLD, 20));

	    JButton Close = new JButton("Close");
	    Close.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    Close.setBackground(Color.BLACK);
	    Close.setForeground(Color.WHITE);

	    buttonsPanel.add(AddBtn);
	    buttonsPanel.add(EditBtn);
	    buttonsPanel.add(RemoveBtn);
	    buttonsPanel.add(SearchBtn);
	    buttonsPanel.add(Details);
	    buttonsPanel.add(Close);

	    add(buttonsPanel, BorderLayout.SOUTH);
	    
	    AddBtn.addActionListener(e ->{
	    	BookFormUI form = new BookFormUI(this, bookManager, null);

    	    form.addWindowListener(new java.awt.event.WindowAdapter() {
    	        @Override
    	        public void windowClosed(java.awt.event.WindowEvent e) {
    	            loadAllBooks();
    	        }
    	    });

    	    form.setVisible(true);
	    });
	    
	    
	    EditBtn.addActionListener(e ->{
	    	int row = BookTable.getSelectedRow();
        	if(row == -1) return;
        	
        	String id = (String) BookTable.getValueAt(row, 0);
        	Book target = null;
        	
        	for (Book b : Book.books) {
        	    if (b.getBookId().equals(id)) {
        	        target = b;   
        	        break;
        	    }
        	}

        	if (target != null) {
		    	BookFormUI form = new BookFormUI(this, bookManager, target);
	
	    	    form.addWindowListener(new java.awt.event.WindowAdapter() {
	    	        @Override
	    	        public void windowClosed(java.awt.event.WindowEvent e) {
	    	            loadAllBooks();
	    	        }
	    	    });
	
	    	    form.setVisible(true);
    	    }
	    });
	    
	    
	    RemoveBtn.addActionListener(e ->{
	    	int selectedRow = BookTable.getSelectedRow();
	    	if (selectedRow == -1) {
	    	    JOptionPane.showMessageDialog(this, "Please select a book first!");
	    	    return;
	    	}
	    	
	    	String bookId = (String) BookTable.getValueAt(selectedRow, 0);
	    	int confirm = JOptionPane.showConfirmDialog(this, "Delete book " + bookId + "?");
            if(confirm == JOptionPane.YES_OPTION) {
	    	    bookManager.removeBook(bookId);
                loadAllBooks();
            }
	    });
	    
	    SearchBtn.addActionListener(e ->{
	    	new BookSearchUI().setVisible(true);
	    });
	    
	    Details.addActionListener(e ->{
	    	int row = BookTable.getSelectedRow();
        	if(row == -1) return;
        	
        	String id = (String) BookTable.getValueAt(row, 0);
        	Book target = null;
        	
        	for (Book b : Book.books) {
        	    if (b.getBookId().equals(id)) {
        	        target = b;   
        	        break;
        	    }
        	}

        	if (target != null) {
        		new BookDetailsUI(this, target).setVisible(true);
        	}
	    });
	    
	    
	    Close.addActionListener(e -> this.dispose());

	    loadAllBooks();
	}
	
	 private void loadAllBooks() {
	        List<Book> allBooks = bookManager.getAllBooks();
	        updateTable(allBooks);
	    }
	private void updateTable(List<Book> books) {
		model.setRowCount(0); 
	    for (Book b : books) {
	        Object[] row = {
	            b.getBookId(),
	            b.getTitle(),
	            b.getAuthor(),
	            b.getGenre(),
	            b.getStatus(),
	            b.getPublicationYear()
	            
	        };
	        model.addRow(row);
	    }
	}

}
