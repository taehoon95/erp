package erp.service;

import java.util.List;

import erp.dao.DepartmentDao;
import erp.daoImpl.DepartmentDaoImpl;
import erp.dto.Department;

public class DeptService {
	private DepartmentDao dao = DepartmentDaoImpl.getInstance();
	
	public List<Department> showDept(){
		return dao.selectDeptByAll();
	}
}
