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

import erp.dto.Employee;
import erp.dto.Title;
import erp.service.TitleService;
import erp.ui.content.TitlePanel;
import erp.ui.exception.InvaildCheckException;
import erp.ui.exception.NotSelectedExeption;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.TitleTablePanel;

public class TitleManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private TitlePanel pContent;
	private TitleTablePanel pList;
	private TitleService service;

	public TitleManager() {
		service = new TitleService();
		initialize();
	}

	private void initialize() {
		setTitle("직책 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = new TitlePanel();
		contentPane.add(pContent);

		pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		JButton btnClear = new JButton("취소");
		pBtn.add(btnClear);

		pList = new TitleTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);

		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popupMenuListner);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popupMenuListner);
		popMenu.add(deleteItem);

		JMenuItem empListByTitleItem = new JMenuItem("동일 직책 사원 보기");
		empListByTitleItem.addActionListener(popupMenuListner);
		popMenu.add(empListByTitleItem);

		return popMenu;
	}

	ActionListener popupMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("삭제")) {
					Title delTitle = pList.getItem();
					service.removeTitle(delTitle);
					JOptionPane.showMessageDialog(null, delTitle + "삭제되었습니다.");
					pList.loadData();
				}
				if (e.getActionCommand().equals("수정")) {
					btnAdd.setText("수정");
					Title upTitle = pList.getItem();
					pContent.setTitle(upTitle);
					service.modifyTitle(upTitle);
					JOptionPane.showMessageDialog(null, upTitle + "수정하세요.");
					pContent.getTfTitleNo().setEditable(false);
					pList.loadData();
				}
				if (e.getActionCommand().equals("동일 직책 사원 보기")) {
					/*
					 * 1. EmployeeDao -> selectEmployeeByTitle() 추가
					 * 2. EmployeeDaoImpl -> selectEmployeeByTitle() 구현 
					 * 3. EmployeeDaoTest -> Test하기 
					 * 4. TitleService -> EmployeeDaoImpl field 추가 및 메서드 추가
					 * 5. 아래 기능 추가 
					 * 6. 예외찾아서 추가하기 (신규 직책 추가 시 NullPointException)
					 * 
					 */
					Title searchTitle = pList.getItem();
					List<Employee> empList = service.empSelectByTitle(searchTitle);
					if (empList == null) {
						JOptionPane.showMessageDialog(null, "해당 사원이 없음", "동일 직책 사원", JOptionPane.WARNING_MESSAGE);
						return;
					}
					List<String> strList = empList.parallelStream().map(s -> {
						return String.format("%s(%d)", s.getEmpName(), s.getEmpNo());
					}).collect(Collectors.toList());
					JOptionPane.showMessageDialog(null, strList, "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (NotSelectedExeption | SqlConstraintException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	};
	private JPanel pBtn;

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdd) {
				actionPerformedBtnAdd(e);
			}
		} catch (InvaildCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title title = pContent.getTitle();
		if (btnAdd.getText().equals("추가")) {
			service.addTitle(title);
			pList.loadData();
			pContent.clearTf();
			JOptionPane.showMessageDialog(null, title + "추가했습니다.");
		}
		if (btnAdd.getText().equals("수정")) {
			service.modifyTitle(title);
			pList.loadData();
			pContent.clearTf();
			JOptionPane.showMessageDialog(null, title + "수정했습니다.");
			btnAdd.setText("추가");
			pContent.getTfTitleNo().setEditable(true);
		}

	}
}
