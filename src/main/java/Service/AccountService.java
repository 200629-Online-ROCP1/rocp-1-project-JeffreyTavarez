package Service;

import java.util.List;

import Models.Account;
import Models.User;
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
	
	public boolean updateAccount(Account a) {
		return adao.updateAccount(a);
	}

	public boolean accountOwner(Account a, User u) {
		return adao.accountOwner(a, u);
	}

	public List<Account> findByStatus(int id) {
		return adao.findByStatus(id);
	}

	public List<Account> findByOwner(int id) {
		return adao.findByOwner(id);
	}
	
	
}
