package erp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp.dao.TitleDao;
import erp.database.JdbcConn;
import erp.dto.Title;
import erp.ui.exception.SqlConstraintException;

public class TitleDaoimpl implements TitleDao {

	private static final TitleDaoimpl instance = new TitleDaoimpl();
	
	public static TitleDaoimpl getInstance() {
		return instance;
	}

	private TitleDaoimpl() {}

	@Override
	public List<Title> selectTitleByAll() {
		String sql = "select tno,tname from title";
		try(Connection con = JdbcConn.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				List<Title> list = new ArrayList<>();
				do {
					list.add(getTitle(rs));
				}while(rs.next());
				return list;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		int tNo = rs.getInt("tno");
		String tName = rs.getString("tname"); 
		return new Title(tNo, tName);
	}

	@Override
	public Title selectTitleByNo(Title title) {
		String sql = "select tno,tname from title  where tno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
				pstmt.setInt(1,title.getTno());
				try(ResultSet rs = pstmt.executeQuery()){
					if(rs.next()) {
						return getTitle(rs);
					}
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertTitle(Title title) {
		String sql = "insert into title values(?,?)";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, title.getTno());
			pstmt.setNString(2, title.getTname());
			return pstmt.executeUpdate();
		}catch(SQLException e){
			throw new SqlConstraintException(e);
		}
	}

	@Override
	public int updateTitle(Title title) {
		String sql = "update title set tname = ? where tno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, title.getTname());
			pstmt.setInt(2, title.getTno());
			return pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteTitle(int titleNo) {
		String sql = "delete from title where tno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, titleNo);
			return pstmt.executeUpdate();
		}catch(SQLException e){
			throw new SqlConstraintException(e);
//			System.out.println("e.getErrorCode() "+e.getErrorCode());
//			e.printStackTrace();
		}
//		return 0;
	}

}
