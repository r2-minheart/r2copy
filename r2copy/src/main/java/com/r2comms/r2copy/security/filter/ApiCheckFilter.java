package com.r2comms.r2copy.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2comms.r2copy.dto.UserResponseDto;
import com.r2comms.r2copy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;


import lombok.extern.log4j.Log4j2;
// import net.minidev.json.JSONObject;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {

	@Autowired
	UserService userService;


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

		if (request.getSession().getAttribute("login.loginId") == null
				|| request.getSession().getAttribute("login.fullNameEn") == null
				|| request.getSession().getAttribute("login.userId") == null) {
			try {

				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				String username = null;

				if (principal instanceof UserDetails) {
					username = ((UserDetails)principal).getUsername();
				}
				else {
					username = principal.toString();
				}

				if (!username.equals("anonymousUser")) {
					UserResponseDto userDto = userService.getUserByLoginId(username);
					// log.info("REQUESTURI: " + userDto);

					if (userDto != null) {
						request.getSession().setAttribute("login.loginId", username);
						request.getSession().setAttribute("login.fullNameEn", userDto.getFullNameEn());
						request.getSession().setAttribute("login.userId", userDto.getUserId());
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {

		}
				
		filterChain.doFilter(request, response);

	}
}
