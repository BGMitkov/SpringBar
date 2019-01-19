package bar.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bar.SpringBarApplication;
import bar.model.EmployeeRole;
import bar.repository.EmployeeRoleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
public class EmployeeRoleRepositoryTest {
	private static final String TEST_EMPLOYEE_ROLE = "testEmployeeRole";
	@Autowired
	private EmployeeRoleRepository employeeRoleDAO;

	@Test
	public void findByNameTest() {
		EmployeeRole employeeRole = employeeRoleDAO.findByName(TEST_EMPLOYEE_ROLE);
		assertNotNull(employeeRole);
		assertEquals(TEST_EMPLOYEE_ROLE, employeeRole.getName());
	}

	@Test
	public void existsByNameTest() {
		boolean exists = employeeRoleDAO.existsByName(TEST_EMPLOYEE_ROLE);
		assertTrue(exists);
	}
}
