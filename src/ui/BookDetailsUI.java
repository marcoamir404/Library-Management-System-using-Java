package ui;

import javax.swing.*;
import java.awt.*;
import dataModel.Book;

public class BookDetailsUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private final Color PRIMARY_COLOR = new Color(44, 62, 80);

    public BookDetailsUI(JFrame parent, Book book) {
        super(parent, "Book Details", true);
        setSize(450, 550);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // عنوان علوي
        JPanel header = new JPanel();
        header.setBackground(PRIMARY_COLOR);
        JLabel title = new JLabel(book.getTitle());
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // المحتوى
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        addRow(content, gbc, 0, "Book ID:", book.getBookId());
        addRow(content, gbc, 1, "Author:", book.getAuthor());
        addRow(content, gbc, 2, "Genre:", book.getGenre());
        addRow(content, gbc, 3, "Year:", String.valueOf(book.getPublicationYear()));
        
        // تمييز الحالة بلون مختلف
        JLabel statusLabel = new JLabel(book.getStatus().toString());
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        statusLabel.setForeground(book.getStatus().toString().equals("AVAILABLE") ? new Color(39, 174, 96) : Color.RED);
        
        gbc.gridx = 0; gbc.gridy = 4;
        content.add(createBoldLabel("Status:"), gbc);
        gbc.gridx = 1;
        content.add(statusLabel, gbc);

        // الملخص
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        content.add(createBoldLabel("Summary:"), gbc);
        
        JTextArea summaryArea = new JTextArea(book.getSummary());
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);
        summaryArea.setEditable(false);
        summaryArea.setBackground(new Color(245, 245, 245));
        summaryArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        summaryArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0; // تمدد
        content.add(new JScrollPane(summaryArea), gbc);

        add(content, BorderLayout.CENTER);

        // زر إغلاق
        JButton closeBtn = new JButton("Close");
        closeBtn.setBackground(new Color(231, 76, 60)); // أحمر
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(e -> dispose());
        
        JPanel footer = new JPanel();
        footer.add(closeBtn);
        add(footer, BorderLayout.SOUTH);
    }

    private void addRow(JPanel p, GridBagConstraints gbc, int row, String label, String value) {
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        
        gbc.gridx = 0;
        p.add(createBoldLabel(label), gbc);
        
        gbc.gridx = 1;
        JLabel valLabel = new JLabel(value);
        valLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        p.add(valLabel, gbc);
    }
    
    private JLabel createBoldLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.BOLD, 13));
        l.setForeground(Color.DARK_GRAY);
        return l;
    }
}