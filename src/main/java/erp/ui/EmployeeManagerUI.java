package erp.ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import erp.dto.Employee;
import erp.service.EmpService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.EmployeePanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.EmployeeTablePanel;

public class EmployeeManagerUI extends AbstractManagerUI<Employee> {
	
	public EmployeeManagerUI() {
		empListByTitleItem.setText(AbstractManagerUI.EMP_MENU);
	}

	private EmpService service;

	@Override
	protected void setService() {
		service = new EmpService();
	}

	@Override
	protected void tableLoadData() {
		((EmployeeTablePanel) pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<Employee> createContentPanel() {
		EmployeePanel empPanel = new EmployeePanel();
		empPanel.setService(service);
		return empPanel;
	}

	@Override
	protected AbstractCustomTablePanel<Employee> createTablePanel() {
		return new EmployeeTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		throw new UnsupportedOperationException("제공되지 않음");
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		
		Employee updateEmp = pList.getItem();
		pContent.setItem(updateEmp);
		btnAdd.setText("수정");
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Employee delEmp = pList.getItem();
		service.removeEmployee(delEmp);
		JOptionPane.showMessageDialog(null,delEmp + "삭제 되었습니다.");
		pList.loadData();
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Employee updateEmp = pContent.getItem();
		service.modifiEmployee(updateEmp);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, updateEmp.getEmpName() +" 로 수정되었습니다");
		btnAdd.setText("추가");
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee emp = pContent.getItem();
		service.addEmployee(emp);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, emp.getEmpName() + "추가되었습니다.");
	}

}
