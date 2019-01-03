package bar.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

//	@Autowired
//	private UserServiceInterceptor userServiceInterceptor;
//
//	@Bean
//	public UserServiceInterceptor getUserServiceInterceptor() {
//		return new UserServiceInterceptor();
//	}
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		logger.info("Add interceptor UserServiceInterceptor");
//		registry.addInterceptor(new UserServiceInterceptor()).addPathPatterns("/**")
//				.excludePathPatterns("/resources/**");
//	}

//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		configurer.enable();
//	}

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(3600)
//				.resourceChain(true).addResolver(new PathResourceResolver());
//	}

}
