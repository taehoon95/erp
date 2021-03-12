package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Department;
import erp.service.DeptService;

public class DepartmentTablePanel extends AbstractCustomTablePanel<Department> {

	private DeptService service = new DeptService();
	
	@Override
	public void initList() {
		list = service.showDept();
	}

	@Override
	protected void setAlignAndWidth() {
		//컬럼내용 정렬 
		 setTableCellAlign(SwingConstants.CENTER,  1); 
		 setTableCellAlign(SwingConstants.RIGHT, 0, 2); 
		 
		 //컬럼별 너비 조정
		 setTableCellWidth(100, 250, 100);
	}

	@Override
	public Object[] toArray(Department t) {
		Object[] T = {t.getDeptNo(),t.getDeptName(),t.getFloor()};
		return T;
	}

	@Override
	public String[] getColumnNames() {
		
		return new String[] {"부서번호","부서명","위치"};
	}

	public void setService(DeptService service) {
		this.service = service;
	}
}
