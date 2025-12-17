package managers;

import java.util.ArrayList;
import java.util.List;

import dataModel.User;
import dataModel.Patron;
import filemanager.UserFileHandler;

public class UserManager {
	
	private UserFileHandler uf = new UserFileHandler();
	
	private List<User> users = User.users;
	
    public UserManager() {
    	if (users.isEmpty()) {
            users.addAll(uf.loadUsers());
        }
    }
    public List<User> getUsers() { return users;}

    public void addUser(User user) {
    	if(user == null) { return;}
    	if(!usernameExists(user.getUsername())) {
    		users.add(user);
    		uf.saveUsers(users);
    	}else {
			System.out.println("Username is already exists!\n");
			return;
		}
    }

    public boolean deleteUser(String userId) {
	    if (userId == null) return false;

	    User target = null;
	    for (User u : users) {
	        if (u.getUserId().equalsIgnoreCase(userId)) {
	            target = u;
	            break;
	        }
	    }
	    if (target == null) return false;

	    if (target instanceof Patron) {
	        Patron p = (Patron) target;
	        if (!p.getCurrentLoans().isEmpty()) {
	            return false;
	        }
	    }
	    boolean removed = users.remove(target);
	    if (removed) uf.saveUsers(users);

	    return removed;
	}


    public boolean updateUser(User updatedUser) {
    	if(updatedUser == null) {return false;}
    
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(updatedUser.getUserId())) {
                users.set(i, updatedUser);
                uf.saveUsers(users);
                return true;
            }
        }
        return false;
    }
    
    public User searchUserById(String Id) {
        User result = null;
        
        if (Id == null) {return null;}
   
        for (User u : users) {
            if (u.getUserId().equalsIgnoreCase(Id)) {
                result = u;
            }
        }
        return result;
    }
    
    
    public List<User> searchUsers(String query) {
        List<User> result = new ArrayList<>();
        if (query == null || query.isBlank()) return result;

        query = query.toLowerCase();

        for (User u : users) {
            boolean matchesText =
                    u.getUserId().toLowerCase().contains(query) ||
                    u.getName().toLowerCase().contains(query) ||
                    u.getUsername().toLowerCase().contains(query) ||
                    u.getEmail().toLowerCase().contains(query);

            boolean matchesRole =
                    u.getUserType().name().toLowerCase().contains(query);

            if (matchesText || matchesRole) {
                result.add(u);
            }
        }
        return result;
    }

    
    public boolean usernameExists(String username) {
    	for(User u : users) {
    		if(u.getUsername().equals(username)) {
    			return true;
    		}
    	}
    	return false;
    }

    
    public User login(String username, String password) {
        for (User u : users) {
            if (u.authenticate(username, password)) {
                return u;
            }
        }
        return null; 
    }
    
}