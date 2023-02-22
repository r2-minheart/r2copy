package com.r2comms.rclone.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;


import lombok.extern.log4j.Log4j2;
// import net.minidev.json.JSONObject;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {

	
	public ApiCheckFilter(String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getRequestURI().startsWith("/admin") == false 
				&& request.getRequestURI().endsWith(".css") == false 
				&& request.getRequestURI().endsWith(".js") == false) {
			
			log.info("REQUESTURI: " + request.getRequestURI());		
		}
				
		filterChain.doFilter(request, response);

	}
}
