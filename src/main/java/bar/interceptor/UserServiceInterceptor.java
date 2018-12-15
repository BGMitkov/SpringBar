package bar.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import bar.service.UserController;

@Component
public class UserServiceInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Pre handle method is Calling on handler: " + handler.getClass() );
		HandlerMethod method = (HandlerMethod) handler;
		logger.info("Bean of handler method: " + method.getBean());
		UserController controller = (UserController) method.getBean();
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
