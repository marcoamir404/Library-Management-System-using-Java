package managers;

import java.util.ArrayList;
import java.util.List;

import dataModel.User;
import dataModel.Librarian;
import dataModel.Patron;
import enums.UserType;
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
    	if(userId == null) { return false;}
    	boolean removed = users.removeIf(u -> u.getUserId().equals(userId));
        if(removed) {uf.saveUsers(users);}
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
    
    public Librarian searchLibrarianById(String libId) {
        Librarian result = null;
        
        if (libId == null) {return null;}
   
        for (User u : users) {
            if (u.getUserId().equalsIgnoreCase(libId)&& u.getUserType() == UserType.LIBRARIAN) {
                result = (Librarian) u;
            }
        }
        return result;
    }
    
    public Patron searchPatronById(String patId) {
        Patron result = null;
        
        if (patId == null) {return null;}
        
        for (User u : users) {
            if (u.getUserId().equalsIgnoreCase(patId) && u.getUserType() == UserType.PATRON) {
                result = (Patron) u;
            }
        }
        return result;
    }
    
    public List<User> searchLibrarian(String query) {
        List<User> result = new ArrayList<>();
        
        if (query == null) {return null;}
        
        query = query.toLowerCase();
        for (User u : users) {
            if ((u.getUserId().toLowerCase().contains(query)||
            	u.getName().toLowerCase().contains(query) ||
                u.getUsername().toLowerCase().contains(query) ||
                u.getEmail().toLowerCase().contains(query))&& u.getUserType() == UserType.LIBRARIAN) {
                result.add(u);
            }else if(u.getUserType() == UserType.LIBRARIAN &&
            		UserType.LIBRARIAN.toString().contains(query.toUpperCase())) {
            	result.add(u);
            }
        }
        return result;
    }
    
    public List<User> searchPatron(String query) {
        List<User> result = new ArrayList<>();
        
        if (query == null) {return null;}
        
        query = query.toLowerCase();
        for (User u : users) {
            if ((u.getUserId().toLowerCase().contains(query)||
            	u.getName().toLowerCase().contains(query) ||
                u.getUsername().toLowerCase().contains(query) ||
                u.getEmail().toLowerCase().contains(query)) && u.getUserType() == UserType.PATRON) {
                result.add(u);
            }else if(u.getUserType() == UserType.PATRON &&
            		UserType.PATRON.toString().contains(query.toUpperCase())) {
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