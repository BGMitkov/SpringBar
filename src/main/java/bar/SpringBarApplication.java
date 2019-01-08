package bar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import bar.spring.SpringContextConfig;
import bar.spring.WebMvcConfig;

@SpringBootApplication
public class SpringBarApplication extends SpringBootServletInitializer {
	private static final Logger barLogger = LoggerFactory.getLogger(SpringBarApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBarApplication.class, WebMvcConfig.class);
	}

	public static void main(String[] args) {
		barLogger.info("Application is about to run.");
		SpringApplication.run(SpringBarApplication.class, args);
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

//	@Bean
//	public ServletRegistrationBean<DispatcherServlet> dispatcherServletRegistration() {
//		ServletRegistrationBean<DispatcherServlet> registrationBean = new ServletRegistrationBean<DispatcherServlet>(
//				dispatcherServlet(), "/");
//	}
}
