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

import javax.swing.text.View;

import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import bar.dto.UserDTO;
import bar.model.Role;
import bar.model.User;
import bar.service.SecurityService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
@WebAppConfiguration
public class UserControllerTest extends AbstractTest {

	MockHttpSession session;

	@MockBean
	private SecurityService securityService;
	@MockBean
	private User user;
	private MockHttpServletRequestBuilder request;

	@Override
	@Before
	public void setup() {
		super.setup();
		this.session = new MockHttpSession();
		this.user = new User("registrationTest", "registrationTEST1@", "regtest@test.test", Role.SERVER,
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
		MvcResult mvcResult = this.mvc.perform(buildPostRequest(user)).andExpect(status().isOk()).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();

		assertViewName(mav, "registeredUser");
		assertModelAttributeAvailable(mav, "user");
	}

	@Test
	public final void whenRegisterFormViewIsRequested_thenNoExceptions() throws Exception {
		when(securityService.checkPermission("/view/registerEmployee")).thenReturn(true);
		MvcResult mvcResult = mvc.perform(get("/view/registerEmployee")).andExpect(status().isOk()).andReturn();

		ModelAndView modelAndView = mvcResult.getModelAndView();

		assertViewName(modelAndView, "registerEmployee");
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
		user.setName("");
		MvcResult mvcResult = this.mvc.perform(buildPostRequest(user)).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void whenEmptyPassword_passwordInvalid_noException() throws Exception {
		user.setPassword("");
		MvcResult mvcResult = this.mvc.perform(buildPostRequest(user)).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void whenNoLowerCaseCharacter_passwordInvalid_noException() throws Exception {
		user.setPassword("TEST1@");
		MvcResult mvcResult = this.mvc.perform(buildPostRequest(user)).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void whenNoUpperCaseCharacter_passwordInvalid_noException() throws Exception {
		user.setPassword("test1@");
		MvcResult mvcResult = this.mvc.perform(buildPostRequest(user)).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void whenNoDigit_passwordInvalid_noException() throws Exception {
		user.setPassword("tesT@");
		MvcResult mvcResult = this.mvc.perform(buildPostRequest(user)).andExpect(status().isBadRequest()).andReturn();
	}

	private MockHttpServletRequestBuilder buildPostRequest(User user) {
		return post("/registerEmployeeSubmit").contentType("application/x-www-form-urlencoded")
				.param("name", user.getName()).param("password", user.getPassword()).param("email", user.getEmail())
				.param("role", user.getRole().toString()).param("birthDate", user.getBirthDate().toString());
	}
}
