package erp.dto;

import java.util.Arrays;
import java.util.Date;

public class EmployeeDetail {
	private int empNo;
	private boolean gender;
	private Date hireDate;
	private byte[] pic;
	private String pass;

	public EmployeeDetail() {
	}

	public EmployeeDetail(int empNO) {
		this.empNo = empNO;
	}

	public EmployeeDetail(int empNO, boolean gender, Date hireDate, byte[] pic) {
		this.empNo = empNO;
		this.gender = gender;
		this.hireDate = hireDate;
		this.pic = pic;
	}
	
	

	public EmployeeDetail(int empNO, boolean gender, Date hireDate,  String pass, byte[] pic) {
		this.empNo = empNO;
		this.gender = gender;
		this.hireDate = hireDate;
		this.pic = pic;
		this.pass = pass;
	}

	public int getEmpNO() {
		return empNo;
	}

	public void setEmpNO(int empNO) {
		this.empNo = empNO;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return String.format("empNO=%s, gender=%s, hireDate=%s, pic=%s", empNo, gender, hireDate,
				pic.length);
	}

}
