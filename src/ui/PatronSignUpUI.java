package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import dataModel.Patron;
import managers.UserManager;

public class PatronSignUpUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtName, txtEmail, txtPhone, txtUsername;
    private JPasswordField txtPassword;
    private UserManager userManager;
    private JFrame parentLoginFrame; // عشان نرجع لشاشة الدخول

    private final Color COLOR_PRIMARY = new Color(44, 62, 80);
    private final Color COLOR_SUCCESS = new Color(39, 174, 96);

    public PatronSignUpUI(JFrame parent) {
    	setResizable(false);
        this.parentLoginFrame = parent;
        this.userManager = new UserManager();

        setTitle("New Patron Registration");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // قفل دي بس مش البرنامج كله
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Header
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(COLOR_PRIMARY);
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        getContentPane().add(title, BorderLayout.NORTH);

        // Form
        JPanel form = new JPanel(new GridLayout(10, 1, 10, 10)); // 10 صفوف
        form.setBorder(new EmptyBorder(0, 40, 20, 40));
        form.setBackground(Color.WHITE);

        txtName = addField(form, "Full Name:");
        txtEmail = addField(form, "Email:");
        txtPhone = addField(form, "Phone Number:");
        txtUsername = addField(form, "Username:");
        
        form.add(createLabel("Password:"));
        txtPassword = new JPasswordField();
        styleField(txtPassword);
        form.add(txtPassword);

        getContentPane().add(form, BorderLayout.CENTER);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        btnPanel.setBackground(Color.WHITE);

        JButton btnRegister = new ModernButton("Register", COLOR_SUCCESS);
        JButton btnCancel = new ModernButton("Cancel", Color.GRAY);

        btnRegister.setPreferredSize(new Dimension(150, 40));
        btnCancel.setPreferredSize(new Dimension(100, 40));

        // Action: Register
        btnRegister.addActionListener(e -> registerPatron());

        // Action: Cancel
        btnCancel.addActionListener(e -> {
            this.dispose();
            parentLoginFrame.setVisible(true); // الرجوع للدخول
        });

        btnPanel.add(btnRegister);
        btnPanel.add(btnCancel);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
        
        // عند إغلاق النافذة من الـ X، نرجع لشاشة الدخول برضه
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                parentLoginFrame.setVisible(true);
            }
        });
    }

    private void registerPatron() {
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String user = txtUsername.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        // 1. Validation
        if (name.isEmpty() || email.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Check Username
        if (userManager.usernameExists(user)) {
            JOptionPane.showMessageDialog(this, "Username already taken. Please choose another.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Generate Simple ID (P + Timestamp) عشان نضمن انه مميز
        String newId = "P" + (System.currentTimeMillis() % 100000);

        // 4. Create Patron
        Patron newPatron = new Patron(newId, user, pass, name, email, phone);

        // 5. Add to Manager
        userManager.addUser(newPatron);

        // 6. Success
        JOptionPane.showMessageDialog(this, "Account created successfully! Please Login.", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        parentLoginFrame.setVisible(true);
    }

    // Helpers
    private JTextField addField(JPanel p, String labelText) {
        p.add(createLabel(labelText));
        JTextField field = new JTextField();
        styleField(field);
        p.add(field);
        return field;
    }

    private JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(Color.DARK_GRAY);
        return l;
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }
    
    // نفس زرار المودرن
    class ModernButton extends JButton {

		private static final long serialVersionUID = 1L;
		public ModernButton(String text, Color baseColor) {
            super(text);
            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setForeground(Color.WHITE);
            setBackground(baseColor);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { setBackground(baseColor.brighter()); }
                public void mouseExited(MouseEvent e) { setBackground(baseColor); }
            });
        }
    }
}