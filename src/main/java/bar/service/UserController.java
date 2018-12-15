package bar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bar.dao.UserDAO;
import bar.dto.UserDTO;
import bar.model.User;

@Controller
public class UserController {
	private static final String APPLICATION_NAME = "applicationName";

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// TODO check whether it is possible to insert preconfigured Model.
	@Value("${spring.application.name:Application}")
	private String applicationName;
	// TODO decide whether this is needed.
	// AOP is to be implemented for user authentication
	@Autowired
	private UserContext userContext;
	// TODO implement UserDAO
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/registerEmployee")
	public String registerEmployeeForm(Model model) {
		logger.info("Request for register employee form.");
		model.addAttribute(APPLICATION_NAME, applicationName);
		return "registerEmployee";
	}

	@RequestMapping(value = "/registerEmployeeSubmit", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user, ModelMap model) {
		logger.info("Request for register employee submit");
		// if(!userContext.isUserInRole(Role.MANAGER)) {
		// return "unauthorized";
		// }

		User userWithName = userDAO.findByProperty(User.PROPERTY_NAME, user.getName());
		User userWithEmail = userDAO.findByProperty(User.PROPERTY_EMAIL, user.getEmail());

		if (userWithName != null) {
			return "nameConflict";
		}
		if (userWithEmail != null) {
			return "emailConflict";
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.create(user);

		user.setPassword(null);
		model.addAttribute(user);
		model.addAttribute(APPLICATION_NAME, applicationName);
		return "registeredUser";
	}

	@GetMapping("/login")
	public ModelAndView loginForm() {
		logger.info("Request for login form");
		return new ModelAndView("login", "command", new UserDTO());
	}

	@PostMapping("/loginSubmit")
	public String login(@ModelAttribute("user") UserDTO user, Model model) {
		logger.info("Request for login submit");
		User storedUser = userDAO.findByProperty(User.PROPERTY_NAME, user.getName());

		if (storedUser == null || !passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
			return "invalidCredentials";
		}

		userContext.setUser(storedUser);
		model.addAttribute(User.PROPERTY_NAME, storedUser.getName());
		model.addAttribute(APPLICATION_NAME, applicationName);
		return "index";
	}

	@GetMapping("/logout")
	public String logout() {
		logger.info("Request for logout");
//		userContext.setUser(null);
		return "redirect:login";
	}
}
