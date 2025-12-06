package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import dataModel.Book;
import managers.BookManager;

public class BookFormUI extends JDialog {

    private JTextField txtTitle, txtAuthor, txtGenre, txtYear;
    private JTextArea txtSummary;
    private BookManager bookManager;
    private Book bookToEdit;
    private boolean isSaved = false;

    // الألوان
    private final Color COLOR_PRIMARY = new Color(44, 62, 80);

    public BookFormUI(Window parent, BookManager manager, Book book) {

	    super(parent, book == null ? "Add New Book" : "Edit Book", ModalityType.APPLICATION_MODAL);

	    this.bookManager = manager;
	    this.bookToEdit = book;

	    setSize(450, 500);
	    setLocationRelativeTo(parent);
	    getContentPane().setLayout(new BorderLayout());

	    JLabel header = new JLabel(
	            book == null ? "Add New Book" : "Edit Book",
	            SwingConstants.CENTER);
	    header.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    header.setOpaque(true);
	    header.setBackground(new Color(44, 62, 80));
	    header.setForeground(Color.WHITE);
	    header.setPreferredSize(new Dimension(400, 60));
	    getContentPane().add(header, BorderLayout.NORTH);

	    JPanel form = new JPanel();
	    form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
	    form.setBorder(new EmptyBorder(20, 20, 20, 20));

	   
	    JLabel lblTitle = new JLabel("Title:");
	    txtTitle = new JTextField();
	    form.add(lblTitle);
	    form.add(txtTitle);

	    JLabel lblAuthor = new JLabel("Author:");
	    txtAuthor = new JTextField();
	    form.add(lblAuthor);
	    form.add(txtAuthor);

	    JLabel lblGenre = new JLabel("Genre:");
	    txtGenre = new JTextField();
	    form.add(lblGenre);
	    form.add(txtGenre);

	    JLabel lblYear = new JLabel("Year:");
	    txtYear = new JTextField();
	    form.add(lblYear);
	    form.add(txtYear);

	    JLabel lblSummary = new JLabel("Summary:");
	    txtSummary = new JTextArea(4,20);
	    txtSummary.setLineWrap(true);
	    form.add(lblSummary);
	    form.add(new JScrollPane(txtSummary));

	    getContentPane().add(form, BorderLayout.CENTER);

	    JPanel btns = new JPanel();
	    JButton btnSave = new JButton("Save");
	    JButton btnClose = new JButton("Close");

	    btnSave.addActionListener(e -> saveBook());
	    btnClose.addActionListener(e -> dispose());

	    btns.add(btnSave);
	    btns.add(btnClose);

	    getContentPane().add(btns, BorderLayout.SOUTH);

	    if(bookToEdit != null){
	        fillData();
	    }
	

    }


    private void fillData() {
        txtTitle.setText(bookToEdit.getTitle());
        txtAuthor.setText(bookToEdit.getAuthor());
        txtGenre.setText(bookToEdit.getGenre());
        txtYear.setText(String.valueOf(bookToEdit.getPublicationYear()));
        txtSummary.setText(bookToEdit.getSummary());
    }

    private void saveBook() {
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String genre = txtGenre.getText().trim();
        String yearStr = txtYear.getText().trim();
        String summary = txtSummary.getText().trim();

        if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int year = Integer.parseInt(yearStr);
            
            if (bookToEdit == null) {
                String id = "B" + (Book.books.size()+1);
                Book newBook = new Book(id, title, author, genre, year, summary);
                boolean added = bookManager.addBook(newBook);
                if (!added) {
                    JOptionPane.showMessageDialog(this, "Book ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                bookToEdit.setTitle(title);
                bookToEdit.setAuthor(author);
                bookToEdit.setGenre(genre);
                bookToEdit.setPublicationYear(year);
                bookToEdit.setSummary(summary);
                bookManager.updateBook(bookToEdit);
            }
            
            isSaved = true;
            JOptionPane.showMessageDialog(this, "Book Saved Successfully!");
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Year must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isSaved() {
        return isSaved;
    }
}