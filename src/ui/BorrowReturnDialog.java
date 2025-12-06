package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class BorrowReturnDialog extends JDialog {


	private static final long serialVersionUID = 1L;
	private JTextField txtPatronId, txtBookId;
    private boolean submitted = false;

    private final Color COLOR_PRIMARY = new Color(39, 174, 96); 
    private final Color COLOR_BG = new Color(245, 247, 250);

    public BorrowReturnDialog(JFrame parent, String title) {
    	super(parent, title,true);

        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_BG);

        JPanel header = new JPanel();
        header.setBackground(COLOR_PRIMARY);
        header.setBorder(new EmptyBorder(15, 0, 15, 0));
        JLabel title1 = new JLabel(title);
        title1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title1.setForeground(Color.WHITE);
        header.add(title1);
        add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(4, 1, 10, 10));
        form.setBorder(new EmptyBorder(20, 40, 20, 40));
        form.setBackground(COLOR_BG);
       
        JLabel patronID = new JLabel("Patron ID:");
        patronID.setFont(new Font("Segoe UI", Font.BOLD, 13));
        patronID.setForeground(Color.DARK_GRAY);
        form.add(patronID);
        
        txtPatronId = new JTextField();
        txtPatronId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        form.add(txtPatronId);

        JLabel bookId = new JLabel("Book ID:");
        bookId.setFont(new Font("Segoe UI", Font.BOLD, 13));
        bookId.setForeground(Color.DARK_GRAY);
        form.add(bookId);
        
        txtBookId = new JTextField();
        txtBookId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        form.add(txtBookId);

        add(form, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        btnPanel.setBackground(COLOR_BG);

        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.setBackground(COLOR_PRIMARY);
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnConfirm.setFocusPainted(false);
        btnConfirm.setPreferredSize(new Dimension(140, 35));
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCancel.setFocusPainted(false);
        btnCancel.setPreferredSize(new Dimension(140, 35));

        btnConfirm.addActionListener(e -> {
            submitted = true;
            dispose();
        });
        btnCancel.addActionListener(e -> dispose());

        btnPanel.add(btnConfirm);
        btnPanel.add(btnCancel);
        add(btnPanel, BorderLayout.SOUTH);

       

    }

    public boolean isSubmitted() { return submitted; }
    public String getPatronId() { return txtPatronId.getText().trim(); }
    public String getBookId() { return txtBookId.getText().trim(); }
}
