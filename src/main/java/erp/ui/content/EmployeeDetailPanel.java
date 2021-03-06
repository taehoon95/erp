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
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	
	private JLabel lblPic;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnAddPic;
	private JLabel lblConfirm;
	private JDateChooser dateHire;
	private JTextField tfEmpno;
	private JRadioButton rdbbtnFemale;
	private JRadioButton rdbbtnMale;
	
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
		setBorder(new TitledBorder(null, "?????? ?????? ??????", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);

		JPanel pPic = new JPanel();
		pTop.add(pPic);
		pPic.setLayout(new BorderLayout(0, 0));

		lblPic = new JLabel();
		lblPic.setPreferredSize(new Dimension(100, 150));
		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		pPic.add(lblPic, BorderLayout.CENTER);

		btnAddPic = new JButton("????????????");
		btnAddPic.addActionListener(this);
		pPic.add(btnAddPic, BorderLayout.SOUTH);

		JPanel pItem = new JPanel();
		add(pItem, BorderLayout.CENTER);
		pItem.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pContent = new JPanel();
		pItem.add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEmpno = new JLabel("?????? ??????");
		lblEmpno.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblEmpno);
		
		tfEmpno = new JTextField();
		tfEmpno.setEditable(false);
		pContent.add(tfEmpno);
		tfEmpno.setColumns(10);
		
		JLabel lblHireDate = new JLabel("?????????");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblHireDate);
		
		dateHire = new JDateChooser();
		pContent.add(dateHire);
		
		JLabel lblGender = new JLabel("??????");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblGender);
		
		JPanel pGender = new JPanel();
		pContent.add(pGender);
		
		rdbbtnFemale = new JRadioButton("???");
		rdbbtnFemale.setSelected(true);
		buttonGroup.add(rdbbtnFemale);
		pGender.add(rdbbtnFemale);
		
		rdbbtnMale = new JRadioButton("???");
		buttonGroup.add(rdbbtnMale);
		pGender.add(rdbbtnMale);
		
		JLabel lblPass1 = new JLabel("????????????");
		lblPass1.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPass1);
		
		tfPass1 = new JPasswordField();
		tfPass1.getDocument().addDocumentListener(listener);
		pContent.add(tfPass1);
		
		JLabel lblPass2 = new JLabel("???????????? ??????");
		lblPass2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPass2);
		
		tfPass2 = new JPasswordField();
		tfPass2.getDocument().addDocumentListener(listener);
		pContent.add(tfPass2);
		
		JPanel pSpace = new JPanel();
		pContent.add(pSpace);
		
		lblConfirm = new JLabel();
		lblConfirm.setFont(new Font("??????", Font.BOLD, 20));
		lblConfirm.setForeground(Color.RED);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		pContent.add(lblConfirm);
	}
	
	public void setTfEmpno(JTextField tfEmpno) {
		this.tfEmpno = tfEmpno;
	}
	
	public JTextField getTfEmpno() {
		return tfEmpno;
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
		tfEmpno.setText(String.valueOf(empNo.getEmpNo()));
	}

	@Override
	public EmployeeDetail getItem() {
		validCheck();
		int empNo = Integer.parseInt(tfEmpno.getText().trim());
		boolean gender = rdbbtnFemale.isSelected()?true:false;
		Date hireDate = dateHire.getDate();
		String pass = String.valueOf(tfPass1.getPassword());
		byte[] pic = getImage();
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
		if(lblConfirm.getText().equals("?????????")) {
			throw new InvaildCheckException("???????????? ?????????");
		}
	}

	@Override
	public void clearTf() {
		loadPic(null);
		dateHire.setDate(new Date());
		rdbbtnFemale.setSelected(true);
		tfPass1.setText("");
		tfPass2.setText("");
		lblConfirm.setText("");
		
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
			JOptionPane.showMessageDialog(null, "????????? ???????????? ???????????????.", "??????", JOptionPane.WARNING_MESSAGE);
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
				lblConfirm.setText("??????");
			}else {
				lblConfirm.setText("?????????");
			}
		}
	};
	

}
