package Controllers;

import java.util.List;

import Models.Role;
import Service.RoleService;

public class RoleController {
	
	private final RoleService rs = new RoleService();
	
	public List<Role> findAll() {
		return rs.findAll();
	}
	
	public Role findById(int id) {
		return rs.findById(id);
	}

}
