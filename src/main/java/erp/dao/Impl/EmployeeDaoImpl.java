package erp.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp.dao.EmployeeDao;
import erp.database.JdbcConn;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.ui.exception.SqlConstraintException;

public class EmployeeDaoImpl implements EmployeeDao {

	private static EmployeeDaoImpl instance = new EmployeeDaoImpl();

	public static EmployeeDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}

	private EmployeeDaoImpl() {
	}

	private Employee getEmployee2(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");
		Title title = new Title(rs.getInt("title_no"));
		Employee manager = new Employee(rs.getInt("manager_no"));
		int salary = rs.getInt("salary");
		Department dept = new Department(rs.getInt("deptNo"));

		try {
			title.setTname(rs.getString("tname"));
			manager.setEmpName(rs.getString("mgrName"));
			dept.setDeptName(rs.getString("deptname"));
			dept.setFloor(rs.getInt("floor"));
		} catch (SQLException e) {
		}

		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");
		Title title = new Title(rs.getInt("title_no"));
		Employee manager = new Employee(rs.getInt("manager_no"));
		int salary = rs.getInt("salary");
		Department dept = new Department(rs.getInt("deptNo"));

		try {
			title.setTname(rs.getString("title_name"));
		} catch (SQLException e) {
		}

		try {
			manager.setEmpName(rs.getString("manager_name"));
		} catch (SQLException e) {
		}

		try {
			dept.setDeptName(rs.getString("deptname"));
			dept.setFloor(rs.getInt("floor"));
		} catch (SQLException e) {
		}

		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	@Override
	public List<Employee> selectEmpByAllJoin() {
		String sql = "select empno,empname,title_no,title_name,manager_no,manager_name,salary,deptNo,deptname,floor from vw_full_employee";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Employee> list = new ArrayList<>();
				do {
					list.add(getEmployee(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> selectEmpByAll() {
		String sql = "select empno,empname,title as title_no,manager as manager_no ,salary,dept as deptNo from employee;";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Employee> list = new ArrayList<>();
				do {
					list.add(getEmployee2(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee selectEmpByNo(Employee emp) {
		String sql = "select empno,empname,title as title_no" + ",manager as manager_no,salary,dept as deptno"
				+ " from employee where empno = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee emp) {
		String sql = "insert into employee values (?,?,?,?,?,?)";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setInt(3, emp.getTitle().getTno());
			pstmt.setInt(4, emp.getManager().getEmpNo());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDept().getDeptNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		String sql = "update employee " + "set empname = ?,title = ?, manager = ?, salary =?, dept = ? "
				+ "where empno = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, emp.getTitle().getTno());
			pstmt.setInt(3, emp.getManager().getEmpNo());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setInt(5, emp.getDept().getDeptNo());
			pstmt.setInt(6, emp.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(int empNo) {
		String sql = "delete from employee where empno = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, empNo);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Employee> selectEmpByDeptNo(Department dept) {
		String sql = "select empno,empname from employee e join department d on e.dept = d.deptno where deptno = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, dept.getDeptNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ArrayList<Employee> list = new ArrayList<Employee>();
					do {
						list.add(getEmployee3(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> selectEmployeeByTitle(Title title) {
		String sql = "select empname, empno\r\n" + "  from employee e \r\n" + "  join title t\r\n"
				+ "    on e.title  = t.tno\r\n" + " where tno = ? ";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, title.getTno());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ArrayList<Employee> list = new ArrayList<Employee>();
					do {
						list.add(getEmployee3(rs));
					} while (rs.next());
					return list;
				}
			}	
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployee3(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");
		return new Employee(empNo, empName);
	}

}
