package Service;

import Models.DepositWithdrawDTO;
import Models.TransferDTO;

public class TransferService {
	
	private static final DepositWithdrawService dws = new DepositWithdrawService();

	public boolean transfer(TransferDTO t) {
		DepositWithdrawDTO w = new DepositWithdrawDTO();
		w.account_id = t.sourceAccountId;
		w.amount = t.amount;
	
		DepositWithdrawDTO d = new DepositWithdrawDTO();
		d.account_id = t.targetAccountId;
		d.amount = t.amount;
	
		if( dws.withdraw(w) && dws.deposit(d)) {
			return true;
		}
		return false;
	}

}
