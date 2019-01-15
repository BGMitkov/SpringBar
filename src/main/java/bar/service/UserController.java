package bar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bar.annotation.ValidationSequence;
import bar.dao.EmployeeRoleDAO;
import bar.dto.UserDTO;
import bar.model.EmployeeRole;
import bar.model.User;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private SecurityService securityService;
	@Autowired
	private EmployeeRoleDAO employeeRoleDAO;

	@GetMapping("/view/registerEmployee")
	public String registerEmployeeForm(Model model) {
		logger.info("Request for register employee form.");
		model.addAttribute("user", new User());
		return "registerEmployee";
	}

	@RequestMapping(value = "/registerEmployeeSubmit", method = RequestMethod.POST)
	public String registerUser(@Validated(ValidationSequence.class) @ModelAttribute("user") UserDTO userDTO,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "registerEmployee";
		}
		User user = convertToUser(userDTO);
		String view = securityService.register(user);
		userDTO.setPassword(null);
		model.addAttribute(userDTO);
		return view;
	}

	@GetMapping("/view/signIn")
	public ModelAndView loginForm() {
		logger.info("Request for sign in form");
		return new ModelAndView("signIn", "command", new UserDTO());
	}

	@PostMapping("/signInSubmit")
	public String login(@ModelAttribute("user") UserDTO user) {
		logger.info("Request for sign in submit");
		return securityService.authenticate(user) ? "index" : "signIn";
	}

	@GetMapping("/signOut")
	public String signout() {
		logger.info("Request for signOut by: {}", securityService.getUserName());
		securityService.signout();
		return "redirect:/view/signIn";
	}

	@GetMapping("/")
	public String index(Model model) {
		logger.info("Request for index page");
		if (!securityService.checkPermission("/SpringBar/")) {
			return "redirect:/view/signIn";
		}
		return "index";
	}

	private User convertToUser(UserDTO userDTO) {
		EmployeeRole employeeRole = employeeRoleDAO.findByName(userDTO.getEmployeeRole());
		return new User(userDTO.getName(), userDTO.getPassword(), userDTO.getEmail(), employeeRole,
				userDTO.getBirthDate());
	}
}
