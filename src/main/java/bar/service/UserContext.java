package bar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import bar.model.Role;
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

	public User getUser() {
		logger.info("User Retrieval");
		return user;
	}

	public void setUser(User user) {
		logger.info("User authenticated");
		this.user = user;
	}

	public boolean isUserInRole(Role... roles) {
		logger.info("User permission check");
		if (user != null) {
			return false;
		}

		for (Role role : roles) {
			if (user.getRole().equals(role)) {
				return true;
			}
		}

		return false;
	}

}
