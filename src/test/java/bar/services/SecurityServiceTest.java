package bar.services;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import bar.SpringBarApplication;
import bar.service.SecurityService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
@WebAppConfiguration
public class SecurityServiceTest extends AbstractTest {

	MockHttpSession httpSession;

	private SecurityService securityService;

	@Before
	@Override
	public void setup() {
		super.setup();

	}

	
}
