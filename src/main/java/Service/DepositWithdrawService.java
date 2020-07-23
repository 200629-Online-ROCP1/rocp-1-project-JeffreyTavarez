package Service;

import Models.Account;
import Models.DepositWithdrawDTO;

public class DepositWithdrawService {

	private static final AccountService as = new AccountService();

	public boolean deposit(DepositWithdrawDTO d) {
		Account a = as.findById(d.accountId);
		double currentBalance = a.getBalance();
		if (d.amount > 0) {
			a.setBalance(currentBalance + d.amount);
			return true;
		}
		return false;
	}

	public boolean withdraw(DepositWithdrawDTO w) {
		Account a = as.findById(w.accountId);
		double currentBalance = a.getBalance();
		if (currentBalance > w.amount) {
			a.setBalance(currentBalance - w.amount);
			return true;
		}
		return false;
	}

}
