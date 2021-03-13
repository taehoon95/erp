package erp.service;

import java.util.List;

import erp.dao.EmployeeDao;
import erp.dao.Impl.EmployeeDaoImpl;
import erp.dto.Employee;

public class EmpService {
	private EmployeeDao dao = EmployeeDaoImpl.getInstance();

	public List<Employee> showEmployee() {
		return dao.selectEmpByAll();
	}
}
