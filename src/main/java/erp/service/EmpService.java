package erp.service;

import java.util.List;

import erp.dao.DepartmentDao;
import erp.dao.EmployeeDao;
import erp.dao.TitleDao;
import erp.dao.Impl.DepartmentDaoImpl;
import erp.dao.Impl.EmployeeDaoImpl;
import erp.dao.Impl.TitleDaoimpl;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

public class EmpService {
	private EmployeeDao EmpDao = EmployeeDaoImpl.getInstance();
	private DepartmentDao DeptDao = DepartmentDaoImpl.getInstance();
	private TitleDao TitleDao = TitleDaoimpl.getInstance();
	
	public List<Employee> showEmployee() {
		return EmpDao.selectEmpByAllJoin();
	}
	
	public List<Department> showDeptList(){
		return DeptDao.selectDeptByAll();
	}
	
	public List<Title> showTitleList(){
		return TitleDao.selectTitleByAll();
	}
	
	public List<Employee> showEmployeeByDept(Department dept){
		return EmpDao.selectEmpByDeptNo(dept);
	}
	
	public void removeEmployee(Employee emp) {
		EmpDao.deleteEmployee(emp);
	}
	
	public void modifiEmployee(Employee emp) {
		EmpDao.updateEmployee(emp);
	}
	
	public void addEmployee(Employee emp) {
		EmpDao.insertEmployee(emp);
	}
}
