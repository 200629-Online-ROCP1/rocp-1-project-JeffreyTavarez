package Service;

import java.util.List;

import Models.AccountType;
import repo.ITypeDAO;
import repo.TypeDAO;

public class TypeService {
	
	private final ITypeDAO tdao = new TypeDAO();
	
	public List<AccountType> findAll() {
		return tdao.findAll();
	}
	
	public AccountType findById(int id) {
		return tdao.findById(id);
	}

}
