package erp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.service.EmpDetailService;
import erp.ui.content.EmployeeDetailPanel;

public class EmployeeDetailUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pBtns;
	private JButton btnAdd;
	private EmployeeDetailPanel pItem;

	private EmpDetailService service;
	private JButton btnCancel;

	public EmployeeDetailUI(boolean isBtns, EmpDetailService service) {
		this.service = service;
		initialize(isBtns);
	}

	private void initialize(boolean isBtns) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		pBtns = new JPanel();

		contentPane.add(pBtns, BorderLayout.SOUTH);

		btnAdd = new JButton();
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnCancel = new JButton();
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);

		if (isBtns) {
			btnAdd.setText("추가");
			btnCancel.setText("취소");
		} else {
			btnAdd.setText("수정");
			btnCancel.setText("삭제");
		}

		pItem = new EmployeeDetailPanel();
		contentPane.add(pItem, BorderLayout.CENTER);
	}

	public void setEmpno(Employee empNo) {
		pItem.setTfEmpno(empNo);
	}

	public void setDetailItem(EmployeeDetail empDetail) {
//		btnAdd.setText("수정");
		pItem.setItem(empDetail);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("삭제")) {
			actionPerformedBtnRemove(e);
		}
		if (e.getActionCommand().contentEquals("취소")) {
			actionPerformedBtnCancel(e);
		}
		if (e.getActionCommand().contentEquals("추가")) {
			actionPerformedBtnAdd(e);
		}
		if (e.getActionCommand().contentEquals("수정")) {
			actionPerformedBtnUpdate(e);
		}
	}

	private void actionPerformedBtnUpdate(ActionEvent e) {
		EmployeeDetail updateEmpDetail = pItem.getItem();
		service.updateEmployeeDetail(updateEmpDetail);
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "수정 완료");
		dispose();
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		/*
		 * 1. 입력된 empdetail 가져오기 2. service에 적용
		 */
		EmployeeDetail newEmpDetail = pItem.getItem();
		service.insertEmployeeDetail(newEmpDetail);
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "추가 완료");
		dispose();
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pItem.clearTf();
		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}

	protected void actionPerformedBtnRemove(ActionEvent e) {
		EmployeeDetail empDetail = pItem.getItem();
		service.deleteEmployeeDetail(new Employee(empDetail.getEmpNO()));
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "삭제 완료");
	}

	////////////////////////////////////////////////////////////

}
