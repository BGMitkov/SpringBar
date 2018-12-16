package bar.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bar.model.Role;
import bar.service.SecurityService;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityService getSecurityService() {
		SecurityService securityService = new SecurityService();
		securityService.setPermissions("registerEmployeeForm", Role.MANAGER);
		securityService.setPermissions("registerEmployeeSubmit", Role.MANAGER);

		return securityService;
	}
}
