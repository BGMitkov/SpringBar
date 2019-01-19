package bar.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bar.controller.UserController;

@Deprecated
public class SimpleFilter implements Filter  {
	
	@Autowired
	UserController userController;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Remote Host:" + request.getRemoteHost());
		System.out.println("Remote Address:" + request.getRemoteAddr());
		System.out.println("Local Address:" + request.getLocalAddr());
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		System.out.println("Path info:" + httpServletRequest.getPathInfo());
		chain.doFilter(request, response);
	}

}
