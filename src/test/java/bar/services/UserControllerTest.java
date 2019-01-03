package bar.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import bar.SpringBarApplication;
import bar.dao.EmployeeRoleDAO;
import bar.dto.UserDTO;
import bar.model.User;
import bar.service.SecurityService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
@WebAppConfiguration
public class UserControllerTest extends AbstractTest {

//	MockHttpSession session;

	@MockBean
	private SecurityService securityService;
	@MockBean
	private UserDTO userDTO;
	@Autowired
	private EmployeeRoleDAO employeeRoleDAO;

	@Override
	@Before
	public void setup() {
		super.setup();
//		this.session = new MockHttpSession();
		this.userDTO = new UserDTO("registrationTest", "registrationTEST1@", "regtest@test.test", "server",
				LocalDate.of(2018, 1, 1));
//		request = post("/registerEmployeeSubmit").contentType("application/x-www-form-urlencoded")
//				.param("name", "registrationTest").param("password", "registrationTEST1@")
//				.param("email", "regtest@test.test").param("role", "SERVER").param("birthDate", "2017-01-01");
	}

	@Test
	public void whenContextIsBootstrapped_thenNoExceptions() {
	}

	@Test
	public void whenRegisterIsSuccessful_thenNoExceptions() throws Exception {
		when(securityService.register(any(User.class))).thenReturn("registeredUser");
		when(securityService.checkPermission(anyString())).thenReturn(true);

		MvcResult mvcResult = this.mvc.perform(buildPostRequest(userDTO)).andExpect(status().isOk()).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();

		assertViewName(mav, "registeredUser");
		assertModelAttributeAvailable(mav, "user");
	}

	@Test
	public final void whenRegisterFormViewIsRequested_thenNoExceptions() throws Exception {
		when(securityService.checkPermission("/view/registerEmployee")).thenReturn(true);
		mvc.perform(get("/view/registerEmployee")).andExpect(status().isOk()).andExpect(view().name("registerEmployee"))
				.andReturn();
	}

	@Test
	public void whenLoginFormIsRequested_thenNoException() throws Exception {
		MvcResult mvcResult = this.mvc.perform(get("/view/login")).andExpect(status().isOk()).andReturn();

		ModelAndView modelAndView = mvcResult.getModelAndView();

		assertViewName(modelAndView, "login");
	}

	@Test
	public void whenLoginFormIsSubmitted_thenIndexViewReturned_noException() throws Exception {
		when(securityService.authenticate(any(UserDTO.class))).thenReturn(true);

		MvcResult mvcResult = this.mvc.perform(post("/loginSubmit").contentType("application/x-www-form-urlencoded")
				.param("name", "regtest").param("password", "regtest")).andExpect(status().isOk()).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();

		assertViewName(mav, "index");
	}

	@Test
	public void whenLoginFormIsSubmitted_thenInvalidCredentailsViewReturned_noException() throws Exception {
		when(securityService.authenticate(any(UserDTO.class))).thenReturn(false);

		MvcResult mvcResult = this.mvc.perform(post("/loginSubmit").contentType("application/x-www-form-urlencoded")
				.param("name", "regtest").param("password", "regtest")).andExpect(status().isOk()).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();

		assertViewName(mav, "invalidCredentials");
	}

	@Test
	public void whenLogoutRequest_thenNoException() throws Exception {
		this.mvc.perform(get("/signout")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
	}

	@Test
	public void whenRegistering_userNameInvalid_noException() throws Exception {
		UserDTO testUser = new UserDTO("", userDTO.getPassword(), userDTO.getEmail(), userDTO.getEmployeeRole(),
				userDTO.getBirthDate());

		this.mvc.perform(buildPostRequest(testUser)).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void whenEmptyPassword_passwordInvalid_noException() throws Exception {
		UserDTO testUser = new UserDTO(userDTO.getName(), "", userDTO.getEmail(), userDTO.getEmployeeRole(),
				userDTO.getBirthDate());

		this.mvc.perform(buildPostRequest(testUser)).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void whenNoLowerCaseCharacter_passwordInvalid_noException() throws Exception {
		UserDTO testUser = new UserDTO(userDTO.getName(), "TEST1@", userDTO.getEmail(), userDTO.getEmployeeRole(),
				userDTO.getBirthDate());

		this.mvc.perform(buildPostRequest(testUser)).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void whenNoUpperCaseCharacter_passwordInvalid_noException() throws Exception {
		UserDTO testUser = new UserDTO(userDTO.getName(), "test1@", userDTO.getEmail(), userDTO.getEmployeeRole(),
				userDTO.getBirthDate());

		this.mvc.perform(buildPostRequest(testUser)).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void whenNoDigit_passwordInvalid_noException() throws Exception {
		UserDTO testUser = new UserDTO(userDTO.getName(), "tesT@", userDTO.getEmail(), userDTO.getEmployeeRole(),
				userDTO.getBirthDate());

		this.mvc.perform(buildPostRequest(testUser)).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void whenNoSpecialSymbol_passwordInvalid_noException() throws Exception {
		UserDTO testUser = new UserDTO(userDTO.getName(), "testT1", userDTO.getEmail(), userDTO.getEmployeeRole(),
				userDTO.getBirthDate());

		this.mvc.perform(buildPostRequest(testUser)).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void whenEmployeeRoleIsInvalid_noException() throws Exception {
		UserDTO testUser = new UserDTO(userDTO.getName(), userDTO.getPassword(), userDTO.getEmail(), "invalidRole",
				userDTO.getBirthDate());

		this.mvc.perform(buildPostRequest(testUser)).andExpect(status().isBadRequest()).andReturn();
	}

	private MockHttpServletRequestBuilder buildPostRequest(UserDTO userDTO) {
		return post("/registerEmployeeSubmit").contentType("application/x-www-form-urlencoded")
				.param("name", userDTO.getName()).param("password", userDTO.getPassword())
				.param("email", userDTO.getEmail()).param("employeeRole", userDTO.getEmployeeRole())
				.param("birthDate", userDTO.getBirthDate().toString());
	}
}
