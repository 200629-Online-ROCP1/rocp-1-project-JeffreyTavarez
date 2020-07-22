package Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Account;
import Models.DepositWithdrawDTO;
import Models.LoginDTO;
import Models.TransferDTO;
import Models.User;
import Service.DepositWithdrawService;
import Service.TransferService;

public class TransactionController {

	public static final UserController uc = new UserController();
	public static final AccountController ac = new AccountController();
	public static final DepositWithdrawService dws = new DepositWithdrawService();
	public static final TransferService ts = new TransferService();
	private static final ObjectMapper om = new ObjectMapper();

	public void manageTransaction(HttpServletRequest req, HttpServletResponse res, HttpSession ses, String[] portions)
			throws IOException {
		LoginDTO l = (LoginDTO) ses.getAttribute("user");
		User user = uc.findByUsername(l.username);

		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = new String(s);

		switch (portions[1]) {

		case "deposit":
			DepositWithdrawDTO d = om.readValue(body, DepositWithdrawDTO.class);

			Account a = ac.findById(d.account_id);
			List<Account> alist = ac.findByOwner(user.getUserId());
			boolean permitted = false;

			for (Account ac : alist) {
				if (ac.getAccountId() == (a.getAccountId())) {
					permitted = true;
				}
			}

			if (permitted || user.getRole().getRoleId() == 4) {

				if (dws.deposit(d)) {
					res.setStatus(200);
					res.getWriter().println("$" + d.amount + " has been deposited to Account #" + d.account_id);
				}

			} else {
				res.setStatus(401);
				res.getWriter().println("The requested action is not permitted");
			}
			break;

		case "withdraw":
			DepositWithdrawDTO w = om.readValue(body, DepositWithdrawDTO.class);

			Account a = ac.findById(w.account_id);
			List<Account> alist = ac.findByOwner(user.getUserId());
			boolean permitted = false;

			for (Account ac : alist) {
				if (ac.getAccountId() == (a.getAccountId())) {
					permitted = true;
				}
			}

			if (permitted || user.getRole().getRoleId() == 4) {

				if (dws.withdraw(w)) {
					res.setStatus(200);
					res.getWriter().println("$" + w.amount + " has been withdrawn from Account #" + w.account_id);
				}

			} else {
				res.setStatus(401);
				res.getWriter().println("The requested action is not permitted");
			}
			break;

		case "transfer":
			TransferDTO t = om.readValue(body, TransferDTO.class);

			Account a = ac.findById(t.sourceAccountId);
			List<Account> alist = ac.findByOwner(user.getUserId());
			boolean permitted = false;

			for (Account ac : alist) {
				if (ac.getAccountId() == (a.getAccountId())) {
					permitted = true;
				}
			}

			if (permitted || user.getRole().getRoleId() == 4) {

				if (ts.transfer(t)) {
					res.setStatus(200);
					res.getWriter().println("$" + t.amount + " has been transferred from Account #" + t.sourceAccountId
							+ " to Account #" + t.targetAccountId);
				}

			} else {
				res.setStatus(401);
				res.getWriter().println("The requested action is not permitted");
			}
			break;

		}
	}

}
