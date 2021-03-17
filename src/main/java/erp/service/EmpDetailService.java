package erp.service;

import erp.dao.EmployeeDetailDao;
import erp.dao.Impl.EmployeeDetailDaoImpl;
import erp.dto.Employee;
import erp.dto.EmployeeDetail;

public class EmpDetailService {
	private EmployeeDetailDao dao = EmployeeDetailDaoImpl.getInstance();
	
	public EmployeeDetail showEmployeeDetail(Employee employee) {
		return dao.selectEmployeeDetailByNo(employee);
	}
	
	public void insertEmployeeDetail(EmployeeDetail empDetail) {
		dao.insertEmployeeDetail(empDetail);
	}
	
	public void updateEmployeeDetail(EmployeeDetail empDetail) {
		dao.updateEmployeeDetail(empDetail);
	}
	
	public void deleteEmployeeDetail(Employee employee) {
		dao.deleteEmployeeDetail(employee);
	}
}
