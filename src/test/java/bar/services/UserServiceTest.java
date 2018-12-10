package bar.services;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import bar.model.Role;
import bar.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/SpringBar-servlet.xml", "/Beans.xml" })
@WebAppConfiguration
public class UserServiceTest {

	MockHttpSession session;

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).apply(sharedHttpSession()).build();
		this.session = new MockHttpSession();
	}

	@Test
	public final void whenContextIsBootstrapped_thenNoExceptions() {
	}

	@Test
	public final void whenEntityIsCreated_thenNoExceptions() throws Exception {
//		User testUser = new User("regtest", passwordEncoder.encode("regtest"), "regtest@test.test", Role.MANAGER,
//		        new Date(0));
//		testUser.setId(5l);
		this.mockMvc
		        .perform(post("/registerUser").contentType("application/x-www-form-urlencoded").param("name", "regtest")
		                .param("password", "regtest").param("email", "regtest@test.test").param("role", "MANAGER")
		                .param("birthDate", "02:00:00 1/1/1970"))
		        .andExpect(status().isOk()).andExpect(view().name("registeredUser"))
		        .andExpect(forwardedUrl("/WEB-INF/jsp/registeredUser.jsp"));
	}
	
}
