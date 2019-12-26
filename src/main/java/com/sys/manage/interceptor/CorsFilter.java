package com.sys.manage.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求过滤器
 */
public class CorsFilter implements Filter {

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
			throws IOException, ServletException {

		HttpServletResponse servletResponse = (HttpServletResponse) response;
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "5000");
		servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, " +
				"Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Authorization, Token");

		arg2.doFilter(request, response);
	}



	@Override
	public void destroy() {

	}

}
