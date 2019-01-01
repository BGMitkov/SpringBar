package bar.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bar.service.SecurityService;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityService getSecurityService() {
		SecurityService securityService = new SecurityService();
//		securityService.setPermissions("/SpringBar/view/registerEmployee", Role.MANAGER);
//		securityService.setPermissions("/SpringBar/registerEmployeeSubmit", Role.MANAGER);
//		securityService.setPermissions("/SpringBar/order", Role.MANAGER, Role.SERVER);
//		securityService.setPermissions("/SpringBar/getWaitingOrders", Role.MANAGER, Role.BARTENDER, Role.SERVER);
//		securityService.setPermissions("/SpringBar/getAcceptedOrders", Role.MANAGER, Role.BARTENDER);
//		securityService.setPermissions("/SpringBar/acceptOrder", Role.MANAGER, Role.BARTENDER);
//		securityService.setPermissions("/SpringBar/printBill", Role.MANAGER, Role.BARTENDER);
//		securityService.setPermissions("/SpringBar/view/addItemForm", Role.MANAGER);
		return securityService;
	}
}
