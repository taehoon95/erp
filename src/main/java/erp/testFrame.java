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
import erp.ui.content.EmployeeDetailPanel;

public class testFrame extends JFrame  {

	private JPanel contentPane;
	private EmployeeTablePanel pEmpList;
	private EmployeeDetailPanel pEmployeeDetail;
	
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
		setBounds(100, 100, 445, 671);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		EmpService service = new EmpService();

		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		pEmpList = new EmployeeTablePanel();
		pEmpList.loadData();
		contentPane.add(pEmpList);
		
		pEmployeeDetail = new EmployeeDetailPanel();
		contentPane.add(pEmployeeDetail);
	}

	

}
