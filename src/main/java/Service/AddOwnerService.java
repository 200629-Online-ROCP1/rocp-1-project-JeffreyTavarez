package Service;

import Models.Account;
import Models.AddOwnerDTO;
import Models.DepositWithdrawDTO;
import Models.User;

public class AddOwnerService {
	
	private final AccountService as = new AccountService();
	private final UserService us = new UserService();
	private final DepositWithdrawService dps = new DepositWithdrawService();
	private final DepositWithdrawDTO dwdto = new DepositWithdrawDTO();
	
	public boolean AddOwner(AddOwnerDTO ao) {
		Account a = as.findById(ao.accountId);
		User u = us.findById(ao.userId);
		if (as.accountOwner(a, u)) {
			dwdto.accountId = a.getAccountId();
			dwdto.accountId = 50;
			dps.withdraw(dwdto);
			return true;
		} else {
			return false;
		}
	}

}
