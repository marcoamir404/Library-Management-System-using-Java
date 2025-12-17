package filemanager;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import dataModel.Admin;
import dataModel.Librarian;
import dataModel.Patron;
import dataModel.User;
import enums.UserType;
import managers.BookManager;
import managers.ReservationManager;
import managers.TransactionManager;
import managers.UserManager;

public class UserFileHandler {
	private UserManager userManager;
	private BookManager bookManager;
	private TransactionManager transactionManager;
	private ReservationManager reservationManager;
    private static final String FILE_PATH = "dataFiles/users.txt";

    public void saveUsers(List<User> users) {
        StringBuilder sb = new StringBuilder();

		for (User u : users) {
		       sb.append(u.getUserType()).append(",") 
		       	.append(u.getUserId()).append(",") 
		       	.append(u.getUsername()).append(",")
		       	.append(u.getPassword()).append(",")
		       	.append(u.getName()).append(",")
		       	.append(u.getEmail()).append(",")
		        .append(u.getPhone()).append("\n");
		}

		FileManager.writeToFile(FILE_PATH, sb.toString());
    }

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        String data = FileManager.readFromFile(FILE_PATH);
        
        if(data.isEmpty()) return users;
        
        String[] lines = data.split("\n");


            for(String user : lines) {
                String[] parts = user.split(",");

                if (parts.length < 7) continue;

                UserType type = UserType.valueOf(parts[0]);
                String userId = parts[1];
                String username = parts[2];
                String password = parts[3];
                String name = parts[4];
                String email = parts[5];
                String phone = parts[6];

                
                switch (type) {
                    case ADMIN:
                        Admin a = new Admin(userId, username, password, name, email, phone);
                        a.setUserManager(userManager);
                        a.setBookManager(bookManager);
                        if(a != null) users.add(a);
                        break;

                    case LIBRARIAN:
                        Librarian b = new Librarian(userId, username, password, name, email, phone);
                        b.setReservationMan(reservationManager);
                        b.setTransactionMan(transactionManager);
                        b.setUserMan(userManager);
                        if(b != null) users.add(b);
                        break;

                    case PATRON:
                        Patron p = new Patron(userId, username, password, name, email, phone);
                        p.setBookMan(bookManager);
                        p.setReservationMan(reservationManager);
                        p.setTransactionMan(transactionManager);
                        p.setUserMan(userManager);
                        if(p != null) users.add(p);
                        break;
                }

            }

        return users;
    }
}
