package erp.service;

import java.util.List;

import erp.dao.DepartmentDao;
import erp.dao.EmployeeDao;
import erp.dao.Impl.DepartmentDaoImpl;
import erp.dao.Impl.EmployeeDaoImpl;
import erp.dto.Department;
import erp.dto.Employee;

public class DeptService {
	private DepartmentDao dao = DepartmentDaoImpl.getInstance();
	private EmployeeDao eDao = EmployeeDaoImpl.getInstance();
	
	public List<Department> showDept(){
		return dao.selectDeptByAll();
	}
	
	public void addDept(Department dept){
		dao.insertDepartment(dept);
	}
	
	public void delDept(Department dept) {
		dao.deleteDepartment(dept.getDeptNo());
	}
	
	public void modiDept(Department dept) {
		dao.updateDepartment(dept);
	}
	
	public List<Employee> selectEmpByDeptNo(Department dept) {
		return eDao.selectEmpByDeptNo(dept);
	}
}
