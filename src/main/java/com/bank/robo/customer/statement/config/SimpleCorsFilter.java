package com.bank.robo.customer.statement.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bank.robo.customer.statement.util.AppConstants;

/**
 * its a CROS filter, which is used to handle the all cross-origin requests
 * 
 * @author mani.kasi
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

	public SimpleCorsFilter() {
		// default constructor
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		response.setHeader("Access-Control-Allow-Origin", AppConstants.CORS_ORIGIN);
		response.setHeader("Access-Control-Allow-Methods", AppConstants.CORS_METHODS);
		response.setHeader("Access-Control-Max-Age", AppConstants.CORS_MAX_AGE);
		response.setHeader("Access-Control-Allow-Headers", AppConstants.CORS_ALLOW_HEADERS);

		if (AppConstants.CORS_METHOD_OPTIONS.equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {
		// init
	}

	@Override
	public void destroy() {
		// destroy
	}
}
