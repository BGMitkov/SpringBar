package bar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import bar.model.EmployeeRole;
import bar.model.User;

@Component
@SessionScope
public class UserContext {
	private static final Logger logger = LoggerFactory.getLogger(UserContext.class);
	private User user;

	public boolean isAuthenticated() {
		logger.info("Authentication check");
		return user != null;
	}

	public void setUser(User user) {
		logger.info("User authenticated");
		this.user = user;
	}

	public boolean isUserInRole(EmployeeRole... roles) {
		logger.info("User permission check");
		if (user != null) {
			return false;
		}

		for (EmployeeRole role : roles) {
			if (user.getEmployeeRole().equals(role)) {
				return true;
			}
		}

		return false;
	}

	public String getName() {
		String name = user != null ? user.getName() : "No user signed";
		logger.info("getName(): {}", name);
		return name;
	}

	public User getUser() {
		logger.info("getUser(): {}", user.getName());
		return user;
	}

	public EmployeeRole getEmployeeRole() {
		return user.getEmployeeRole();
	}
}
