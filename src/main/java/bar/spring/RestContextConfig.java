package bar.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import bar.services.UserController;

@ComponentScan
public class RestContextConfig {
	
	@Bean
	public UserController getUserController() {
		return new UserController();
	}
	
	
}
