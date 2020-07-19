package Service;

import java.util.List;

import Models.AccountStatus;
import repo.IStatusDAO;
import repo.StatusDAO;

public class StatusService {
	
	private final IStatusDAO sdao = new StatusDAO();
	
	public List<AccountStatus> findAll() {
		return sdao.findAll();		
	}
	
	public AccountStatus findById(int id) {
		return sdao.findById(id);
	}

}
