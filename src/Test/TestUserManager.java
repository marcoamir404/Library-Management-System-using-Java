package Test;
import managers.UserManager;
import dataModel.User;
import dataModel.Admin;
import dataModel.Patron;
import java.util.List;
public class TestUserManager {
     public static void main(String[] args) {
        UserManager userManager = new UserManager();

        Admin admin1 = new Admin("A001", "admin1", "pass123", "Alice", "alice@mail.com", "0123456789");
        Patron patron1 = new Patron("P001", "patron1", "pass456", "Bob", "bob@mail.com", "0987654321");

        userManager.addUser(admin1);
        userManager.addUser(patron1);

        System.out.println("Users added:");

        for (User u : userManager.getAllUsers()) {
            System.out.println(u.getUserType() + ": " + u.getName() + " (" + u.getUsername() + ")");
        }

        System.out.println("\n Search for 'Alice':");
        List<User> searchResult = userManager.searchUser("Alice");
        for (User u : searchResult) {
            System.out.println("Found: " + u.getName() + " (" + u.getUsername() + ")");
        }

        System.out.println("\n Login attempt for 'admin1' with correct password:");
        User loginUser = userManager.login("admin1", "pass123");
        System.out.println(loginUser != null ? "Login successful for: " + loginUser.getName() : "Login failed");

        System.out.println("\n Login attempt for 'patron1' with wrong password:");
        loginUser = userManager.login("patron1", "wrongpass");
        System.out.println(loginUser != null ? "Login successful for: " + loginUser.getName() : "Login failed");

        System.out.println("\n️ Updating Bob's email...");
        patron1.setEmail("bob_new@mail.com");
        userManager.updateUser(patron1);
        System.out.println("Updated email: " + userManager.searchUser("Bob").get(0).getEmail());

        System.out.println("\n Saving users to file...");
        userManager.saveUsersToFile("users.dat");

        System.out.println(" Loading users from file into new UserManager...");
        UserManager loadedManager = new UserManager();
        loadedManager.loadUsersFromFile("users.dat");

        System.out.println("Loaded users:");
        for (User u : loadedManager.getAllUsers()) {
            System.out.println(u.getUserType() + ": " + u.getName() + " (" + u.getEmail() + ")");
        }
        System.out.println("\nTest complete!");
    }
}

