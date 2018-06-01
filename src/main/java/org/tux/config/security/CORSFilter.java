package org.tux.config.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CORSFilter implements Filter {
	
	//private Logger logger = Logger.getLogger(CORSFilter.class);

	// This is to be replaced with a list of domains allowed to access the server
	private final List<String> allowedOrigins;
	
	public CORSFilter(List<String> allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	@Override
	public void destroy() {
		// dostroy filter
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		// Lets make sure that we are working with HTTP (that is, against HttpServletRequest and HttpServletResponse objects)
		if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;

			// Access-Control-Allow-Origin
			String origin = request.getHeader("Origin");
			
			//logger.info(origin);
			response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : origin);
			response.setHeader("Vary", "Origin");

			// Access-Control-Max-Age
			response.setHeader("Access-Control-Max-Age", "3600");

			// Access-Control-Allow-Credentials
			response.setHeader("Access-Control-Allow-Credentials", "true");

			// Access-Control-Allow-Methods
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");

			// Access-Control-Allow-Headers
			response.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, data, method, url, Content-Type, Accept, Access-Control-Allow-Origin,Cache-Control, " + CSRF.REQUEST_HEADER_NAME);
		}

		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) {
		// init filter
	}
}