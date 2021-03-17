package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmpService;
import erp.service.TitleService;
import erp.ui.exception.NotSelectedExeption;
import erp.ui.list.AbstractCustomTablePanel;

public class EmployeeTablePanel extends AbstractCustomTablePanel<Employee> {
	public EmployeeTablePanel() {
	}

	private EmpService service = new EmpService();

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 5);
		setTableCellAlign(SwingConstants.RIGHT, 4);

		// 컬럼별 너비 조정
		setTableCellWidth(100, 100, 100, 150, 100, 100);
	}

	@Override
	public Object[] toArray(Employee t) {
		
		return new Object[] { 
				t.getEmpNo()
				,t.getEmpName()
				,String.format("%s(%d)", t.getTitle().getTname(),t.getTitle().getTno())
				,t.getManager().getEmpNo()==0?"":String.format("%s(%d)", t.getManager().getEmpName(), t.getManager().getEmpNo())
				,t.getSalary()
				,String.format("%s(%d)", t.getDept().getDeptName(),t.getDept().getDeptNo())
				};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "사원번호", "사원명", "직책", "직속상사", "급여", "부서" };
	}

	@Override
	public void initList() {
		list = service.showEmployee();
	}

	public void setService(EmpService service) {
		this.service = service;
	}

	@Override
	public Employee getItem() {
		int row = table.getSelectedRow();
		int empNo = (int) table.getValueAt(row, 0);
		
		if(row == -1) {
			throw new NotSelectedExeption();
		}
		
		return list.get(list.indexOf(new Employee(empNo)));
	}
}
