package com.r2comms.r2copy.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.r2comms.r2copy.security.filter.ApiCheckFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private AuthenticationFailureHandler CustomAuthenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.csrf().disable();		
		http
			.authorizeRequests()			
				.antMatchers(											
						"/admin/**").permitAll()
				.antMatchers("/", "/job/**", "/node/**", "/user/**").hasRole("ADMIN")						
				.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/login")
					 .defaultSuccessUrl("/")
				.successHandler(successHandler())
				.failureHandler(CustomAuthenticationFailureHandler)
					.permitAll()
					.and()
				.logout()			  
			      	.invalidateHttpSession(true).deleteCookies("JSESSIONID")
					.permitAll()
					.and()

			    .rememberMe().tokenValiditySeconds(7776000).key("r2commsapp1")   // 60 * 60 * 24 * 7 : 604800,  60*60*24*30일 = 2592000   60*60*24*30일*3개월 = 7776000
			    .rememberMeParameter("remember-me");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
		
	  throws Exception {
	    auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      // .passwordEncoder(passwordEncoder())
	      .usersByUsernameQuery("select login_id, password, is_active from user where login_id = ?")
	      .authoritiesByUsernameQuery("select u.login_id, r.role_name from user u inner join role r on u.role_id = r.role_id where u.login_id = ? ");
	}	
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler();
	}

	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new CustomAuthenticationFailureHandler();
	}
	
	@Bean
	public ApiCheckFilter apiCheckFilter() {
		return new ApiCheckFilter("/api/v1/**");
	}
    
}