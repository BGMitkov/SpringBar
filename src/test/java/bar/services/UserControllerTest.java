package bar.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import bar.SpringBarApplication;
import bar.dto.UserDTO;
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

	@Override
	@Before
	public void setup() {
		super.setup();
		this.session = new MockHttpSession();
	}

	@Test
	public final void whenContextIsBootstrapped_thenNoExceptions() {
	}

	@Test
	public final void whenRegisterIsSuccessful_thenNoExceptions() throws Exception {
		when(securityService.register(any(User.class))).thenReturn("registeredUser");
		MvcResult mvcResult = this.mvc
				.perform(post("/registerEmployeeSubmit").contentType("application/x-www-form-urlencoded")
						.param("name", "regtest").param("password", "regtest").param("email", "regtest@test.test")
						.param("role", "MANAGER").param("birthDate", "1970/1/1"))
				.andExpect(status().isOk()).andReturn();

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
}
