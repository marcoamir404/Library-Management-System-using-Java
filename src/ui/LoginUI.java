package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import dataModel.User;
import enums.UserType;
import managers.UserManager;


public class LoginUI extends JFrame {
	
	UserManager userManager;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;

	
	public LoginUI() {
		userManager = new UserManager();
		
		setTitle("LMS - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBounds(5, 5, 424, 100);
		header.setPreferredSize(new Dimension(10, 100));
		header.setAlignmentX(Component.RIGHT_ALIGNMENT);
		header.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		header.setBackground(new Color(44, 62, 80));
		contentPane.add(header);
		header.setLayout(null);
		
		JLabel loginTitel = new JLabel("Library System");
		loginTitel.setBounds(109, 33, 206, 35);
		loginTitel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		loginTitel.setAlignmentY(5.0f);
		loginTitel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		loginTitel.setForeground(new Color(255, 255, 255));
		header.add(loginTitel);
		
		JLabel username = new JLabel("Username");
		username.setBounds(22, 152, 135, 17);
		username.setFont(new Font("Arial Black", Font.BOLD, 20));
		username.setForeground(new Color(93, 93, 93));
		contentPane.add(username);
		
		userField = new JTextField();
		userField.setBorder(UIManager.getBorder("Tree.editorBorder"));
		userField.setToolTipText("username");
		userField.setBounds(89, 180, 251, 27);
		contentPane.add(userField);
		userField.setColumns(10);
		
		JLabel password = new JLabel("Password");
		password.setForeground(new Color(93, 93, 93));
		password.setFont(new Font("Arial Black", Font.BOLD, 20));
		password.setBounds(22, 243, 135, 17);
		contentPane.add(password);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(89, 271, 251, 27);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(44, 62, 80));
		loginButton.setBounds(89, 352, 251, 37);
		loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { setBackground(new Color(44, 62, 80).brighter()); }
            public void mouseExited(MouseEvent e) { setBackground(new Color(44, 62, 80)); }
        });
		contentPane.add(loginButton);
		
		loginButton.addActionListener(e-> performLogin());
		
		JLabel lblNewLabel = new JLabel("don't have a patron accout?");
		lblNewLabel.setBounds(22, 398, 208, 14);
		contentPane.add(lblNewLabel);
		
		JButton signInButton = new JButton("Create new account");
		signInButton.setForeground(Color.WHITE);
		signInButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		signInButton.setBackground(new Color(0, 157, 157));
		signInButton.setBounds(89, 420, 251, 37);
		signInButton.setFocusPainted(false);
        signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		signInButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { setBackground(new Color(0, 157, 157).brighter()); }
            public void mouseExited(MouseEvent e) { setBackground(new Color(0, 157, 157)); }
        });
		contentPane.add(signInButton);

		signInButton.addActionListener(e-> {
			new PatronSignUpUI(this).setVisible(true);
			this.setVisible(false);
		});
		
	}
	
	private void performLogin() {
		String username = userField.getText().trim();
		String password = new String (passwordField.getPassword()).trim();
		
		if(username.isEmpty()||password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		User user = userManager.login(username, password);
		
		if (user != null) {
            JOptionPane.showMessageDialog(this, "Welcome back, " + user.getName() + "!");
		
            if (user.getUserType() == UserType.ADMIN) {
                new AdminDashBoardUI().setVisible(true);
            } 
            else if (user.getUserType() == UserType.LIBRARIAN) {
               new LibrarianDashboardUI().setVisible(true);
            } 
            else {
                //new PatronDashboardUI((Patron) user).setVisible(true);
            }
            
            this.dispose();
        } else {
            
            
            JOptionPane.showMessageDialog(this, "Invalid Username or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
		}
		
}
























