package bar.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import bar.SpringBarApplication;
import bar.dto.UserDTO;
import bar.model.EmployeeRole;
import bar.model.Permission;
import bar.model.User;
import bar.repository.EmployeeRoleDAO;
import bar.repository.UserDAO;
import bar.service.SecurityService;
import bar.service.UserContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
@WebAppConfiguration
@Transactional
public class SecurityServiceTest extends AbstractTest {

	@Autowired
	MockHttpSession httpSession;
	@Autowired
	private SecurityService securityService;
	@MockBean
	private UserContext userContext;
	private String testUri;
	@Autowired
	private EmployeeRoleDAO employeeRoleDAO;
	private EmployeeRole testRoleWithPermission;
	private EmployeeRole testRoleWithOutPermission;
	private User user;
	@MockBean
	private UserDAO userDAO;
	@MockBean
	PasswordEncoder passwordEncoder;

	@Before
	@Override
	public void setup() {
		super.setup();
		httpSession.setAttribute("userContext", userContext);
		when(userContext.getName()).thenReturn("testName");
		testUri = "/SpringBar/test";
		testRoleWithPermission = employeeRoleDAO.findByName("testRoleWithPermission");
		testRoleWithOutPermission = employeeRoleDAO.findByName("testRoleWithOutPermission");
		user = new User("registrationTest", "registrationTEST1@", "regtest@test.test", testRoleWithPermission,
				LocalDate.of(2018, 1, 1));
	}

	@Test
	public void checkPermissionTest_nullInput() {
		assertFalse(securityService.checkPermission(null));
	}

	@Test
	public void checkPermissionTest_nonRestrictedUri() {
		assertTrue(securityService.checkPermission("/SpringBar/nonRestrictedUri"));
	}

	@Test
	public void checkPermissionTest_whenUserNotAuthenticated() {
		when(userContext.isAuthenticated()).thenReturn(false);
		assertFalse(securityService.checkPermission(testUri));
	}

	@Test
	public void checkPermissionTest_notPermitted() {
		when(userContext.isAuthenticated()).thenReturn(true);
		when(userContext.getEmployeeRole()).thenReturn(testRoleWithOutPermission);
		assertFalse(securityService.checkPermission(testUri));
	}

	@Test
	public void checkPermissionTest_Permitted() {
		when(userContext.isAuthenticated()).thenReturn(true);
		when(userContext.getEmployeeRole()).thenReturn(testRoleWithPermission);
		assertTrue(securityService.checkPermission(testUri));
	}

	@Test
	public void setPermissionsTest_whenPermissionIsNew() throws Exception {
		Permission permission = securityService.setPermissions("/SpringBar/testNewPermission", testRoleWithPermission);
		assertNotNull(permission);
		assertEquals("/SpringBar/testNewPermission", permission.getUri());
		permission.hasAccess(testRoleWithPermission);
	}

	@Test
	public void setPermissionsTest_whenPermissionExists() {
		Permission permission = securityService.setPermissions("/SpringBar/test",
				new EmployeeRole("testRoleInExistingPermission"));
		assertNotNull(permission);
		assertEquals("/SpringBar/test", permission.getUri());
		EmployeeRole employeeRole = employeeRoleDAO.findByName("testRoleInExistingPermission");
		permission.hasAccess(employeeRole);
	}

	@Test
	public void getUserNameTest_whenAuthenticated() {
		when(userContext.isAuthenticated()).thenReturn(true);
		when(userContext.getName()).thenReturn("testName");
		assertEquals("testName", securityService.getUserName());
	}

	@Test
	public void getUserNameTest_whenNotAuthenticated() {
		when(userContext.isAuthenticated()).thenReturn(false);
		assertEquals("Sign in", securityService.getUserName());
	}

	@Test
	public void getUserTest() {
		User testUser = new User();
		when(userContext.getUser()).thenReturn(testUser);
		assertEquals(testUser, securityService.getUser());
	}

	@Test
	public void authenticateTest_successful() {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("test_user");
		userDTO.setPassword("testUser1@");
		when(userDAO.findByName("test_user")).thenReturn(user);
		when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
		assertTrue(securityService.authenticate(userDTO));
	}

	@Test
	public void authenticateTest_failed_whenPasswordIsNotCorrect() {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("test_user");
		userDTO.setPassword("incorrectPassword1@");
		assertFalse(securityService.authenticate(userDTO));
	}

	@Test
	public void authenticateTest_failed_whenUserDoesNotExist() {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("missingUser");
		userDTO.setPassword("noExistingPassward1@");
		assertFalse(securityService.authenticate(userDTO));
	}

	@Test
	public void registerTest_successful() {
		when(userDAO.findByName(anyString())).thenReturn(null);
		when(userDAO.findByEmail(anyString())).thenReturn(null);
		when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
		when(userDAO.save(any(User.class))).thenReturn(any(User.class));
		assertEquals("registeredUser", securityService.register(new User()));
	}

	@Test
	public void registerTest_nameConflict() {
		when(userDAO.findByName(anyString())).thenReturn(user);
		when(userDAO.findByEmail(anyString())).thenReturn(null);
		when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
		when(userDAO.save(any(User.class))).thenReturn(user);
		assertEquals("nameConflict", securityService.register(user));
	}

	@Test
	public void registerTest_emailConflict() {
		when(userDAO.findByName(anyString())).thenReturn(null);
		when(userDAO.findByEmail(anyString())).thenReturn(user);
		when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
		when(userDAO.save(any(User.class))).thenReturn(user);
		assertEquals("emailConflict", securityService.register(user));
	}

	@Test
	public void getRoleTest_whenAuthenticated() {
		when(userContext.isAuthenticated()).thenReturn(true);
		when(userContext.getEmployeeRole()).thenReturn(testRoleWithPermission);
		assertEquals("testRoleWithPermission", securityService.getRole());
	}

	@Test
	public void getRoleTest_whenNotAuthenticated() {
		when(userContext.isAuthenticated()).thenReturn(false);
		assertEquals("", securityService.getRole());
	}

	@Test
	public void signOutTest() {
		when(userContext.isAuthenticated()).thenReturn(true);
		assertTrue(securityService.signout());
	}
}
