package repo;

import java.util.List;

import Models.Account;
import Models.User;

public interface IAccountDAO {
	
	public List<Account> findAll();
	public List<Account> findByStatus(int id);
	public Account findById(int id);
	public boolean addAccount(Account a);
	public boolean updateAccount(Account a);
	public boolean accountOwner(Account a, User u);
	public List<Account> findByOwner(int id);
	public Account findLast();

}
