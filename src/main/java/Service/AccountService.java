package Service;

import java.util.List;

import Models.Account;
import repo.AccountDAO;
import repo.IAccountDAO;

public class AccountService {

	private final IAccountDAO adao = new AccountDAO();
	
	public List<Account> findAll() {
		return adao.findAll();
	}
	
	public Account findById(int id) {
		return adao.findById(id);
	}
	
	public boolean addAccount(Account a) {
		return adao.addAccount(a);
	}
	
	
	
}
