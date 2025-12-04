
package ui;

import dataModel.Book;
import java.util.List;
import dataModel.Book;
import dataModel.Filters;
import enums.BookStatus;
import filemanager.BookFileHandler;
import filemanager.FileManager;
import javax.swing.JOptionPane;
import managers.BookManager;
/**
 *
 * @author user
 */
public class BookManagement extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BookManagement.class.getName());
private javax.swing.table.DefaultTableModel model;
    private BookManager bookManager;

    /**
     * Creates new form BookManagement
     */
public BookManagement() {
    initComponents();                   
    model = (javax.swing.table.DefaultTableModel) BookTable.getModel();
            bookManager = new BookManager();
            loadAllBooks();          

}


    private void initComponents() {

        LabelForTitle = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        BookTable = new javax.swing.JTable();
        EditBtn = new javax.swing.JButton();
        RemoveBtn = new javax.swing.JButton();
        AddBtn2 = new javax.swing.JButton();
        Close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LabelForTitle.setBackground(new java.awt.Color(44, 62, 80));

        Title.setBackground(new java.awt.Color(44, 62, 80));
        Title.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setText("Library System");

        javax.swing.GroupLayout LabelForTitleLayout = new javax.swing.GroupLayout(LabelForTitle);
        LabelForTitle.setLayout(LabelForTitleLayout);
        LabelForTitleLayout.setHorizontalGroup(
            LabelForTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LabelForTitleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(482, 482, 482))
        );
        LabelForTitleLayout.setVerticalGroup(
            LabelForTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabelForTitleLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        BookTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Title", "Author", "Genre", "Status", "summary", "Year"
            }
        ));
        jScrollPane1.setViewportView(BookTable);

        EditBtn.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Yellow"));
        EditBtn.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        EditBtn.setForeground(new java.awt.Color(255, 255, 255));
        EditBtn.setText("Edit");
        EditBtn.addActionListener(this::EditBtnActionPerformed);

        RemoveBtn.setBackground(javax.swing.UIManager.getDefaults().getColor("Component.error.focusedBorderColor"));
        RemoveBtn.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        RemoveBtn.setForeground(new java.awt.Color(255, 255, 255));
        RemoveBtn.setText("Remove");
        RemoveBtn.addActionListener(this::RemoveBtnActionPerformed);

        AddBtn2.setBackground(new java.awt.Color(44, 62, 80));
        AddBtn2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        AddBtn2.setForeground(new java.awt.Color(255, 255, 255));
        AddBtn2.setText("Add ");
        AddBtn2.setPreferredSize(new java.awt.Dimension(72, 34));
        AddBtn2.addActionListener(this::AddBtn2ActionPerformed);

        Close.setBackground(new java.awt.Color(0, 0, 0));
        Close.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Close.setForeground(new java.awt.Color(255, 255, 255));
        Close.setText("Close");
        Close.addActionListener(this::CloseActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelForTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(AddBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(RemoveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1526, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LabelForTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RemoveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AddBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
        );

        pack();
    }


        private void loadAllBooks() {
        List<Book> allBooks = bookManager.getAllBooks();
        updateTable(allBooks);
<<<<<<< HEAD
      }
        private void updateTable(List<Book> books) {
		    model.setRowCount(0); 
		    for (Book b : books) {
		        Object[] row = {
		            b.getBookId(),
		            b.getTitle(),
		            b.getAuthor(),
		            b.getGenre(),
		          
		            b.getStatus(),
		            b.getSummary()
		        };
		        model.addRow(row);
		    }
		}
        
	    private void EditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditBtnActionPerformed
			int selectedRow = BookTable.getSelectedRow();
			if (selectedRow == -1) {
			    JOptionPane.showMessageDialog(this, "Please select a book first!");
			    return;
			}
		    String bookId = (String)BookTable.getValueAt(selectedRow, 0);
		    String title = (String)BookTable.getValueAt(selectedRow, 1);
		    String author = (String)BookTable.getValueAt(selectedRow, 2);
		    String genre = (String)BookTable.getValueAt(selectedRow, 3);
		    String year = (String)BookTable.getValueAt(selectedRow, 4);   
		    String status = (String)BookTable.getValueAt(selectedRow, 5);   
		    String Summary = (String)BookTable.getValueAt(selectedRow, 6);   
		    EditBookForm editForm = new EditBookForm(bookId, title, author, genre,year,Summary, status);
		    editForm.setVisible(true);
		    editForm.addWindowListener(new java.awt.event.WindowAdapter() {
		        @Override
		        public void windowClosed(java.awt.event.WindowEvent e) {
		            loadAllBooks(); 
		        }
		    });
	    // TODO add your handling code here:
	    }//GEN-LAST:event_EditBtnActionPerformed
=======
    }
private void updateTable(List<Book> books) {
    model.setRowCount(0); 
    for (Book b : books) {
        Object[] row = {
            b.getBookId(),
            b.getTitle(),
            b.getAuthor(),
            b.getGenre(),
          
            b.getStatus(),
            b.getSummary()
        };
        model.addRow(row);
    }
}
    private void EditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditBtnActionPerformed
 int selectedRow = BookTable.getSelectedRow();
if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Please select a book first!");
    return;
}
      String bookId = (String)BookTable.getValueAt(selectedRow, 0);
    String title = (String)BookTable.getValueAt(selectedRow, 1);
    String author = (String)BookTable.getValueAt(selectedRow, 2);
    String genre = (String)BookTable.getValueAt(selectedRow, 3);
    String status = (String)BookTable.getValueAt(selectedRow, 4);   
    String Summary = (String)BookTable.getValueAt(selectedRow, 5);   
        String year = (String)BookTable.getValueAt(selectedRow, 6);   

      EditBookForm editForm = new EditBookForm(bookId, title, author, genre,year,Summary, status);
    editForm.setVisible(true);
    editForm.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            loadAllBooks(); 
        }
    });
    // TODO add your handling code here:
    }//GEN-LAST:event_EditBtnActionPerformed
>>>>>>> 0f291a4e6857e62e52519dccd9e59fe9cc47af8c

    private void RemoveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveBtnActionPerformed
 
    int selectedRow = BookTable.getSelectedRow();

    String bookId = (String) BookTable.getValueAt(selectedRow, 0);

    model.removeRow(selectedRow);
    bookManager.removeBook(bookId);

    }//GEN-LAST:event_RemoveBtnActionPerformed

    private void AddBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtn2ActionPerformed
   java.awt.EventQueue.invokeLater(() -> {
            new AddBookForm().setVisible(true); 
<<<<<<< HEAD
        });        
    }
     
    
=======
        });        // TODO add your handling code here:
    }//GEN-LAST:event_AddBtn2ActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
 LibrarianDashboard booksForm = new LibrarianDashboard();
    booksForm.setVisible(true);   
     this.dispose(); 
      // TODO add your handling code here:
    }//GEN-LAST:event_CloseActionPerformed

    /**
     * @param args the command line arguments
     */
>>>>>>> 0f291a4e6857e62e52519dccd9e59fe9cc47af8c
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BookManagement().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn2;
    private javax.swing.JTable BookTable;
    private javax.swing.JButton Close;
    private javax.swing.JButton EditBtn;
    private javax.swing.JPanel LabelForTitle;
    private javax.swing.JButton RemoveBtn;
    private javax.swing.JLabel Title;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
