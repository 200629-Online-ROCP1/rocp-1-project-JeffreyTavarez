package repo;

import java.util.List;

import Models.Role;

public interface IRoleDAO {
	public List<Role> findAll();
	public Role findById(int id);


}
