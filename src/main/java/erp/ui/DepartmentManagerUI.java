package erp.ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import erp.dto.Department;
import erp.dto.Employee;
import erp.service.DeptService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.DeptPanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.DepartmentTablePanel;

@SuppressWarnings("serial")
public class DepartmentManagerUI extends AbstractManagerUI<Department> {

	private DeptService service;
	@Override
	protected void setService() {
		service = new DeptService();
	}

	@Override
	protected void tableLoadData() {
		((DepartmentTablePanel)pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<Department> createContentPanel() {
		return new DeptPanel();
	}

	@Override
	protected AbstractCustomTablePanel<Department> createTablePanel() {
		return new DepartmentTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		Department selectEmpByDept = pList.getItem(); 
		List<Employee> empList = service.selectEmpByDeptNo(selectEmpByDept);
		if(empList == null) {
			JOptionPane.showMessageDialog(null, "해당사원이 존재하지 않습니다.","동일 부서 사원",JOptionPane.WARNING_MESSAGE);
			return;
		}
		List<String> strList = empList
				.parallelStream()
				.map(s -> {return String.format("%s %d", s.getEmpName(),s.getEmpNo());})
				.collect(Collectors.toList());
		JOptionPane.showMessageDialog(null, strList, "동일 부서 사원", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		btnAdd.setText("수정");
		
		Department modiDept = pList.getItem(); 
		pContent.setItem(modiDept);
		((DeptPanel) pContent).getTfDeptNo().setEditable(false);
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Department delDept = pList.getItem();
		service.delDept(delDept);
		JOptionPane.showMessageDialog(null,delDept + "삭제 되었습니다.");
		pList.loadData();
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Department dept = pContent.getItem();
		service.modiDept(dept);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, dept +" 로 수정되었습니다");
		btnAdd.setText("추가");
		((DeptPanel) pContent).getTfDeptNo().setEditable(true);
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department dept = pContent.getItem();
		service.addDept(dept);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, dept + "추가되었습니다.");
	}

}
