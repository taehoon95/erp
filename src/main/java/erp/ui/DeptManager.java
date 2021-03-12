package erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.service.DeptService;
import erp.ui.content.DeptPanel;
import erp.ui.list.DepartmentTablePanel;

public class DeptManager extends JFrame {

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
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
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
		
		JMenuItem empListByDeptItem = new JMenuItem("동일 직책 사원 보기");
		empListByDeptItem.addActionListener(popupMenuListener);
		popMenu.add(empListByDeptItem);
		
		return popMenu;
	}

	ActionListener popupMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
}
