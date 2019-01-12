package bar.spring;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import bar.interceptor.UserServiceInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

//	@Bean
//	public UrlBasedViewResolver getUrlBasedViewResolver() {
//		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//		resolver.setPrefix("/WEB-INF/jsp/");
//		resolver.setSuffix(".jsp");
//		resolver.view
//		return resolver;
//	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Autowired
	private UserServiceInterceptor userServiceInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("Add interceptor UserServiceInterceptor");
		registry.addInterceptor(userServiceInterceptor).addPathPatterns("/**").excludePathPatterns("/resources/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
				.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS)).resourceChain(false)
				.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	}

}
