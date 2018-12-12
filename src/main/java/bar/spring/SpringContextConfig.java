package bar.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import bar.dao.UserDAO;
import bar.mappers.UserMapper;
import bar.services.UserContext;

@ComponentScan
public class SpringContextConfig {

	@Bean
	public UserContext getUserContext() {
		return new UserContext();
	}

	@Bean
	public UserDAO getUserDAO() {
		return new UserDAO();
	}

	@Bean
	public UserMapper getUserMapper() {
		return new UserMapper();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
