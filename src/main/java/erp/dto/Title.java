package erp.dto;

public class Title {
	private int tNo;
	private String tName;

	public Title() {
	}

	public Title(int tNo) {
		this.tNo = tNo;
	}

	public Title(int tNo, String tName) {
		this.tNo = tNo;
		this.tName = tName;
	}

	public int getTno() {
		return tNo;
	}

	public void setTno(int tno) {
		this.tNo = tno;
	}

	public String getTname() {
		return tName;
	}

	public void setTname(String tname) {
		this.tName = tname;
	}

	@Override
	public String toString() {
		return String.format("%4s %3s  ", tNo, tName);
	}

}
