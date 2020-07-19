package repo;

import java.util.List;

import Models.User;

public interface IUserDAO {
	
	public List<User> findAllUsers();
	public User findById(int id);
	public User findByUsername(String username);
	public boolean addUser(User u);
	public boolean updateRole(User u);
	
	

}
