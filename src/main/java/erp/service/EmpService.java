package erp.service;

import erp.dao.EmployeeDao;
import erp.daoImpl.EmployeeDaoImpl;
import erp.dto.Title;

public class EmpService {
	private EmployeeDao dao = EmployeeDaoImpl.getInstance();

	public void selectEmployeeByTitle(Title title) {
		dao.selectEmployeeByTitle(title);
	}
}
