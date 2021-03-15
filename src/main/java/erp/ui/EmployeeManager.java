package erp.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmpService;
import erp.ui.content.EmployeePanel;
import erp.ui.exception.InvaildCheckException;
import erp.ui.list.EmployeeTablePanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private EmployeePanel pEmpItem;
	private EmployeeTablePanel pEmpList;
	private JButton btnSet;
	private JButton btnCancel;

	public EmployeeManager() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		EmpService service = new EmpService();

		pEmpItem = new EmployeePanel();
		pEmpItem.setService(service);
		contentPane.add(pEmpItem);

		JPanel pBtn = new JPanel();
		pEmpItem.add(pBtn, BorderLayout.SOUTH);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnSet = new JButton("Set");
		btnSet.addActionListener(this);
		pBtn.add(btnSet);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

		pEmpList = new EmployeeTablePanel();
		pEmpList.loadData();
		contentPane.add(pEmpList);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnCancel) {
				actionPerformedBtnCancel(e);
			}
			if (e.getSource() == btnSet) {
				actionPerformedBtnSet(e);
			}
			if (e.getSource() == btnAdd) {
				actionPerformedBtnAdd(e);
			}
		} catch (InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee emp = pEmpItem.getItem();
		String message = String.format("%d%n%s%ntitle(%d)%ndept(%d)%nmanager(%s)%nsalary(%d)",
				emp.getEmpNo(),
				emp.getEmpName(),
				emp.getTitle().getTno(),
				emp.getDept().getDeptNo(),
				emp.getManager().getEmpName(),
				emp.getSalary());
		JOptionPane.showMessageDialog(null, message);
	}

	protected void actionPerformedBtnSet(ActionEvent e) {
		//1003	조민희	3	4377	3000000	2
		Employee emp = new Employee(
					1003
					, "조민희"
					, new Title(3)
					,new Employee(4377)
					, 3000000
					,new Department(2));
		pEmpItem.setItem(emp);
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {

		pEmpItem.clearTf();
	}
}
