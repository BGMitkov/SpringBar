package bar.services;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import bar.SpringBarApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
@WebAppConfiguration
public class UserServiceTest extends AbstractTest {

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
	public final void whenEntityIsCreated_thenNoExceptions() throws Exception {
//		User testUser = new User("regtest", passwordEncoder.encode("regtest"), "regtest@test.test", Role.MANAGER,
//		        new Date(0));
//		testUser.setId(5l);
		String uri = "/registerUser";
		
		MvcResult mvcResult = this.mockMvc.perform(post("/registerUser")
				.contentType("application/x-www-form-urlencoded").param("name", "regtest").param("password", "regtest")
				.param("email", "regtest@test.test").param("role", "MANAGER").param("birthDate", "02:00:00 1/1/1970"))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String viewName = mvcResult.getModelAndView().getViewName();
		assertEquals("registeredUser", viewName);
		String forwardedUrl = mvcResult.getResponse().getForwardedUrl();
		assertEquals("/WEB-INF/jsp/registeredUser.jsp", forwardedUrl);
	}

}
