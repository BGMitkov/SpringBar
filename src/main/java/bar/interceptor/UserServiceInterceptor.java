package bar.interceptor;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import bar.service.SecurityService;
import bar.service.UserController;

@Component
public class UserServiceInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceInterceptor.class);
	@Autowired
	private SecurityService securityService;

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
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("Request and Response is completed");
	}
}
