package bar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bar.dao.PermissionDAO;
import bar.dao.UserDAO;
import bar.dto.UserDTO;
import bar.model.EmployeeRole;
import bar.model.Permission;
import bar.model.User;

/**
 * @author bgmitkov
 *
 */
@Service
public class SecurityService {
	private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
	@Autowired
	private UserContext userContext;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private PermissionDAO permissionDAO;

//	private Map<String, Set<EmployeeRole>> permissions;

	public SecurityService() {
//		permissions = new HashMap<>();
	}

	/**
	 * Given a uri returns true or false on whether the currently signed in user has
	 * permission to access the service mapped by the given uri. If there is not
	 * permission object for a given uri then access is not restricted
	 * 
	 * @param uri mapped to a service
	 * @return whether access is permitted
	 */
	public boolean checkPermission(String uri) {
		logger.info("Permission requested by: {} for {}", userContext.getName(), uri);
		if (uri == null) {
			return false;
		}

		Permission permission = permissionDAO.findByUri(uri);

		if (permission == null) {
			return true;
		}

		return userContext.isAuthenticated() && permission.hasAccess(userContext.getEmployeeRole());
	}

	public void setPermissions(String uri, EmployeeRole... employeeRoles) {
//		Set<EmployeeRole> setPermissions = new HashSet<>(Arrays.asList(employeeRoles));
		Permission permission = new Permission(uri, employeeRoles);
		permissionDAO.save(permission);
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

	public boolean authenticate(UserDTO userDTO) {
		logger.info("authenticate(): " + userDTO.getName());

		User storedUser = userDAO.findByName(userDTO.getName());
		if (storedUser != null && bCryptPasswordEncoder.matches(userDTO.getPassword(), storedUser.getPassword())) {
			userContext.setUser(storedUser);
			logger.info("authenticate(): success");
			return true;
		}

		logger.info("authenticate(): failed");
		return false;
	}

	/**
	 * @param user the object containing the submitted user data
	 * @return the name of the view to send to user
	 */
	public String register(User user) {
		logger.info("Request for register user");
		User userWithName = userDAO.findByName(user.getName());
		User userWithEmail = userDAO.findByEmail(user.getEmail());

		if (userWithName != null) {
			return "nameConflict";
		}
		if (userWithEmail != null) {
			return "emailConflict";
		}

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userDAO.save(user);

		return "registeredUser";
	}

	public String getRole() {
		if (userContext.isAuthenticated()) {
			return userContext.getEmployeeRole().getName();
		}

		return "";
	}

	public void signout() {
		userContext.setUser(null);
	}
}
