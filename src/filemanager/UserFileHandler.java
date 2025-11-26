package filemanager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import dataModel.Admin;
import dataModel.Librarian;
import dataModel.Patron;
import dataModel.User;
import enums.UserType;

public class UserFileHandler {

    private static final String FILE_PATH = "users.txt";

    public void saveUsers(List<User> users) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH));

            for (User u : users) {
                pw.println(
                        u.getUserType() + "," +          // IMPORTANT
                        u.getUserId() + "," +
                        u.getUsername() + "," +
                        u.getPassword() + "," +
                        u.getName() + "," +
                        u.getEmail() + "," +
                        u.getPhone()
                );
            }

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return users; // No file yet → return empty list
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length < 7) continue; // Skip invalid lines

                UserType type = UserType.valueOf(parts[0]);
                String userId = parts[1];
                String username = parts[2];
                String password = parts[3];
                String name = parts[4];
                String email = parts[5];
                String phone = parts[6];

                User user = null;

                
                switch (type) {
                    case ADMIN:
                        user = new Admin(userId, username, password, name, email, phone);
                        break;

                    case LIBRARIAN:
                        user = new Librarian(userId, username, password, name, email, phone);
                        break;

                    case PATRON:
                        user = new Patron(userId, username, password, name, email, phone);
                        break;
                }

                if (user != null) {
                    users.add(user);
                }
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
