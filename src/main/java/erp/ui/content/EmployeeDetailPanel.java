package erp.ui.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.ui.exception.InvaildCheckException;

public class EmployeeDetailPanel extends AbstractContentPanel<EmployeeDetail> implements ActionListener {
	private JPasswordField tfPass1;
	private JPasswordField tfPass2;
	private String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator;
	private JLabel lblPic;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnAddPic;
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	private JLabel lblConfirm;
	private JDateChooser dateHire;
	
	public EmployeeDetailPanel() {
		initialize();
		loadPic(null);
	}

	private void loadPic(String imgFilePath) {
		Image changeImage = null;
		if(imgFilePath == null) {
			ImageIcon icon = new ImageIcon(imgPath + "noimage.jpg");
			changeImage = icon.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH);	
		}else {
			ImageIcon icon = new ImageIcon(imgFilePath);
			changeImage = icon.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH);	
		}	
		ImageIcon changeIcon = new ImageIcon(changeImage);
		lblPic.setIcon(changeIcon);
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "사원 세부 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);

		JPanel pPic = new JPanel();
		pTop.add(pPic);
		pPic.setLayout(new BorderLayout(0, 0));

		lblPic = new JLabel();
		lblPic.setPreferredSize(new Dimension(100, 120));
		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		pPic.add(lblPic, BorderLayout.CENTER);

		btnAddPic = new JButton("사진추가");
		btnAddPic.addActionListener(this);
		pPic.add(btnAddPic, BorderLayout.SOUTH);

		JPanel pItem = new JPanel();
		add(pItem, BorderLayout.CENTER);
		pItem.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pContent = new JPanel();
		pItem.add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEmpno = new JLabel("\uC0AC\uC6D0 \uBC88\uD638");
		lblEmpno.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblEmpno);
		
		tfEmpno = new JTextField();
		tfEmpno.setEditable(false);
		pContent.add(tfEmpno);
		tfEmpno.setColumns(10);
		
		JLabel lblHireDate = new JLabel("입사일");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblHireDate);
		
		dateHire = new JDateChooser();
		dateHire.setDate(new Date());
		pContent.add(dateHire);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblGender);
		
		JPanel pGender = new JPanel();
		pContent.add(pGender);
		
		rdbbtnFemale = new JRadioButton("여");
		rdbbtnFemale.setSelected(true);
		buttonGroup.add(rdbbtnFemale);
		pGender.add(rdbbtnFemale);
		
		rdbbtnMale = new JRadioButton("남");
		buttonGroup.add(rdbbtnMale);
		pGender.add(rdbbtnMale);
		
		JLabel lblPass1 = new JLabel("비밀번호");
		lblPass1.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPass1);
		
		tfPass1 = new JPasswordField();
		tfPass1.getDocument().addDocumentListener(listener);
		pContent.add(tfPass1);
		
		JLabel lblPass2 = new JLabel("비밀번호 확인");
		lblPass2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPass2);
		
		tfPass2 = new JPasswordField();
		tfPass2.getDocument().addDocumentListener(listener);
		pContent.add(tfPass2);
		
		JPanel pSpace = new JPanel();
		pContent.add(pSpace);
		
		lblConfirm = new JLabel("불일치");
		lblConfirm.setFont(new Font("굴림", Font.BOLD, 20));
		lblConfirm.setForeground(Color.RED);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		pContent.add(lblConfirm);
	}

	public JTextField getTfEmpno() {
		return tfEmpno;
	}

	public void setTfEmpno(JTextField tfEmpno) {
		this.tfEmpno = tfEmpno;
	}

	@Override
	public void setItem(EmployeeDetail item) {
		
		tfEmpno.setText(String.valueOf(item.getEmpNO()));
		byte[] iconBytes = item.getPic();
		ImageIcon icon = new ImageIcon(iconBytes);
		lblPic.setIcon(icon);
		dateHire.setDate(item.getHireDate());
		if(item.isGender()) {
			rdbbtnFemale.setSelected(true);
		}else {
			rdbbtnMale.setSelected(true);
		}
	}
	
	public void setTfEmpno(Employee empNo) {
		tfEmpno.setText(String.valueOf(empNo));
	}

	@Override
	public EmployeeDetail getItem() {
		validCheck();
		int empNo = Integer.parseInt(tfEmpno.getText());
		boolean gender = rdbbtnFemale.isSelected()?true:false;
		Date hireDate = dateHire.getDate();
//		if(!lblConfirm.getText().equals("일치")) {
//		throw new InvaildCheckException("패스워드 불일치");
//		}
		String pass = String.valueOf(tfPass1.getPassword());
		byte[] pic = getImage();
		validCheck();
		return new EmployeeDetail(empNo, gender, hireDate, pass, pic);
	}

	private byte[] getImage() {
		
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			ImageIcon icon = (ImageIcon) lblPic.getIcon();
			BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			
			//icon => image
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(icon.getImage(), 0, 0, null);
			g2.dispose();
			
			ImageIO.write(bi, "png", baos);

			return baos.toByteArray();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void validCheck() {
		if(lblConfirm.getText().equals("불일치")) {
			throw new InvaildCheckException("비밀번호 불일치");
		}
	}

	@Override
	public void clearTf() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddPic) {
			actionPerformedBtnAddPic(e);
		}
	}
	protected void actionPerformedBtnAddPic(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG & GIF imaged", "jpg", "png", "gif");
		
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int res = chooser.showOpenDialog(null);
		if(res != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String chooseFilePath = chooser.getSelectedFile().getPath();
		loadPic(chooseFilePath);
	}
	
	DocumentListener listener = new DocumentListener() {
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			getMessage();
			
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			getMessage();
			
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			getMessage();
			
		}
		
		private void getMessage() {
			String pw1 = new String(tfPass1.getPassword());
			String pw2 = new String(tfPass2.getPassword());
			if(pw1.equals(pw2)) {
				lblConfirm.setText("일치");
			}else {
				lblConfirm.setText("불일치");
			}
		}
	};
	private JTextField tfEmpno;
	private JRadioButton rdbbtnFemale;
	private JRadioButton rdbbtnMale;

}
