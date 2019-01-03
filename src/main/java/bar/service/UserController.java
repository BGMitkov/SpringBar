package bar.service;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
//	@Autowired
//	private ConversionService ConversionService;
//
//	@InitBinder
//	protected void initBinder(ServletRequestDataBinder binder) {
//		binder.setConversionService(ConversionService);
//	}

	@GetMapping("/view/registerEmployee")
	public String registerEmployeeForm(Model model) {
		logger.info("Request for register employee form.");
		model.addAttribute("user", new User());
		return "registerEmployee";
	}

	@RequestMapping(value = "/registerEmployeeSubmit", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") @Valid UserDTO userDTO, ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			return "registerEmployee";
		}
		User user = convertToUser(userDTO);
		String view = securityService.register(user);
		userDTO.setPassword(null);
		model.addAttribute(userDTO);
		return view;
	}

	@GetMapping("/view/login")
	public ModelAndView loginForm() {
		logger.info("Request for login form");
		return new ModelAndView("login", "command", new UserDTO());
	}

	@PostMapping("/loginSubmit")
	public String login(@ModelAttribute("user") UserDTO user) {
		logger.info("Request for login submit");
		return securityService.authenticate(user) ? "index" : "invalidCredentials";
	}

	@GetMapping("/signout")
	public String signout() {
		logger.info("Request for signout by: {}", securityService.getUserName());
		securityService.signout();
		return "redirect:/login";
	}

	private User convertToUser(UserDTO userDTO) {
		EmployeeRole employeeRole = employeeRoleDAO.findByName(userDTO.getEmployeeRole());
		return new User(userDTO.getName(), userDTO.getPassword(), userDTO.getEmail(), employeeRole,
				userDTO.getBirthDate());
	}
}
