package repo;

import java.util.List;

import Models.Account;

public interface IAccountDAO {
	
	public List<Account> findAll();
	public List<Account> findByStatus(int id);
	public Account findById(int id);
	public boolean addAccount(Account a);
	public boolean updateAccount(Account a);

}
