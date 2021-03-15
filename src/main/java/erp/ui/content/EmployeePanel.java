package erp.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmpService;
import erp.ui.exception.InvaildCheckException;


public class EmployeePanel extends InterfaceItem<Employee> implements ItemListener {
	private JTextField tfNo;
	private JTextField tfName;
	private JComboBox<Title> cmbTitle;
	private JComboBox<Employee> cmbManager;
	private JSpinner spinnerSalary;
	private JComboBox<Department> cmbDept;
	private EmpService service;
	
	
	public EmployeePanel() {
		initialize();
	}
	
	@Override
	public void initialize() {
		setBorder(new TitledBorder(null, "사원정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);
		
		JPanel pPic = new JPanel();
		pTop.add(pPic);
		pPic.setLayout(new BorderLayout(0, 0));
		
		JLabel lblpic = new JLabel("");
		lblpic.setHorizontalAlignment(SwingConstants.CENTER);
		lblpic.setIcon(new ImageIcon("C:\\workspace_java\\swingStudy\\images\\dog22.png"));
		pPic.add(lblpic, BorderLayout.CENTER);
		
		JPanel pItem = new JPanel();
		add(pItem, BorderLayout.CENTER);
		pItem.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNo = new JLabel("사원번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblNo);
		
		tfNo = new JTextField();
		pItem.add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("사원명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		pItem.add(tfName);
		
		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblDept);
		
		cmbDept = new JComboBox<>();
		cmbDept.addItemListener(this);
		pItem.add(cmbDept);
		
		JLabel lblManager = new JLabel("직속상사");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblManager);
		
		cmbManager = new JComboBox<>();
		pItem.add(cmbManager);
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblTitle);
		
		cmbTitle = new JComboBox<>();
		pItem.add(cmbTitle);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblSalary);
		
		spinnerSalary = new JSpinner();
		spinnerSalary.setModel(new SpinnerNumberModel(2000000, 1500000, 5000000, 100000));
		pItem.add(spinnerSalary);
		
	}
	
	



	
	public void setService(EmpService service) {
		this.service = service;
		
		List<Department> deptList = service.showDeptList();
		DefaultComboBoxModel deptModel = new DefaultComboBoxModel<Department>(new Vector<>(deptList));
		cmbDept.setModel(deptModel);
		
		List<Title> titleList = service.showTitleList();
		DefaultComboBoxModel titleModel = new DefaultComboBoxModel<Title>(new Vector<>(titleList));
		cmbTitle.setModel(titleModel);
		
		cmbDept.setSelectedIndex(-1);
		cmbTitle.setSelectedIndex(-1);
		
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbDept) {
			itemStateChangedCmbDept(e);
		}
	}
	protected void itemStateChangedCmbDept(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			Department dept = (Department) cmbDept.getSelectedItem();
			List<Employee> empByDeptList = service.showEmployeeByDept(dept);
			
			//직속 상사가 없는 경우 추가
			if(empByDeptList == null) {
				empByDeptList = new ArrayList<>();
			}
			
			DefaultComboBoxModel model = new DefaultComboBoxModel<Employee>(new Vector<>(empByDeptList));
			cmbManager.setModel(model);
			cmbManager.setSelectedIndex(-1);
		}
	}

	@Override
	public void setItem(Employee item) {
		tfNo.setText(item.getEmpNo()+"");
		tfName.setText(item.getEmpName());
		cmbDept.setSelectedItem(item.getDept());
		cmbManager.setSelectedItem(item.getManager());
		cmbTitle.setSelectedItem(item.getTitle());
		spinnerSalary.setValue(item.getSalary());
		
	}

	@Override
	public Employee getItem() {
		validCheck();
		int empNo = Integer.parseInt(tfNo.getText().trim());
		String empName = tfName.getText().trim();
		Title title = (Title) cmbTitle.getSelectedItem();
		Employee manager = (Employee) cmbManager.getSelectedItem();
		int salary = (int) spinnerSalary.getValue();
		Department dept = (Department) cmbDept.getSelectedItem();
		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	@Override
	public void validCheck() {
		if(tfNo.getText().equals("") || tfName.getText().equals("") ||
				cmbDept.getSelectedItem().equals("") || cmbManager.getSelectedItem().equals("") ||
				cmbTitle.getSelectedItem().equals("") || spinnerSalary.getValue().equals("")) {
			throw new InvaildCheckException();
		}
	}
	
	@Override
	public void clearTf() {
		tfNo.setText("");
		tfName.setText("");
		cmbDept.setSelectedItem(null);
		cmbManager.setSelectedItem(null);
		cmbTitle.setSelectedItem(null);
		spinnerSalary.setValue(2000000);
	}
}
