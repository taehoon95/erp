package erp.dto;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private Employee manager;
	private int salary;
	private Department dept;

	public Employee() {
		// TODO Auto-generated constructor stub
	}


	public Employee(int empNo, String empName) {
		this.empNo = empNo;
		this.empName = empName;
	}


	public Employee(int empNo, Employee manager) {
		this.empNo = empNo;
		this.manager = manager;
	}

	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}


	
	@Override
	public String toString() {
		return String.format("%s %s %s %s %s %s", empNo,
				empName, title.getTno(), manager.getEmpNo() == 0 ? null : manager.getEmpNo(), salary, dept.getDeptNo());
	}
	
	public String toString2() {
		return String.format("%4s %4s %4s %s %4s %4s %4s %s %s",
	         empNo,empName, title, manager.empName == null ? " " : manager.empName
			,manager.empNo == 0 ? null : manager.empNo, salary,
					dept.getDeptNo(),dept.getDeptName() == null ? " " : dept.getDeptName()
							,dept.getFloor() == 0 ? -1: dept.getFloor());}
	
}
