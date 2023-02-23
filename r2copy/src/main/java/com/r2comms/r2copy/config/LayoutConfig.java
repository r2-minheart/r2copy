package com.r2comms.r2copy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class LayoutConfig {
	
	// thymeleaf layout
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
	
}
