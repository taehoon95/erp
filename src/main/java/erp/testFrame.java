package erp;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.service.EmpDetailService;
import erp.service.EmpService;
import erp.ui.content.EmployeeDetailPanel;
import erp.ui.list.EmployeeTablePanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class testFrame extends JFrame implements ActionListener  {

	private JPanel contentPane;
	private EmployeeTablePanel pEmpList;
	private EmployeeDetailPanel pEmployeeDetail;
	private JPanel panel;
	private JButton btnGet;
	private JButton btnSet;
	
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
		setBounds(100, 100, 445, 784);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		EmpService service = new EmpService();
		
		pEmpList = new EmployeeTablePanel();
		pEmpList.loadData();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(pEmpList);
		
		pEmployeeDetail = new EmployeeDetailPanel();
		pEmployeeDetail.setTfEmpno(new Employee(1003));
		contentPane.add(pEmployeeDetail);
		
		panel = new JPanel();
		
		pEmployeeDetail.add(panel, BorderLayout.SOUTH);
		
		btnGet = new JButton("\uAC00\uC838\uC624\uAE30");
		btnGet.addActionListener(this);
		panel.add(btnGet);
		
		btnSet = new JButton("\uBD88\uB7EC\uC624\uAE30");
		btnSet.addActionListener(this);
		panel.add(btnSet);
	}
	
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSet) {
			actionPerformedBtnSet(e);
		}
		if (e.getSource() == btnGet) {
			actionPerformedBtnGet(e);
		}
	}
	
	protected void actionPerformedBtnGet(ActionEvent e) {
		EmployeeDetail employeeDetail = pEmployeeDetail.getItem();
		JOptionPane.showMessageDialog(null, employeeDetail);
	}
	protected void actionPerformedBtnSet(ActionEvent e) {
		EmpDetailService service = new EmpDetailService();
		EmployeeDetail empDetail = service.showEmployeeDetail(new Employee(1003));
		pEmployeeDetail.setItem(empDetail);
	}
}
