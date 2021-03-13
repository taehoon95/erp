package erp.service;

import java.util.List;

import erp.dao.EmployeeDao;
import erp.dao.TitleDao;
import erp.dao.Impl.EmployeeDaoImpl;
import erp.dao.Impl.TitleDaoimpl;
import erp.dto.Employee;
import erp.dto.Title;


public class TitleService {
	private TitleDao dao = TitleDaoimpl.getInstance();
	private EmployeeDao eDao = EmployeeDaoImpl.getInstance();
	
	
	public List<Title> showTitles(){
		return dao.selectTitleByAll();
	}
	
	public void addTitle(Title title) {
		dao.insertTitle(title);
	}
	
	public void removeTitle(Title title) {
		dao.deleteTitle(title.getTno());
	}
	
	public void modifyTitle(Title title) {
		dao.updateTitle(title);
	}

	public List<Employee> empSelectByTitle(Title title) {
		 return eDao.selectEmployeeByTitle(title);
	}

	
}
