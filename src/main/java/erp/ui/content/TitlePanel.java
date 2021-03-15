package erp.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp.dto.Title;
import erp.ui.exception.InvaildCheckException;

public class TitlePanel extends InterfaceItem<Title>{
	private JTextField tfTitleNo;
	private JTextField tfTitleName;

	public JTextField getTfTitleNo() {
		return tfTitleNo;
	}


	public void setTfTitleNo(JTextField tfTitleNo) {
		this.tfTitleNo = tfTitleNo;
	}


	public TitlePanel() {
		initialize();
	}
	
	public void initialize() {
		setBorder(new TitledBorder(null, "직책", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTitleNo = new JLabel("직책번호");
		lblTitleNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleNo);
		
		tfTitleNo = new JTextField();
		add(tfTitleNo);
		tfTitleNo.setColumns(10);
		
		JLabel lblTitleName = new JLabel("직책명");
		lblTitleName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleName);
		
		tfTitleName = new JTextField();
		tfTitleName.setColumns(10);
		add(tfTitleName);
	}


	@Override
	public void setItem(Title item) {
		tfTitleNo.setText(item.getTno() + "");
		tfTitleName.setText(item.getTname());
	}


	@Override
	public Title getItem() {
		validCheck();
		int tNo = Integer.parseInt(tfTitleNo.getText().trim());
		String tName = tfTitleName.getText().trim();
		return new Title(tNo, tName);
	}


	@Override
	public void validCheck() {
		if(tfTitleNo.getText().contentEquals("") || tfTitleName.getText().equals("")) {
			throw new InvaildCheckException();
		}
	}
	
	@Override
	public void clearTf() {
		tfTitleNo.setText("");
		tfTitleName.setText("");
	}

}
