package bar.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import bar.services.UserController;

@SpringBootApplication
public class SpringBarApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBarApplication.class, SpringContextConfig.class, RestContextConfig.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBarApplication.class, args);
	}
}
