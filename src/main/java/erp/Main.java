package erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.ui.DeptManager;
import erp.ui.TitleManager;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnTitle;
	private JButton btnDepartment;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Main() {
		initialize();
	}
	private void initialize() {
		setTitle("erp management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 85);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnTitle = new JButton("직책 관리");
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);
		
		btnDepartment = new JButton("부서 관리");
		btnDepartment.addActionListener(this);
		contentPane.add(btnDepartment);
		
		JButton btnEmployee = new JButton("사원 관리");
		contentPane.add(btnEmployee);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDepartment) {
			actionPerformedBtnDepartment(e);
		}
		if (e.getSource() == btnTitle) {
			actionPerformedBtnTitle(e);
		}
	}
	protected void actionPerformedBtnTitle(ActionEvent e) {
		TitleManager frame = new TitleManager();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnDepartment(ActionEvent e) {
		DeptManager frame = new DeptManager();
		frame.setVisible(true);
	}
}
