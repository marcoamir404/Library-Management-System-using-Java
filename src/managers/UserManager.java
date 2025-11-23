package managers;
import java.util.ArrayList;
import java.util.List;
import dataModel.User;
import java.io.*;
public class UserManager {
    private List<User> users;
    public UserManager() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(String userId) {
        users.removeIf(u -> u.getUserId().equals(userId));
    }

    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(updatedUser.getUserId())) {
                users.set(i, updatedUser);
                return;
            }
        }
    }

    public List<User> searchUser(String query) {
        List<User> result = new ArrayList<>();
        for (User u : users) {
            if (u.getName().toLowerCase().contains(query.toLowerCase()) ||
                u.getUsername().toLowerCase().contains(query.toLowerCase()) ||
                u.getEmail().toLowerCase().contains(query.toLowerCase())) {
                result.add(u);
            }
        }
        return result;
    }

    public User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null; 
    }

    public void saveUsersToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadUsersFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            users = (List<User>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, starting with empty user list.");
            users = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        return users;
    }
}
