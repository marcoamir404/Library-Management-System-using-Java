package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;


import dataModel.User;
import managers.UserManager;

public class UserSearchUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField searchField;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private UserManager userManager;
    private JFrame parent;
    
    private final Color PRIMARY_COLOR = new Color(44, 62, 80);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);

    public UserSearchUI(JFrame parent) {
    	this.userManager = new UserManager();
    	this.parent = parent;
    	
        setTitle("User Search");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("User Search");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        searchPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("Search User:");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        searchField = new JTextField(25);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(200, 30));

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(SECONDARY_COLOR);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(128, 128, 128));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton refreshButton = new JButton("Refresh All");
        refreshButton.setBackground(new Color(149, 165, 166));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(backButton);
        searchPanel.add(refreshButton);
        
        

        headerPanel.add(searchPanel, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);


        String[] columnNames = {"ID", "Name", "Username", "Email", "Type"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        resultTable = new JTable(tableModel);
        resultTable.setFillsViewportHeight(true);
        resultTable.setRowHeight(30);
        resultTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultTable.setSelectionBackground(new Color(189, 195, 199));

        JTableHeader tableHeader = resultTable.getTableHeader();
        tableHeader.setBackground(new Color(220, 220, 220));
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonsPanel.setBackground(Color.WHITE);

        JButton viewButton = new JButton("View");
        viewButton.setBackground(new Color(39, 174, 96));
        viewButton.setForeground(Color.WHITE);
        viewButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        viewButton.setFocusPainted(false);
        viewButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        viewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        JButton editButton = new JButton("Edit");
        editButton.setBackground(new Color(255, 165, 0));
        editButton.setForeground(Color.WHITE);
        editButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        editButton.setFocusPainted(false);
        editButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(220, 20, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        

        buttonsPanel.add(viewButton);
        if(this.parent instanceof AdminDashBoardUI) {
        	buttonsPanel.add(editButton);
        	buttonsPanel.add(deleteButton);
        }
       
        add(buttonsPanel, BorderLayout.SOUTH);


        searchButton.addActionListener(e -> performSearch());
        refreshButton.addActionListener(e -> loadAllUsers());
        backButton.addActionListener(e -> dispose());
        viewButton.addActionListener(e -> viewUser());
        editButton.addActionListener(e -> editUser());
        deleteButton.addActionListener(e -> {
        	int row = resultTable.getSelectedRow();
    	    if (row == -1) {
    	        JOptionPane.showMessageDialog(this, "Select a user first!");
    	        return;
    	    }

    	    String id = (String) resultTable.getValueAt(row, 0);

    	    int confirm = JOptionPane.showConfirmDialog(this,"Delete user " + id + "?","Confirm",JOptionPane.YES_NO_OPTION);

    	    if (confirm != JOptionPane.YES_OPTION) return;

    	    boolean success = userManager.deleteUser(id);

    	    if (success) {
    	        JOptionPane.showMessageDialog(this, "User deleted successfully!");
    	        loadAllUsers();
    	    } else {
    	        JOptionPane.showMessageDialog(this,"Cannot delete this user.\nThey still have borrowed books!","Delete Failed",JOptionPane.WARNING_MESSAGE);
    	    }
        });
        
        loadAllUsers();

        
    }


    private void loadAllUsers() {
        tableModel.setRowCount(0);
        List<User> allUsers = User.users;
        updateTable(allUsers);
    }
    
    
    private void updateTable(List<User> users) {
        tableModel.setRowCount(0);
        	if (users != null) {
                for (User u : users) {
                    tableModel.addRow(new Object[]{
                            u.getUserId(), u.getName(), u.getUsername(), u.getEmail(), u.getUserType()
                    });
                }
            }
    }

    
    private void performSearch() {
        String query = searchField.getText().toLowerCase();
        List<User> results = userManager.searchUsers(query);
        updateTable(results);
    }
    

    private User getSelectedUser() {
        int row = resultTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user first!");
            return null;
        }
        String userId = (String) tableModel.getValueAt(row, 0);
        for (User u : User.users) {
            if (u.getUserId().equals(userId)) return u;
        }
        return null;
    }
    

    private void viewUser() { // edit with userform
        User u = getSelectedUser();
        if (u != null) {
            JOptionPane.showMessageDialog(this,
                    "ID: " + u.getUserId() + "\n" +
                    "Name: " + u.getName() + "\n" +
                    "Username: " + u.getUsername() + "\n" +
                    "Email: " + u.getEmail() + "\n" +
                    "Type: " + u.getUserType(),
                    "User Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    

    private void editUser() {
    	int row = resultTable.getSelectedRow();
    	if(row == -1) return;
    	String id = (String) resultTable.getValueAt(row, 0);
    	User target = null;
    	for (User u : User.users) {
    	    if (u.getUserId().equals(id)) {
    	        target = u;   
    	        break;
    	    }
    	}

    	if (target != null) {
    		 UserFormUI form = new UserFormUI(this, userManager, target, false);

    		    form.addWindowListener(new java.awt.event.WindowAdapter() {
    		        @Override
    		        public void windowClosed(java.awt.event.WindowEvent e) {
    		            loadAllUsers();
    		        }
    		    });

    		    form.setVisible(true);
    	}
    	loadAllUsers();
    }
    
}
