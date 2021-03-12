package erp.dto;

import java.util.Arrays;
import java.util.Date;

public class EmpDetail {
	private int empNO;
	private boolean gender;
	private Date hireDate;
	private byte[] pic;

	public EmpDetail() {
	}

	public EmpDetail(int empNO) {
		this.empNO = empNO;
	}

	public EmpDetail(int empNO, boolean gender, Date hireDate, byte[] pic) {
		this.empNO = empNO;
		this.gender = gender;
		this.hireDate = hireDate;
		this.pic = pic;
	}

	public int getEmpNO() {
		return empNO;
	}

	public void setEmpNO(int empNO) {
		this.empNO = empNO;
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

	@Override
	public String toString() {
		return String.format("EmpDetail [empNO=%s, gender=%s, hireDate=%s, pic=%s]", empNO, gender, hireDate,
				Arrays.toString(pic));
	}

}
