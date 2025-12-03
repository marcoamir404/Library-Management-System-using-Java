package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
import managers.BookManager;
import dataModel.Book;
import dataModel.Filters;

public class BookSearchUI extends JFrame {


	private static final long serialVersionUID = 1L;
	private JTextField searchField;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private BookManager bookManager;
    private Filters filters = null;
    
    private final Color PRIMARY_COLOR = new Color(44, 62, 80);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219); 
    public BookSearchUI() {
        bookManager = new BookManager();
        
       
        setTitle("Library Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        
        JLabel titleLabel = new JLabel("Library Search System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.NORTH);

       
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        searchPanel.setOpaque(false); 

        JLabel searchLabel = new JLabel("Search Book:");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        searchField = new JTextField(25);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(200, 30)); 

       
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(SECONDARY_COLOR);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setBackground(new Color(39, 174, 96));
        viewDetailsButton.setForeground(Color.WHITE);
        viewDetailsButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        viewDetailsButton.setFocusPainted(false);
        viewDetailsButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        viewDetailsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        JButton refreshButton = new JButton("Refresh All");
        refreshButton.setBackground(new Color(149, 165, 166));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton filtersButton = new JButton("Add Filters");
        filtersButton.setBackground(SECONDARY_COLOR);
        filtersButton.setForeground(Color.WHITE);
        filtersButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        filtersButton.setFocusPainted(false);
        filtersButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        filtersButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchPanel.add(filtersButton);
        
        searchPanel.add(filtersButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(viewDetailsButton);
        searchPanel.add(refreshButton);
       

        headerPanel.add(searchPanel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        
        String[] columnNames = {"ID", "Title", "Author", "Genre", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {

			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        resultTable = new JTable(tableModel);
        resultTable.setFillsViewportHeight(true);
        resultTable.setRowHeight(30); 
        resultTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultTable.setSelectionBackground(new Color(189, 195, 199));
        
        
        JTableHeader tableHeader = resultTable.getTableHeader();
        tableHeader.setBackground(new Color(220, 220, 220));
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // مسافة حول الجدول
        scrollPane.getViewport().setBackground(Color.WHITE); // لون خلفية الجدول البيضاء

        add(scrollPane, BorderLayout.CENTER);

        
        
        filtersButton.addActionListener(e-> {
        	FiltersDialog filtersDialog = new FiltersDialog(this);
        	filtersDialog.setVisible(true);
        	
        	filters = filtersDialog.getResultFilters();
        	performSearch(filters);
        });
        searchButton.addActionListener(e -> performSearch(filters));
        
        viewDetailsButton.addActionListener(e -> openSelectedBookDetails());
        
        refreshButton.addActionListener(e -> loadAllBooks());
        
       
        loadAllBooks();
    }


    private void performSearch(Filters filters) {
        String query = searchField.getText().trim();
		List<Book> results = bookManager.searchBooks(query, filters);
        updateTable(results);
    }
    
    private void loadAllBooks() {
        searchField.setText("");
        List<Book> allBooks = bookManager.getAllBooks();
        updateTable(allBooks);
    }

    private void updateTable(List<Book> books) {
        tableModel.setRowCount(0);
        for (Book b : books) {
            Object[] row = {
                b.getBookId(),
                b.getTitle(),
                b.getAuthor(),
                b.getGenre(),
                b.getStatus()
            };
            tableModel.addRow(row);
        }
    }
    
    private void openSelectedBookDetails() {
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a book first!", "Alert", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String bookId = (String) tableModel.getValueAt(selectedRow, 0);
        Book selectedBook = bookManager.searchBookById(bookId);
        if (selectedBook != null) {
            new BookDetailsUI(this, selectedBook).setVisible(true);
        }
    }
}