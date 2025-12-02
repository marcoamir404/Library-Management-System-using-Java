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

    private final Color PRIMARY_COLOR = new Color(44, 62, 80);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);

    public UserSearchUI(UserManager userManager) {
        this.userManager = userManager;

        // إعدادات النافذة
        setTitle("User Search");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== Header Panel =====
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("User Search");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.NORTH);

        // ===== Search Panel داخل الـ Header =====
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        searchPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("Search User:");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        searchField = new JTextField(25);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(200, 30));

        JButton searchButton = createStyledButton("Search", SECONDARY_COLOR);
        JButton backButton = createStyledButton("Back", new Color(128, 128, 128));

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(backButton);

        headerPanel.add(searchPanel, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);

        // ===== Table Panel =====
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

        // ===== Buttons Panel أسفل الجدول =====
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonsPanel.setBackground(Color.WHITE);

        JButton viewButton = createStyledButton("View", new Color(39, 174, 96));
        JButton editButton = createStyledButton("Edit", new Color(255, 165, 0));
        JButton deleteButton = createStyledButton("Delete", new Color(220, 20, 60));

        buttonsPanel.add(viewButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        add(buttonsPanel, BorderLayout.SOUTH);

        // ===== Load all users initially =====
        loadAllUsers();

        // ===== Action Listeners =====
        searchButton.addActionListener(e -> performSearch());
        backButton.addActionListener(e -> dispose());
        viewButton.addActionListener(e -> viewUser());
        editButton.addActionListener(e -> editUser());
        deleteButton.addActionListener(e -> deleteUser());
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void loadAllUsers() {
        tableModel.setRowCount(0);
        List<User> allUsers = User.users;
        if (allUsers != null) {
            for (User u : allUsers) {
                tableModel.addRow(new Object[]{
                        u.getUserId(), u.getName(), u.getUsername(), u.getEmail(), u.getUserType()
                });
            }
        }
    }

    private void performSearch() {
        String query = searchField.getText().toLowerCase();
        tableModel.setRowCount(0);
        if (query.isEmpty()) {
            loadAllUsers();
            return;
        }
        for (User u : User.users) {
            if (u.getUserId().toLowerCase().contains(query) ||
                u.getName().toLowerCase().contains(query) ||
                u.getUsername().toLowerCase().contains(query) ||
                u.getEmail().toLowerCase().contains(query)) {
                tableModel.addRow(new Object[]{
                        u.getUserId(), u.getName(), u.getUsername(), u.getEmail(), u.getUserType()
                });
            }
        }
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

    private void viewUser() {
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
        User u = getSelectedUser();
        if (u != null) {
            JOptionPane.showMessageDialog(this, "Edit user: " + u.getUsername());
        }
    }

    private void deleteUser() {
        User u = getSelectedUser();
        if (u != null) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete " + u.getUsername() + "?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                User.users.remove(u);
                loadAllUsers();
            }
        }
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        javax.swing.SwingUtilities.invokeLater(() -> new UserSearchUI(userManager).setVisible(true));
    }
}
