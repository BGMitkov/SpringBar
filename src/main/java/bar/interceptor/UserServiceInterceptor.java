package bar.interceptor;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import bar.model.User;
import bar.service.SecurityService;
import bar.service.UserController;

@Component
public class UserServiceInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceInterceptor.class);
	private static final String APPLICATION_NAME = "applicationName";
	@Autowired
	private SecurityService securityService;
	@Value("${spring.application.name:Application}")
	private String applicationName;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod method = (HandlerMethod) handler;
		String name = method.getMethod().getName();
		logger.info("Method name {}", name);
		RequestMapping mapping = (RequestMapping) method.getMethodAnnotation(RequestMapping.class);

		String string = Arrays.toString(mapping.value());
		logger.info("Request to {}", string.substring(1, string.length() - 1));
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("Post handle method is Calling");
		modelAndView.addObject(APPLICATION_NAME, applicationName);
		modelAndView.addObject(User.PROPERTY_NAME, securityService.getUserName());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("Request and Response is completed");
	}
}
