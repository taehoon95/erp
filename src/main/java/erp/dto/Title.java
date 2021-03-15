package erp.dto;

public class Title {
	private int tNo;
	private String tName;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Title other = (Title) obj;
		if (tNo != other.tNo)
			return false;
		return true;
	}

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
		return String.format("%s (%d)  ", tName, tNo);
	}

}
