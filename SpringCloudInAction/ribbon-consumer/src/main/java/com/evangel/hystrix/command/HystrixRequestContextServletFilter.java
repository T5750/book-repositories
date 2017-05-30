package com.evangel.hystrix.command;

import java.io.IOException;

import javax.servlet.*;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * Initializes the {@link HystrixRequestContext} at the beginning of each HTTP
 * request and then cleans it up at the end.
 * <p>
 * Install by adding the following lines to your project web.xml:
 * <p>
 *
 * <pre>
 * {@code
 *   <filter>
 *     <display-name>HystrixRequestContextServletFilter</display-name>
 *     <filter-name>HystrixRequestContextServletFilter</filter-name>
 *     <filter-class>com.netflix.hystrix.contrib.requestservlet.HystrixRequestContextServletFilter</filter-class>
 *   </filter>
 *   <filter-mapping>
 *     <filter-name>HystrixRequestContextServletFilter</filter-name>
 *     <url-pattern>/*</url-pattern>
 *   </filter-mapping>
 * }
 * </pre>
 */
public class HystrixRequestContextServletFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HystrixRequestContext context = HystrixRequestContext
				.initializeContext();
		try {
			chain.doFilter(request, response);
		} finally {
			context.shutdown();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}