package erp.dao;

import java.util.List;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

public interface EmployeeDao {
	List<Employee> selectEmpByAll();
	List<Employee> selectEmpByAllJoin();
	
	
	Employee selectEmpByNo(Employee employee);
	int insertEmployee(Employee employee);
	int updateEmployee(Employee employee);
	int deleteEmployee(Employee emp);
	
	List<Employee> selectEmployeeByTitle(Title title);
	List<Employee> selectEmpByDeptNo(Department dept);
}
