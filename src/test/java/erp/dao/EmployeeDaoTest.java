package erp.dao;


import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.dao.Impl.EmployeeDaoImpl;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectEmpByAll() {
		System.out.println("test04SelectDeptByAll");
		List<Employee> empList = dao.selectEmpByAll();
		Assert.assertNotNull(empList); // null 이 아니면 성공
		for (Employee e : empList) {
			System.out.println(e);
		}
	}

	@Test
	public void test05SelectEmpByNo() {
		System.out.println("test05SelectDeptByNo");
		Employee selEmp = new Employee(2106);
		Employee seachEmployee = dao.selectEmpByNo(selEmp);
		Assert.assertNotNull(seachEmployee); // null 이 아니면 성공
		System.out.println(seachEmployee);
	}

	@Test
	public void test01InsertEmployee() {
		System.out.println("testInsertEmployee");
		Employee newEmp = new Employee(1004, "천사", new Title(5), new Employee(4377), 2000000, new Department(1));
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmpByNo(newEmp));
	}

	@Test
	public void test02UpdateEmployee() {
		System.out.println("test02UpdateEmployee");
		Employee newEmp = new Employee(1004, "천사2", new Title(4), new Employee(1003), 3700000, new Department(2));
		int res = dao.updateEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmpByNo(newEmp));
	}

	@Test
	public void test03DeleteEmployee() {
		System.out.println("test03DeleteEmployee");
		int res = dao.deleteEmployee(1004);
		Assert.assertEquals(1, res);
		dao.selectEmpByAll().stream().forEach(System.out::println);
	}

	@Test
	public void test06selectEmpByAllJoin() {
		System.out.println("test06selectEmpByAllJoin");
		List<Employee> empList = dao.selectEmpByAllJoin();
		Assert.assertNotNull(empList);
		for (Employee e : empList) {
			System.out.println(e.toString2());
		}
	}
	
	@Test
	public void test07selectEmpByDept() {
		System.out.println("test07selectEmpByDept");
		Department dept = new Department(2);
		List<Employee> list = dao.selectEmpByDeptNo(dept);
		Assert.assertNotNull(dept);
		for(Employee emp : list) {
			System.out.println(emp);
		}
	}

	@Test
	public void test09selectEmpByTitle() {
		System.out.println("test08selectEmpByTitle");
		Title title = new Title(1);
		List<Employee> list = dao.selectEmployeeByTitle(title);
		Assert.assertNotNull(title);
		for(Employee emp : list) {
			System.out.println(emp);
		}
	}

}

