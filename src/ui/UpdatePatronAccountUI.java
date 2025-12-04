package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import dataModel.Patron;
import managers.UserManager;

public class UpdatePatronAccountUI extends JDialog {


	private static final long serialVersionUID = 1L;
	private JTextField txtName, txtEmail, txtPhone, txtUser;
    private JPasswordField txtPass;
    private Patron patron;
    private UserManager userManager;

    public UpdatePatronAccountUI(Frame parent, Patron patron, UserManager userManager) {
        super(parent, "Update Profile", true);
        this.patron = patron;
        this.patron.setUserMan(userManager);
        
        setSize(400, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Edit Your Profile", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(6, 1, 10, 10));
        form.setBorder(new EmptyBorder(20, 40, 20, 40));

        form.add(new JLabel("Full Name:"));
        txtName = new JTextField(patron.getName());
        form.add(txtName);

        form.add(new JLabel("Email:"));
        txtEmail = new JTextField(patron.getEmail());
        form.add(txtEmail);

        form.add(new JLabel("Phone:"));
        txtPhone = new JTextField(patron.getPhone());
        form.add(txtPhone);

        form.add(new JLabel("Username (Cannot be changed):"));
        txtUser = new JTextField(patron.getUsername());
        txtUser.setEditable(false);
        txtUser.setBackground(new Color(230, 230, 230));
        form.add(txtUser);

        form.add(new JLabel("New Password:"));
        txtPass = new JPasswordField(patron.getPassword());
        form.add(txtPass);

        add(form, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnSave = new JButton("Save Changes");
        JButton btnCancel = new JButton("Cancel");

        btnSave.setBackground(new Color(39, 174, 96));
        btnSave.setForeground(Color.WHITE);

        btnSave.addActionListener(e -> saveChanges());
        btnCancel.addActionListener(e -> this.dispose());

        btnPanel.add(btnSave);
        btnPanel.add(btnCancel);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void saveChanges() {
        String newName = txtName.getText().trim();
        String newEmail = txtEmail.getText().trim();
        String newPhone = txtPhone.getText().trim();
        String newPass = new String(txtPass.getPassword()).trim();

        if (newName.isEmpty() || newEmail.isEmpty() || newPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        patron.updateName(newName);
        patron.updateEmail(newEmail);
        patron.updatePhone(newPhone);
        patron.updatePassword(newPass);

        JOptionPane.showMessageDialog(this, "Profile Updated Successfully!");
        dispose();
    }
}