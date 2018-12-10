package bar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import bar.dao.UserDAO;
import bar.model.User;

@Controller
public class UserController {

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
		
		return "registerEmployee";
	}
	
	@RequestMapping(value = "/registerEmployeeSubmit", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user, ModelMap model) {
		// if(!userContext.isUserInRole(Role.MANAGER)) {
		// return "unauthorized";
		// }

//		User userWithName = userDAO.findByProperty("name", user.getName());
//		User userWithEmail = userDAO.findByProperty("email", user.getEmail());
//
//		if (userWithName != null) {
//			return "usernameConflict";
//		}
//		if (userWithEmail != null) {
//			return "emailConflict";
//		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.create(user);

		model.addAttribute(user);
		// TODO make registeredUser.jsp

		return "registeredUser";
	}

//	@RequestMapping(value = "/student", method = RequestMethod.POST)
//	public String login(@ModelAttribute("user") User user, Model model) {
//
//		User storedUser = userDAO.findByProperty("name", user.getName());
//
//		if (storedUser == null || !passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
//			return "InvalidCredentials";
//		}
//
//		userContext.setUser(storedUser);
//		model.addAttribute("userName", storedUser.getName());
//		return "main";
//	}
//
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logout() {
//		userContext.setUser(null);
//		return "main";
//	}
}
