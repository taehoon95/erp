package erp;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class testFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private EmployeePanel pEmpItem;
	private JButton btnSet;
	private EmployeeTablePanel pEmpList;
	private JButton btnCancel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testFrame frame = new testFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public testFrame() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		EmpService service = new EmpService();
		pEmpItem = new EmployeePanel();
		pEmpItem.setService(service);
		contentPane.add(pEmpItem);
		
		JPanel panel = new JPanel();
		pEmpItem.add(panel, BorderLayout.SOUTH);
		
		btnAdd = new JButton(" \uCD94\uAC00");
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		
		btnCancel = new JButton("\uCDE8\uC18C");
		btnCancel.addActionListener(this);
		panel.add(btnCancel);
		
		btnSet = new JButton("set");
		btnSet.addActionListener(this);
		panel.add(btnSet);

		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		pEmpList = new EmployeeTablePanel();
		pEmpList.loadData();
		contentPane.add(pEmpList);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		try {
			if (e.getSource() == btnSet) {
				actionPerformedBtnSet(e);
			}
			if (e.getSource() == btnAdd) {
				actionPerformedBtnAdd(e);
			}	
		}catch (InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee emp = pEmpItem.getItem();
		String message = String.format("%d%n%s%ntitle(%d)%ndept(%d)%nmanager(%s)%nsalary(%d)"
				,emp.getEmpNo()
				,emp.getEmpName()
				,emp.getTitle().getTno()
				,emp.getDept().getDeptNo()
				,emp.getManager().getEmpName()
				,emp.getSalary());
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