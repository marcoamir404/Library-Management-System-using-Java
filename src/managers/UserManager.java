package managers;

import java.util.ArrayList;
import java.util.List;

import dataModel.User;

public class UserManager {
	
    public UserManager() {}

    public void addUser(User user) {
    	if(user == null) { return;}
        User.users.add(user);
    }

    public void deleteUser(String userId) {
    	if(userId == null) { return;}
        User.users.removeIf(u -> u.getUserId().equals(userId));
    }

    public void updateUser(User updatedUser) {
    	if(updatedUser == null) {return;}
    
        for (int i = 0; i < User.users.size(); i++) {
            if (User.users.get(i).getUserId().equals(updatedUser.getUserId())) {
                User.users.set(i, updatedUser);
                return;
            }
        }
    }

    public List<User> searchUser(String query) {
        List<User> result = new ArrayList<>();
        
        if (query == null) {return null;}
        
        query = query.toLowerCase();
        for (User u : User.users) {
            if (u.getUserId().toLowerCase().contains(query)||
            	u.getName().toLowerCase().contains(query) ||
                u.getUsername().toLowerCase().contains(query) ||
                u.getEmail().toLowerCase().contains(query)) {
                result.add(u);
            }
        }
        return result;
    }
    
    // Remember to update with authenticateManager
    public User login(String username, String password) {
        for (User u : User.users) {
            if (u.authenticate(username, password)) {
                return u;
            }
        }
        return null; 
    }
    
}