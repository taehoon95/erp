package erp.dao;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.dao.Impl.EmployeeDetailDaoImpl;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDetailDaoTest {

	private EmployeeDetailDao dao = EmployeeDetailDaoImpl.getInstance();
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test02SelectEmployeeDetailByNo() {
		System.out.println("test02SelectEmployeeDetailByNo()");
		EmployeeDetail employeeDetail = dao.selectEmployeeDetailByNo(new Employee(1003));
		Assert.assertNotNull(employeeDetail);
		System.out.println(employeeDetail);
	}

	@Test
	public void test01InsertEmployeeDetail() {
		System.out.println("test01InsertEmployeeDetail");
		
		Calendar cal = GregorianCalendar.getInstance();
		
		
		EmployeeDetail detail = new EmployeeDetail(1003, true, new Date(),"1234", getImage("noimage.jpg") );
		int res = dao.insertEmployeeDetail(detail);
		Assert.assertEquals(1, res);
	}

	private byte[] getImage(String imgName) {
		byte[] pic = null;
		File file = new File(System.getProperty("user.dir") + File.separator + "images" ,imgName);
		try(InputStream is = new FileInputStream(file);){
			pic = new byte[is.available()]; // file로 부터 읽은 이미지의 바이트길이로 배열 생성
			is.read(pic);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}

	@Test
	public void test03UpdateEmployeeDetail() {
		System.out.println("test03UpdateEmployeeDetail");
		EmployeeDetail newEmpDetail = new EmployeeDetail(1003, false, new Date(), "1111", getImage("dog3.jpg"));
		int res = dao.updateEmployeeDetail(newEmpDetail);
		Assert.assertEquals(1, res);
	}

//	@Test
	public void test04DeleteEmployeeDetail() {
		System.out.println("test04DeleteEmployeeDetail()");
		int res = dao.deleteEmployeeDetail(new Employee(1003));
		Assert.assertEquals(1, res);
	}

}
