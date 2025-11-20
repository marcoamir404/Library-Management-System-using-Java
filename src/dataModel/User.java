package dataModel;

import java.util.ArrayList;
import enums.UserType;

public abstract class User {
	
	public static ArrayList<User> users = new ArrayList<>();
	
	protected String userId;
	protected String username;
	protected String password;
	protected String name;
	protected String email;
	protected String phone;
	protected UserType userType;
	
	public User() {}
	
	public User(String userId, String username, String password, String name, String email, String phone, UserType userType) {
		setUserId(userId);
		setUsername(username);
		setPassword(password);
		setName(name);
		setEmail(email);
		setPhone(phone);
		setUserType(userType);
	}
	
	public String getUserId() { return userId;}
	public void setUserId(String userId) { this.userId = userId;}
	public String getUsername() { return username;}
	public void setUsername(String username) { this.username = username;}
	public String getPassword() { return password;}
	public void setPassword(String password) { this.password = password;}
	public String getName() { return name;}
	public void setName(String name) { this.name = name;}
	public String getEmail() { return email;}
	public void setEmail(String email) { this.email = email;}
	public String getPhone() { return phone;}
	public void setPhone(String phone) { this.phone = phone;}
	public UserType getUserType() {return userType;}
	public void setUserType(UserType userType) {this.userType = userType; }
	
	public void updateContactInfo(String email, String phone) {
		setEmail(email);
		setPhone(phone);
	}
	
	public boolean authenticate(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
		
	}
}
