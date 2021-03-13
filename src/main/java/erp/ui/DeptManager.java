package erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.service.DeptService;
import erp.ui.content.DeptPanel;
import erp.ui.exception.InvaildCheckException;
import erp.ui.exception.NotSelectedExeption;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.DepartmentTablePanel;

public class DeptManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private DeptPanel pContent;
	private JPanel pBtn;
	private DepartmentTablePanel pList;
	private JButton btnAdd;
	private JButton btnCancel;
	private DeptService service;
	
	public DeptManager() {
		service = new DeptService();
		initialize();
	}
	private void initialize() {
		setTitle("부서 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = new DeptPanel();
		contentPane.add(pContent);
		
		pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		pList = new DepartmentTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}
	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popupMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleItem = new JMenuItem("삭제");
		deleItem.addActionListener(popupMenuListener);
		popMenu.add(deleItem);
		
		JMenuItem empListByDeptItem = new JMenuItem("동일 부서 사원 보기");
		empListByDeptItem.addActionListener(popupMenuListener);
		popMenu.add(empListByDeptItem);
		
		return popMenu;
	}

	ActionListener popupMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(e.getActionCommand().equals("삭제")) {
					Department delDept = pList.getItem();
					service.delDept(delDept);
					JOptionPane.showMessageDialog(null,delDept + "삭제 되었습니다.");
					pList.loadData();
				}
				if(e.getActionCommand().equals("수정")) {
					btnAdd.setText("수정");
					
					Department modiDept = pList.getItem(); 
					pContent.setDepartment(modiDept);
					pContent.getTfDeptNo().setEditable(false);
				}
				if(e.getActionCommand().equals("동일 부서 사원 보기")) {
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
				
			}catch(NotSelectedExeption | SqlConstraintException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} /*
				 * catch(NullPointerException e2) { JOptionPane.showMessageDialog(null,
				 * "해당사원이 존재하지 않습니다."); } 예외처리로 처리
				 */
			
		}
	};
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdd) {
				actionPerformedBtnAdd(e);
			}	
		}catch(InvaildCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department dept = pContent.getDepartment();
		if(btnAdd.getText().equals("추가")) {
			service.addDept(dept);
			pList.loadData();
			pContent.ClearTf();
			JOptionPane.showMessageDialog(null, dept + "추가되었습니다.");
		}
		if(btnAdd.getText().equals("수정")) {
			JOptionPane.showMessageDialog(null, "수정해주세요");
			service.modiDept(dept);
			pList.loadData();
			pContent.ClearTf();
			JOptionPane.showMessageDialog(null, dept +" 로 수정되었습니다");
			btnAdd.setText("추가");
			pContent.getTfDeptNo().setEditable(true);
		}
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pContent.ClearTf();
	}
}
