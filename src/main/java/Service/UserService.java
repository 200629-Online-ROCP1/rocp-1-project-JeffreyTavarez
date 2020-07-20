package Service;

import java.util.List;

import Models.User;
import repo.IUserDAO;
import repo.UserDAO;

public class UserService {
	
	private final IUserDAO udao = new UserDAO();
	
	public List<User> findAllUsers() {
		return udao.findAllUsers();
	}
	
	public User findById(int id) {
		return udao.findById(id);
	}
	
	public User findByUsername(String username) {
		return udao.findByUsername(username);
	}
	
	public boolean addUser(User u) {
		List<User> list = findAllUsers();
		
		for(User us: list) {
			if(us.getUsername().equals(u.getUsername()) && us.getFirstName().equals(u.getFirstName()) && us.getLastName().equals(u.getLastName()) && us.getEmail().equals(u.getEmail())) {
				return false;
			}
		}
	
		return udao.addUser(u);
	}
	
}
