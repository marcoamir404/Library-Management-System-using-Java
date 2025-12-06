package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.annotation.Target;
import java.util.List;

import dataModel.Admin;
import dataModel.Librarian;
import dataModel.Patron;
import dataModel.User;
import managers.UserManager;

public class UserListUI extends JFrame {


	private static final long serialVersionUID = 1L;
	private JTable table;
    private DefaultTableModel model;
    private JButton addBtn, editBtn, deleteBtn, backBtn;
    private UserManager userManager;
    private JButton searchBtn;

    public UserListUI(UserManager userManager) {
        this.userManager = userManager;

        setTitle("User List");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(44, 62, 80));
        topPanel.setPreferredSize(new Dimension(getWidth(), 70));
        topPanel.setLayout(new GridBagLayout());
        JLabel titleLabel = new JLabel("User List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        String[] cols = {"ID", "Name", "Username", "Email", "Type"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionBackground(new Color(30, 144, 255));
        table.setSelectionForeground(Color.WHITE);
        table.setFillsViewportHeight(true);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(230, 230, 230));
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 15, 10)); 
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        addBtn = new JButton("Add New User");
        addBtn.setBackground(new Color(44,62,80));
        addBtn.setForeground(Color.WHITE);
        addBtn.setPreferredSize(new Dimension(160, 40));

        editBtn = new JButton("Edit");
        editBtn.setBackground(new Color(255, 165, 0));
        editBtn.setForeground(Color.WHITE);
        editBtn.setPreferredSize(new Dimension(160, 40));
        
        backBtn = new JButton("Back to Dashboard");
        backBtn.setBackground(new Color(128, 128, 128));
        backBtn.setForeground(Color.WHITE);

        addBtn.setFocusPainted(false);
        editBtn.setFocusPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setPreferredSize(new Dimension(160, 40));

        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(backBtn);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
                deleteBtn = new JButton("Delete");
                deleteBtn.setBackground(new Color(220, 20, 60));
                deleteBtn.setForeground(Color.WHITE);
                deleteBtn.setPreferredSize(new Dimension(160, 40));
                deleteBtn.setFocusPainted(false);
                buttonPanel.add(deleteBtn);
                
                deleteBtn.addActionListener(e -> {
                	 int row = table.getSelectedRow();
                	    if (row == -1) {
                	        JOptionPane.showMessageDialog(this, "Select a user first!");
                	        return;
                	    }

                	    String id = (String) table.getValueAt(row, 0);

                	    int confirm = JOptionPane.showConfirmDialog(this,"Delete user " + id + "?","Confirm",JOptionPane.YES_NO_OPTION);

                	    if (confirm != JOptionPane.YES_OPTION) return;

                	    boolean success = userManager.deleteUser(id);

                	    if (success) {
                	        JOptionPane.showMessageDialog(this, "User deleted successfully!");
                	        loadUsers();
                	    } else {
                	        JOptionPane.showMessageDialog(this,"Cannot delete this user.\nThey still have borrowed books!","Delete Failed",JOptionPane.WARNING_MESSAGE);
                	    }
                });
        
        searchBtn = new JButton("Search");
        searchBtn.setPreferredSize(new Dimension(160, 40));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);
        searchBtn.setBackground(new Color(44,62,80));
        buttonPanel.add(searchBtn);
        
        searchBtn.addActionListener(e ->{
        	new UserSearchUI().setVisible(true);
        });
        
        addBtn.addActionListener(e ->{
        	 UserFormUI form = new UserFormUI(this, userManager, null);

        	    form.addWindowListener(new java.awt.event.WindowAdapter() {
        	        @Override
        	        public void windowClosed(java.awt.event.WindowEvent e) {
        	            loadUsers();
        	        }
        	    });

        	    form.setVisible(true);
        });
        
        editBtn.addActionListener(e ->{
        	int row = table.getSelectedRow();
        	if(row == -1) return;
        	String id = (String) table.getValueAt(row, 0);
        	User target = null;
        	for (User u : User.users) {
        	    if (u.getUserId().equals(id)) {
        	        target = u;   
        	        break;
        	    }
        	}

        	if (target != null) {
        		 UserFormUI form = new UserFormUI(this, userManager, target);

        		    form.addWindowListener(new java.awt.event.WindowAdapter() {
        		        @Override
        		        public void windowClosed(java.awt.event.WindowEvent e) {
        		            loadUsers();
        		        }
        		    });

        		    form.setVisible(true);
        	}
        });
        
        backBtn.addActionListener(e ->{
        	this.dispose();
        });
        
        loadUsers();
    }
        

    private void loadUsers() {
        model.setRowCount(0);
        List<User> allUsers = userManager != null ? userManager.getUsers() : User.users;
        updateTable(allUsers);
    }

    private void updateTable(List<User> users) {
        model.setRowCount(0);
        	if (users != null) {
                for (User u : users) {
                    model.addRow(new Object[]{
                            u.getUserId(), u.getName(), u.getUsername(), u.getEmail(), u.getUserType()
                    });
                }
            }
    }
    
 
}

 


