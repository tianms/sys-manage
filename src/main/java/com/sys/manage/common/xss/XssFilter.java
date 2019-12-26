package com.sys.manage.common.xss;

import javax.servlet.*;
import java.io.IOException;

/**
 * XSS过滤
 */
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}