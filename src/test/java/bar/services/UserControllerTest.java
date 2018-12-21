package bar.services;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import bar.SpringBarApplication;
import bar.model.Role;
import bar.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
@WebAppConfiguration
public class UserControllerTest extends AbstractTest {

	MockHttpSession session;

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private MockMvc mockMvc;

	@Override
	@Before
	public void setup() {
		super.setup();
//		this.session = new MockHttpSession();
	}

	@Test
	public final void whenContextIsBootstrapped_thenNoExceptions() {
	}

	@Test
	public final void whenRegisterIsSuccessful_thenNoExceptions() throws Exception {
		User testUser = new User("regtest", passwordEncoder.encode("regtest"), "regtest@test.test", Role.MANAGER,
				"02:00:00 1/1/1970");

		String uri = "/registerUser";

		MvcResult mvcResult = this.mockMvc.perform(post("/registerUser")
				.contentType("application/x-www-form-urlencoded").param("name", "regtest").param("password", "regtest")
				.param("email", "regtest@test.test").param("role", "MANAGER").param("birthDate", "02:00:00 1/1/1970"))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		ModelAndView mav = mvcResult.getModelAndView();
		assertViewName(mav, "registeredUser");
		assertModelAttributeAvailable(mav, "user");
		assertModelAttributeAvailable(mav, "applicationName");
		ModelAndViewAssert.assertModelAttributeValue(mav, "applicationName", "Simple Bar Management Default");
	}

	public final void whenRegisterFormViewIsRequested_thenNoExceptions() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/view/registerEmployee")
				.accept(MediaType.APPLICATION_FORM_URLENCODED).characterEncoding("UTF-8").secure(true))
				.andExpect(status().isOk()).andReturn();

		ModelAndView modelAndView = mvcResult.getModelAndView();

		assertViewName(modelAndView, "registerEmployee");
	}

}
