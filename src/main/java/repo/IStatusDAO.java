package repo;

import java.util.List;

import Models.AccountStatus;

public interface IStatusDAO {
	public List<AccountStatus> findAll();
	public AccountStatus findById(int id);

}
