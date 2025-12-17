package ui;

import managers.ReservationManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PatronMessagesUI extends JDialog {

    private static final long serialVersionUID = 1L;

    public PatronMessagesUI(Window parent, String patronId,String bookName, boolean notified) {
        super(parent, "My Notifications", ModalityType.APPLICATION_MODAL);

        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(new Color(44, 62, 80));
        header.setPreferredSize(new Dimension(500, 50));

        JLabel title = new JLabel("My Notifications", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        header.add(title);
        add(header, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);


        if (!notified) {
            textArea.setText("No notifications yet.");
        } else {
            String message = "Your reserved book "+bookName+ " is available now";
            textArea.setText(message);
        }

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());

        JPanel footer = new JPanel();
        footer.add(btnClose);
        add(footer, BorderLayout.SOUTH);
    }
}
