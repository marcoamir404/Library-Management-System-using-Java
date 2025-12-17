package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import dataModel.Patron;
import dataModel.User;
import enums.UserType;
import managers.UserManager;

public class PatronSignUpUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nameField, emailField, phoneField, usernameField;
    private JPasswordField txtPassword;
    private UserManager userManager;
    private JFrame parentLoginFrame; 

    private final Color COLOR_PRIMARY = new Color(44, 62, 80);
    private final Color COLOR_SUCCESS = new Color(39, 174, 96);

    public PatronSignUpUI(JFrame parent) {
    	setResizable(false);
        this.parentLoginFrame = parent;
        this.userManager = new UserManager();

        setTitle("New Patron Registration");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(COLOR_PRIMARY);
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        getContentPane().add(title, BorderLayout.NORTH);

        
        JPanel form = new JPanel(new GridLayout(10, 1, 10, 10));
        form.setBorder(new EmptyBorder(0, 40, 20, 40));
        form.setBackground(Color.WHITE);

        
        JLabel name = new JLabel("Full Name:");
        name.setFont(new Font("Segoe UI", Font.BOLD, 12));
        name.setForeground(Color.DARK_GRAY);
        form.add(name);
        nameField = new JTextField();
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        form.add(nameField);
        
        
        JLabel email = new JLabel("Email:");
        email.setFont(new Font("Segoe UI", Font.BOLD, 12));
        email.setForeground(Color.DARK_GRAY);
        form.add(email);
        emailField = new JTextField();
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        form.add(emailField);
        
        JLabel phone = new JLabel("Phone Number:");
        phone.setFont(new Font("Segoe UI", Font.BOLD, 12));
        phone.setForeground(Color.DARK_GRAY);
        form.add(phone);
        phoneField = new JTextField();
        phoneField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phoneField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        form.add(phoneField);
        
        JLabel username = new JLabel("Username:");
        username.setFont(new Font("Segoe UI", Font.BOLD, 12));
        username.setForeground(Color.DARK_GRAY);
        form.add(username);
        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        form.add(usernameField);
        
        JLabel password = new JLabel("Password:");
        username.setFont(new Font("Segoe UI", Font.BOLD, 12));
        username.setForeground(Color.DARK_GRAY);
        form.add(password);
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        form.add(txtPassword);

        getContentPane().add(form, BorderLayout.CENTER);

        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        btnPanel.setBackground(Color.WHITE);

        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBackground(COLOR_SUCCESS);
        btnRegister.setFocusPainted(false);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegister.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { setBackground(COLOR_SUCCESS.brighter()); }
            public void mouseExited(MouseEvent e) { setBackground(COLOR_SUCCESS); }
        });
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setFocusPainted(false);
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { setBackground(Color.GRAY.brighter()); }
            public void mouseExited(MouseEvent e) { setBackground(Color.GRAY); }
        });

        btnRegister.setPreferredSize(new Dimension(150, 40));
        btnCancel.setPreferredSize(new Dimension(100, 40));

        
        btnRegister.addActionListener(e -> registerPatron());

        
        btnCancel.addActionListener(e -> {
            this.dispose();
            parentLoginFrame.setVisible(true);
        });

        btnPanel.add(btnRegister);
        btnPanel.add(btnCancel);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
        
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                parentLoginFrame.setVisible(true);
            }
        });
    }

    private void registerPatron() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String user = usernameField.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        
        if (name.isEmpty() || email.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!email.matches(".+@.+\\.com")) {
        	 JOptionPane.showMessageDialog(this, "Incorrect Email Syntax!", "Error", JOptionPane.ERROR_MESSAGE);
             return;
        }
        
        if(!phone.matches("\\d{11,15}")) {
       	 JOptionPane.showMessageDialog(this, "Incorrect Phone lenght!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
       }

       
        if (userManager.usernameExists(user)) {
            JOptionPane.showMessageDialog(this, "Username already taken. Please choose another.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        
        String newId = generateUserId();

        
        Patron newPatron = new Patron(newId, user, pass, name, email, phone);

        
        userManager.addUser(newPatron);

        
        JOptionPane.showMessageDialog(this, "Account created successfully! Please Login.", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        parentLoginFrame.setVisible(true);
    }
    
    private String generateUserId() {
        int max = 0;

        for(User u : User.users){
            if(u.getUserType() == UserType.PATRON){
                String id = u.getUserId().substring(1); 
                try{
                    int num = Integer.parseInt(id);
                    if(num > max) max = num;
                } catch(Exception ignore){}
            }
        }

        return "P" + (max + 1);
        
    }
}