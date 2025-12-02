package ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataModel.Filters;
import enums.BookStatus;

public class FiltersDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private Filters result = null;

    private JTextField authorField;
    private JTextField genreField;
    private JTextField yearFromField;
    private JTextField yearToField;
    private JComboBox<BookStatus> statusBox;

    public FiltersDialog(JFrame parent) {
        super(parent, true); 

        setTitle("Filters");
        setSize(350, 320);
        setLocationRelativeTo(parent);
        setLayout(null);
        


        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(20, 20, 100, 25);
        add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(120, 20, 180, 25);
        add(authorField);


        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setBounds(20, 60, 100, 25);
        add(genreLabel);

        genreField = new JTextField();
        genreField.setBounds(120, 60, 180, 25);
        add(genreField);


        JLabel yearFromLabel = new JLabel("Year From:");
        yearFromLabel.setBounds(20, 100, 100, 25);
        add(yearFromLabel);

        yearFromField = new JTextField();
        yearFromField.setBounds(120, 100, 180, 25);
        add(yearFromField);


        JLabel yearToLabel = new JLabel("Year To:");
        yearToLabel.setBounds(20, 140, 100, 25);
        add(yearToLabel);

        yearToField = new JTextField();
        yearToField.setBounds(120, 140, 180, 25);
        add(yearToField);


        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(20, 180, 100, 25);
        add(statusLabel);

        statusBox = new JComboBox<>(BookStatus.values());
        statusBox.setBounds(120, 180, 180, 25);
        add(statusBox);



        JButton ok = new JButton("Apply Filters");
        ok.setBackground(new Color(39, 174, 96));
        ok.setForeground(Color.WHITE);
        ok.setBounds(20, 230, 150, 30);
        add(ok);

        ok.addActionListener(e -> {
            result = new Filters();

            result.author = authorField.getText();
            result.genre = genreField.getText();

            result.yearFrom = yearFromField.getText().isEmpty() ? -1 :
                              Integer.parseInt(yearFromField.getText());

            result.yearTo = yearToField.getText().isEmpty() ? -1 :
                            Integer.parseInt(yearToField.getText());

            result.status = (BookStatus) statusBox.getSelectedItem();

            dispose();
        });
    }

    public Filters getResultFilters() {
        return result;
    }
}


