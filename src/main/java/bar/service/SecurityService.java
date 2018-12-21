package bar.service;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bar.dao.UserDAO;
import bar.dto.UserDTO;
import bar.model.Role;
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
	private Map<String, Set<Role>> permissions;

	public SecurityService() {
		permissions = new HashMap<>();
	}

	public boolean checkPermission(String methodMapping) {
		Set<Role> set = permissions.get(methodMapping);
		if (set == null) {
			return true;
		}
		if (set.contains(userContext.getRole())) {
			return true;
		}

		return false;
	}

	public void setPermissions(String methodName, Role... permissions) {
		Set<Role> setPermissions = EnumSet.noneOf(Role.class);
		for (int i = 0; i < permissions.length; i++) {
			setPermissions.add(permissions[i]);
		}
		this.permissions.put(methodName, setPermissions);
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
		User storedUser = userDAO.findByProperty(User.PROPERTY_NAME, user.getName());

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

		return "registeredUser";
	}

	public void signout() {
		userContext.setUser(null);
	}
}
