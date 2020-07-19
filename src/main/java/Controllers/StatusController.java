package Controllers;

import java.util.List;

import Models.AccountStatus;
import Service.StatusService;

public class StatusController {
	
	private final StatusService ss = new StatusService();
	
	public List<AccountStatus> findAll() {
		return ss.findAll();
	}
	
	public AccountStatus findById(int id) {
		return ss.findById(id);
	}

}
