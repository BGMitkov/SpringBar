package bar.services;

import org.springframework.stereotype.Component;

import bar.model.Role;
import bar.model.User;

@Component
public class UserContext {
	private User user;

	public boolean isAuthenticated() {
		return user != null;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public boolean isUserInRole(Role... roles) {
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
