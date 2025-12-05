package ui;

import javax.swing.*;
import java.awt.*;

public class BorrowReturnDialog extends JDialog {

    private JTextField txtPatronId, txtBookId;
    private boolean submitted = false;

    public BorrowReturnDialog(JFrame parent, String title) {
        super(parent, title, true);
        setSize(391, 262);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new GridLayout(6, 1, 5, 5));

        getContentPane().add(new JLabel("Patron ID:", SwingConstants.CENTER));
        txtPatronId = new JTextField();
        getContentPane().add(txtPatronId);

        getContentPane().add(new JLabel("Book ID:", SwingConstants.CENTER));
        txtBookId = new JTextField();
        getContentPane().add(txtBookId);

        JButton btnSubmit = new JButton("Confirm");
        JButton btnCancel = new JButton("Cancel");

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnSubmit);
        btnPanel.add(btnCancel);
        getContentPane().add(btnPanel);

        btnSubmit.addActionListener(e -> {
            submitted = true;
            dispose();
        });

        btnCancel.addActionListener(e -> dispose());
    }

    public boolean isSubmitted() { return submitted; }
    public String getPatronId() { return txtPatronId.getText().trim(); }
    public String getBookId() { return txtBookId.getText().trim(); }
}
