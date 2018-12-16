package bar.service;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.aspectj.lang.reflect.PerClauseKind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bar.model.Role;

@Service
public class SecurityService {
	private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
	@Autowired
	private UserContext userContext;
	private Map<String, Set<Role>> permissions;

	public SecurityService() {
		permissions = new HashMap<>();
	}

	public boolean checkPermission(String methodMapping) {
		Set<Role> set = permissions.get(methodMapping);
		if (set == null) {
			return true;
		}
		if (set.contains(userContext.getUser().getRole())) {
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
}
