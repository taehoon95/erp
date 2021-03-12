package erp;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import erp.ui.list.TitleTablePanel;
import java.awt.GridLayout;
import erp.ui.list.DepartmentTablePanel;

public class testFrame extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 445, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		TitleTablePanel panel1 = new TitleTablePanel();
		panel1.loadData();
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(panel1);
		
		DepartmentTablePanel panel2 = new DepartmentTablePanel();
		panel2.loadData();
		contentPane.add(panel2);
	}

}
