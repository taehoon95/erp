package erp.dao;

import erp.dto.Employee;
import erp.dto.EmployeeDetail;

public interface EmployeeDetailDao {
	EmployeeDetail selectEmployeeDetailByNo(Employee employee);
	
	int insertEmployeeDetail(EmployeeDetail empDetail);
	int updateEmployeeDetail(EmployeeDetail empDetail);
	int deleteEmployeeDetail(Employee empDetail);
	
}
