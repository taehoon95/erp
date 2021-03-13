package erp.dao;


import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.dao.Impl.DepartmentDaoImpl;
import erp.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	private static final DepartmentDao dao = DepartmentDaoImpl.getInstance();
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test05SelectDeptByAll() {
		System.out.println("testSelectDeptByAll");
		List<Department> deptList = dao.selectDeptByAll();
		Assert.assertNotNull(deptList);
		for(Department d : deptList) {
			System.out.println(d);
		}
	}

	@Test
	public void test04SelectDeptByNo() {
		System.out.println("testSelectDeptByNo");
		Department dept = new Department(3);
		Department searchDept = dao.selectDeptByNo(dept);
		Assert.assertNotNull(searchDept);
		System.out.println(searchDept);
	}

	@Test
	public void test01InsertDepartment() {
		System.out.println("testInsertDepartment");
		Department newDept = new Department(5,"인사",6);
		int res = dao.insertDepartment(newDept);
		Assert.assertEquals(1,res);
	}

	@Test
	public void test02UpdateDepartment() {
		System.out.println("testUpdateDepartment");
		Department newDept = new Department(5, "관리");
		int res = dao.updateDepartment(newDept);
		Assert.assertEquals(1,res);
		System.out.println(dao.selectDeptByNo(newDept));
	}

	@Test
	public void test03DeleteDepartment() {
		System.out.println("testDeleteDepartment");
		int res = dao.deleteDepartment(5);
		Assert.assertEquals(1,res);
	}

}

