package repo;

import java.util.List;

import Models.AccountType;

public interface ITypeDAO {
	public List<AccountType> findAll();
	public AccountType findById(int id);

}
