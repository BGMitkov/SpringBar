package bar.services;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bar.model.Role;
import bar.model.User;

@Controller
public class UserController {
	
	//TODO decide whether this is needed. 
	//AOP is to be implemented for user authentication
	private UserContext userContext;
	//TODO implement UserDAO
	//private UserDAO userDAO;
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("registerUser")User user) {
		if(!userContext.isUserInRole(Role.MANAGER)) {
			return "unauthorized";
		}
		
		/*boolean userNameExists = userDAO.userNameExists(user.getUsername());
		boolean emailExists = userDAO.emailExists(user.getEmail());
		
		if(userNameExists) {
			return "usernameConflict";
		}
		if(emailExists) {
			return "emailConflict";
		}*/
		
		//TODO hash password
		//userDAO.addUser(user);
		
		//TODO make registeredUser.jsp
		return "registeredUser";
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public String login(@ModelAttribute("user")User user, Model model) {
		/*User validUser = userDAO.validateUserCredentials(user.getUsername(), user.getPassword());
		if(validUser == null) {
			return "invalid";
		}
		
		userContext.setUser(validUser);
		model.addAttribute("userName", validUser.getUsername());
		*/return "main";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		userContext.setUser(null);
		return "main";
	}
}
