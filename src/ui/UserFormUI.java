package ui;

import dataModel.Admin;
import dataModel.Librarian;
import dataModel.Patron;
import dataModel.User;
import enums.UserType;
import managers.UserManager;

import javax.swing.*;


import java.awt.*;

public class UserFormUI extends JFrame {

    
	private static final long serialVersionUID = 1L;
	private JTextField txtName, txtUsername, txtEmail, txtPhone;
    private JPasswordField txtPassword;
    private JComboBox<String> typeBox;
    
    private static User userToEdit;
    private static UserManager userManager;
    private JFrame parent;
    private boolean myAcc;

    public UserFormUI(JFrame parent, UserManager manager, User user, boolean myAcc) {
        this.parent = parent;
        this.userManager = manager;
        this.userToEdit = user;
        this.myAcc = myAcc;
        
        setTitle("User Form");
        setSize(400, 500);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JLabel header = new JLabel("User Form", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(new Color(44, 62, 80));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(400, 60));
        getContentPane().add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(14, 1, 5, 5));
        form.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));


        form.add(new JLabel("Full Name:"));
        txtName = new JTextField();
        form.add(txtName);

        form.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        form.add(txtUsername);

        form.add(new JLabel("User Type:"));
        typeBox = new JComboBox<>(new String[]{"ADMIN", "LIBRARIAN", "PATRON"});
        form.add(typeBox);

        form.add(new JLabel("Phone:"));
        txtPhone = new JTextField();
        form.add(txtPhone);

        form.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        form.add(txtEmail);

        form.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        form.add(txtPassword);

        getContentPane().add(form, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnSave = new JButton("Save");
        JButton btnClose = new JButton("Close");

        btnSave.setBackground(new Color(44, 62, 80));
        btnSave.setForeground(Color.WHITE);

        btnSave.addActionListener(e -> saveUser());

        btnClose.setBackground(new Color(220, 20, 60));
        btnClose.setForeground(Color.WHITE);
        btnClose.addActionListener(e -> dispose());

        btnPanel.add(btnSave);
        btnPanel.add(btnClose);

        getContentPane().add(btnPanel, BorderLayout.SOUTH);
        
        if(userToEdit != null) {
        	fillData();
        }
    }
    
    private void fillData() {

        txtName.setText(userToEdit.getName());
        txtUsername.setText(userToEdit.getUsername());
        txtPassword.setText(userToEdit.getPassword());
        txtEmail.setText(userToEdit.getEmail());
        txtPhone.setText(userToEdit.getPhone());
        typeBox.setSelectedItem(userToEdit.getUserType());
        typeBox.setEnabled(false);
        if(!myAcc) {
        	txtUsername.setEnabled(false);
            txtPassword.setEnabled(false);
        }
    }

    private void saveUser() {
        String name = txtName.getText().trim();
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();
        UserType type = UserType.valueOf(typeBox.getSelectedItem().toString());

        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (userToEdit == null && userManager.usernameExists(username)) {
            JOptionPane.showMessageDialog(this, "Username already taken!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        if (userToEdit != null) {
            userToEdit.setName(name);
            userToEdit.setUsername(username);
            userToEdit.setPassword(pass);
            userToEdit.setEmail(email);
            userToEdit.setPhone(phone);
            userManager.updateUser(userToEdit);
            JOptionPane.showMessageDialog(this, "User Updated!");
        } else {
            User newUser = null;
        	String id = "";
            switch (type) {
                case ADMIN: 
                	id = generateUserId(UserType.ADMIN);
                	newUser = new Admin(id, username, pass, name, email, phone);
                	break;
                case LIBRARIAN: 
                	id = generateUserId(UserType.LIBRARIAN);
                	newUser = new Librarian(id, username, pass, name, email, phone); 
                	break;
                case PATRON: 
                	id = generateUserId(UserType.PATRON);
                	newUser = new Patron(id, username, pass, name, email, phone); 
                	break;
            }
           
            
            userManager.addUser(newUser);
            JOptionPane.showMessageDialog(this, "User Added!");
        }

        dispose();
        if (parent != null) parent.setVisible(true);
    }
    
    private String generateUserId(UserType type) {
        int max = 0;

        for(User u : User.users){
            if(u.getUserType() == type){
                String id = u.getUserId().substring(1); 
                try{
                    int num = Integer.parseInt(id);
                    if(num > max) max = num;
                } catch(Exception ignore){}
            }
        }

        switch(type){
            case ADMIN: return "A" + (max + 1);
            case LIBRARIAN: return "L" + (max + 1);
            case PATRON: return "P" + (max + 1);
        }
        return null;
    }

}
