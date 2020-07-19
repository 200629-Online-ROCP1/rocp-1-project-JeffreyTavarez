package Service;

import java.util.List;

import Models.Role;
import repo.IRoleDAO;
import repo.RoleDAO;

public class RoleService {
	
	private final IRoleDAO rdao = new RoleDAO();
	
	public List<Role> findAll() {
		return rdao.findAll();
	}
	
	public Role findById(int id) {
		return rdao.findById(id);
	}

}
