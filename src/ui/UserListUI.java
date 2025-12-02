package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import dataModel.User;
import managers.UserManager;

public class UserListUI extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton addBtn, editBtn, deleteBtn, backBtn;
    private UserManager userManager;

    public UserListUI(UserManager userManager) {
        this.userManager = userManager;

        setTitle("User List");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ====== Top Panel ======
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(44, 62, 80));
        topPanel.setPreferredSize(new Dimension(getWidth(), 70));
        topPanel.setLayout(new GridBagLayout());
        JLabel titleLabel = new JLabel("User List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // ====== Table setup ======
        String[] cols = {"ID", "Name", "Username", "Type", "Email"};
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

        // Zebra Striping
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
        add(scrollPane, BorderLayout.CENTER);

        // ====== Buttons Panel ======
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 15, 10)); // 2 صفوف × 2 أعمدة + مسافات
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        addBtn = new JButton("Add New User");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        backBtn = new JButton("Back to Dashboard");

        // ألوان الأزرار
        addBtn.setBackground(new Color(44,62,80));
        addBtn.setForeground(Color.WHITE);

        editBtn.setBackground(new Color(255, 165, 0));
        editBtn.setForeground(Color.WHITE);

        deleteBtn.setBackground(new Color(220, 20, 60));
        deleteBtn.setForeground(Color.WHITE);

        backBtn.setBackground(new Color(128, 128, 128));
        backBtn.setForeground(Color.WHITE);

        addBtn.setFocusPainted(false);
        editBtn.setFocusPainted(false);
        deleteBtn.setFocusPainted(false);
        backBtn.setFocusPainted(false);

        Dimension btnSize = new Dimension(160, 40);
        addBtn.setPreferredSize(btnSize);
        editBtn.setPreferredSize(btnSize);
        deleteBtn.setPreferredSize(btnSize);
        backBtn.setPreferredSize(btnSize);

        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(backBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        loadUsers();
    }

    private void loadUsers() {
        model.setRowCount(0);
        List<User> allUsers = userManager != null ? userManager.users : User.users;
        for (User u : allUsers) {
            Object[] row = {
                    u.getUserId(),
                    u.getName(),
                    u.getUsername(),
                    u.getUserType(),
                    u.getEmail()
            };
            model.addRow(row);
        }
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        javax.swing.SwingUtilities.invokeLater(() -> new UserListUI(userManager).setVisible(true));
    }
}

 


