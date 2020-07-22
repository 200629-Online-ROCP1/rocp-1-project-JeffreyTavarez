package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Account;
import Models.AccountStatus;
import Models.AccountType;
import Models.User;
import Util.ConnectionUtil;

public class AccountDAO implements IAccountDAO {

	private static final IStatusDAO sdao = new StatusDAO();
	private static final ITypeDAO tdao = new TypeDAO();

	@Override
	public List<Account> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account;";

			Statement statement = conn.createStatement();

			List<Account> list = new ArrayList<>();

			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Account a = new Account();
				a.setAccountId(result.getInt("account_id"));
				a.setBalance(result.getInt("balance"));
				AccountStatus as = sdao.findById(result.getInt("status"));
				a.setStatus(as);
				AccountType at = tdao.findById(result.getInt("type"));
				a.setType(at);

				list.add(a);
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Account findById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account WHERE account_id = " + id + ";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			if (result.next()) {
				Account a = new Account();
				a.setAccountId(result.getInt("account_id"));
				a.setBalance(result.getInt("balance"));
				AccountStatus as = sdao.findById(result.getInt("status"));
				a.setStatus(as);
				AccountType at = tdao.findById(result.getInt("type"));
				a.setType(at);

				return a;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Account> findByStatus(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account WHERE status = " + id + ";";

			Statement statement = conn.createStatement();

			List<Account> list = new ArrayList<>();

			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Account a = new Account();
				a.setAccountId(result.getInt("account_id"));
				a.setBalance(result.getInt("balance"));
				AccountStatus as = sdao.findById(result.getInt("status"));
				a.setStatus(as);
				AccountType at = tdao.findById(result.getInt("type"));
				a.setType(at);

				list.add(a);
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
		

	@Override
	public boolean addAccount(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			int index = 0;

			String sql = "INSERT INTO account(balance, status, type)" + " VALUES(?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(++index, a.getBalance());
			statement.setInt(++index, 1);
			statement.setInt(++index, a.getType().getTypeId());

			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateAccount(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE account SET balance = ?, status = ?, type =? WHERE account_id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			int index = 0;
			
			statement.setDouble(++index, a.getBalance());
			statement.setInt(++index, a.getStatus().getStatusId());
			statement.setInt(++index, a.getType().getTypeId());
			statement.setInt(++index, a.getAccountId());

			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean accountOwner(Account a, User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO account_owner(account_id, user_id) VALUES(?,?);";

			PreparedStatement statement = conn.prepareStatement(sql);
			int index = 0;
			
			statement.setInt(++index, a.getAccountId());
			statement.setInt(++index, u.getUserId());

			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> findByOwner(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM account_owner WHERE user_id = " + id + ";";

			Statement statement = conn.createStatement();

			List<Account> list = new ArrayList<>();

			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Account a = findById(result.getInt("account_id"));
				list.add(a);
			}

			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
