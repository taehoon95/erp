package erp.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp.dto.Department;

public class DeptPanel extends JPanel {
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;

	public JTextField getTfDeptNo() {
		return tfDeptNo;
	}
	public void setTfDeptNo(JTextField tfDeptNo) {
		this.tfDeptNo = tfDeptNo;
	}
	public JTextField getTfDeptName() {
		return tfDeptName;
	}
	public void setTfDeptName(JTextField tfDeptName) {
		this.tfDeptName = tfDeptName;
	}
	public JTextField getTfFloor() {
		return tfFloor;
	}
	public void setTfFloor(JTextField tfFloor) {
		this.tfFloor = tfFloor;
	}
	public DeptPanel() {
		initialize();
	}
	public Department getDepartment() {
		int deptNo = Integer.parseInt(tfDeptNo.getText().trim());
		String deptName =(tfDeptName.getText().trim());
		int floor = Integer.parseInt(tfFloor.getText().trim());
		return new Department(deptNo, deptName, floor);
	}
	
	public void setDepartment(Department dept) {
//		tfDeptNo.setText(String.valueof(dept.getDeptNo())));
		tfDeptNo.setText(dept.getDeptNo() + "");
		tfDeptName.setText(dept.getDeptName());
		tfFloor.setText(dept.getFloor() + "");
	}
	
	public void ClearTf() {
//		tfDeptNo.setText(String.valueof(dept.getDeptNo())));
		tfDeptNo.setText("");
		tfDeptName.setText("");
		tfFloor.setText("");
	}
	

	
	private void initialize() {
		setBorder(new TitledBorder(null, "부서 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblDeptNo = new JLabel("부서번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptNo);
		
		tfDeptNo = new JTextField();
		add(tfDeptNo);
		tfDeptNo.setColumns(10);
		
		JLabel lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptName);
		
		tfDeptName = new JTextField();
		tfDeptName.setColumns(10);
		add(tfDeptName);
		
		JLabel lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFloor);
		
		tfFloor = new JTextField();
		tfFloor.setColumns(10);
		add(tfFloor);
	}

}
