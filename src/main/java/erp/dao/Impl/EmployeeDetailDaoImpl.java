package erp.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import erp.dao.EmployeeDetailDao;
import erp.database.JdbcConn;
import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.ui.exception.SqlConstraintException;

public class EmployeeDetailDaoImpl implements EmployeeDetailDao {

	private static EmployeeDetailDaoImpl instance = new EmployeeDetailDaoImpl();

	public static EmployeeDetailDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmployeeDetailDaoImpl();
		}
		return instance;
	}

	private EmployeeDetailDaoImpl() {
	}
	
	@Override
	public EmployeeDetail selectEmployeeDetailByNo(Employee emp) {
		String sql = "select empno,pic,gender,hiredate from emp_detail where empno = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getEmployeeDetail(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private EmployeeDetail getEmployeeDetail(ResultSet rs) throws SQLException {
		int empNO = rs.getInt("empno");
		boolean gender = rs.getBoolean("gender");
		Date hireDate = rs.getTimestamp("hireDate");
		byte[] pic = rs.getBytes("pic");
		return new EmployeeDetail(empNO, gender, hireDate, pic);
	}

	@Override
	public int insertEmployeeDetail(EmployeeDetail empDetail) {
		String sql = "INSERT INTO emp_detail (empno, pic, gender, hiredate, pass) VALUES(?, ?, ?, ?, password(?))";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, empDetail.getEmpNO());
			pstmt.setBytes(2, empDetail.getPic());
			pstmt.setBoolean(3, empDetail.isGender());
			//util.Date -> sql.Date로 변환
			pstmt.setTimestamp(4, new Timestamp(empDetail.getHireDate().getTime()));
			pstmt.setString(5, empDetail.getPass());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SqlConstraintException(e.getMessage(),e);
		}
	}

	@Override
	public int updateEmployeeDetail(EmployeeDetail empDetail) {
		String sql = "update emp_detail set pic = ? ,gender = ?, hiredate = ?, pass = password(?) where empno = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setBytes(1, empDetail.getPic());
			pstmt.setBoolean(2, empDetail.isGender());
			pstmt.setTimestamp(3, new Timestamp(empDetail.getHireDate().getTime()));
			pstmt.setString(4, empDetail.getPass());
			pstmt.setInt(5, empDetail.getEmpNO());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployeeDetail(Employee emp) {
		String sql = "delete from emp_detail where empno = ?";
		try (Connection con = JdbcConn.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
