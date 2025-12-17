package ui;

import javax.swing.*;

import managers.UserManager;

import java.awt.*;

public class AdminDashBoardUI extends JFrame {
	
	private UserManager userManager;
	private String thisAdminID;
    public AdminDashBoardUI(String id) {
    	
    	this.userManager = new UserManager();
    	this.thisAdminID = id;
    	
        setTitle("Admin Dashboard");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(new Color(44,62,80));
        top.setPreferredSize(new Dimension(900,120));

        JLabel subtitle = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JPanel titleBox = new JPanel();
        titleBox.setOpaque(false);
        titleBox.setLayout(new BorderLayout(0, 0));
        titleBox.add(subtitle);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBackground(new Color(231,76,60)); 
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setPreferredSize(new Dimension(100,35));
        btnLogout.setFocusPainted(false);

        JPanel rightBox = new JPanel();
        rightBox.setOpaque(false);
        rightBox.setLayout(new BorderLayout(0, 0));
        rightBox.add(btnLogout, BorderLayout.SOUTH);

        top.add(titleBox, BorderLayout.CENTER);
        
        JLabel title = new JLabel("Library System", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleBox.add(title, BorderLayout.NORTH);
        top.add(rightBox, BorderLayout.EAST);
        getContentPane().add(top, BorderLayout.NORTH);


        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 100));
        center.setBackground(Color.WHITE);

        JButton ManageUsersBtn = new JButton("Manage Users");
        ManageUsersBtn.setPreferredSize(new Dimension(200,60));
        ManageUsersBtn.setFont(new Font("Segoe UI",Font.PLAIN,18));
        ManageUsersBtn.setBackground(new Color(204,204,204));

        JButton MansgeBooksBtn = new JButton("Manage Books");
        MansgeBooksBtn.setPreferredSize(new Dimension(200,60));
        MansgeBooksBtn.setFont(new Font("Segoe UI",Font.PLAIN,18));
        MansgeBooksBtn.setBackground(new Color(204,204,204));
        
        JButton editMyAcc = new JButton("Edit my account");
        editMyAcc.setPreferredSize(new Dimension(200, 60));
        editMyAcc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        editMyAcc.setBackground(new Color(204, 204, 204));
        
        center.add(editMyAcc);
        center.add(ManageUsersBtn);
        center.add(MansgeBooksBtn);

        getContentPane().add(center, BorderLayout.CENTER);

        ManageUsersBtn.addActionListener(e -> {
        	new UserListUI(userManager, this).setVisible(true);
        });

        MansgeBooksBtn.addActionListener(e -> {
        	new BookManagementUI().setVisible(true);
        });
        
        editMyAcc.addActionListener(e -> {
        	new UserFormUI(this, userManager, userManager.searchUserById(id), true).setVisible(true);
        });
        
        btnLogout.addActionListener(e -> {
            
             new LoginUI().setVisible(true);
             dispose();
        });
    }

}
