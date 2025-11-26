package managers;
import dataModel.User;

public class AuthenticationManager {

    private UserManager userManager;

    public AuthenticationManager(UserManager um) {
        this.userManager = um;
    }

    public User login(String username, String password) {
        return userManager.login(username, password);
    }

    public boolean logout(User user) {
        return true;
    }
	
}
