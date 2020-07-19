package Controllers;

import java.util.List;

import Models.Account;
import Service.AccountService;

public class AccountController {
	
	private final AccountService as = new AccountService();
	
	public List<Account> findAll() {
		return as.findAll();
	}
	
	public Account findById(int id) {
		return as.findById(id);
	}
	
	public boolean addAccount(Account a) {
		return as.addAccount(a);
	}

}
