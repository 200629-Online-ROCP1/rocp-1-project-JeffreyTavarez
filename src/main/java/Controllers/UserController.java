package Controllers;

import java.util.List;

import Models.User;
import Service.UserService;

public class UserController {
	
	private final UserService us = new UserService();
	
	public List<User> findAllUsers(){
		return us.findAllUsers();
	}
	
	public User findById(int id) {
		return us.findById(id);
	}
	
	public User findByUsername(String username) {
		return us.findByUsername(username);
	}
	
	public boolean addUser(User u) {
		return us.addUser(u);
	}

}
