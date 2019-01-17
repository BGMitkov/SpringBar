package bar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bar.dto.UserDTO;
import bar.model.EmployeeRole;
import bar.model.Permission;
import bar.model.User;
import bar.repository.PermissionDAO;
import bar.repository.UserDAO;

/**
 * Class implementing the security functionality for the application.
 * 
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
	 * permission to access the service mapped by the given uri.
	 * 
	 * @param uri mapped to a service
	 * @return true if access is permitted, false otherwise
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

	/**
	 * Permits the given Employee Roles to access the service mapped to the given
	 * URI.
	 * 
	 * @param uri
	 * @param employeeRoles
	 * @return The permission associated to the given URI
	 */
	public Permission setPermissions(String uri, EmployeeRole... employeeRoles) {
		Permission permission = permissionDAO.findByUri(uri);
		if (permission == null) {
			permission = new Permission(uri, employeeRoles);
		} else {
			for (EmployeeRole employeeRole : employeeRoles) {
				permission.addEmployeeRole(employeeRole);
			}
		}

		return permissionDAO.save(permission);
	}

	// TODO make a remove permissions service

	public String getUserName() {
		if (!userContext.isAuthenticated()) {
			return "Sign in";
		}

		return userContext.getName();
	}

	/**
	 * Return the currently signed in user for the session.
	 * 
	 * @return the user or null if no user is signed in
	 */
	public User getUser() {
		return userContext.getUser();
	}

	/**
	 * Authenticates the user with the given data
	 * 
	 * @param userDTO containing the name and password. Not null.
	 * @return true if authentication is successful or false otherwise.
	 */
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
	 * Registers the given user. Name and email have to be unique.
	 * 
	 * @param user the object containing the submitted user data. Not null.
	 * @return the name of the view
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

	public boolean signout() {
		userContext.setUser(null);
		return userContext.isAuthenticated();
	}
}
