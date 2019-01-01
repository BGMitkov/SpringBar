package bar.service;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bar.dao.UserDAO;
import bar.dto.UserDTO;
import bar.model.EmployeeRole;
import bar.model.User;

/**
 * @author bgmitkov
 *
 */
public class SecurityService {
	private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
	@Autowired
	private UserContext userContext;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private Map<String, Set<EmployeeRole>> permissions;

	public SecurityService() {
		permissions = new HashMap<>();
	}

	public boolean checkPermission(String uri) {
		Set<EmployeeRole> set = permissions.get(uri);
		if (set == null) {
			return true;
		}

		if (userContext.isAuthenticated() && set.contains(userContext.getEmployeeRole())) {
			return true;
		}

		return false;
	}

	public void setPermissions(String uri, EmployeeRole... permissions) {
		Set<EmployeeRole> setPermissions = new HashSet<>(Arrays.asList(permissions));
		this.permissions.put(uri, setPermissions);
	}

	public String getUserName() {
		if (!userContext.isAuthenticated()) {
			return "Sign in";
		}

		return userContext.getName();
	}

	public User getUser() {
		return userContext.getUser();
	}

	public boolean authenticate(UserDTO user) {
//		User storedUser = userDAO.findByProperty(User.PROPERTY_NAME, user.getName());
		User storedUser = userDAO.findByName(user.getName());
		if (storedUser == null || !passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
			return false;
		}

		userContext.setUser(storedUser);
		return true;
	}

	/**
	 * @param user the object containing the submitted user data
	 * @return the name of the view to send to user
	 */
	public String register(User user) {
		logger.info("Request for register user");
//		User userWithName = userDAO.findByProperty(User.PROPERTY_NAME, user.getName());
		User userWithName = userDAO.findByName(user.getName());
//		User userWithEmail = userDAO.findByProperty(User.PROPERTY_EMAIL, user.getEmail());
		User userWithEmail = userDAO.findByEmail(user.getEmail());

		if (userWithName != null) {
			return "nameConflict";
		}
		if (userWithEmail != null) {
			return "emailConflict";
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.save(user);

		return "registeredUser";
	}

	public void signout() {
		userContext.setUser(null);
	}
}
