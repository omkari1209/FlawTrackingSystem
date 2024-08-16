package com.example.flawtrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.session.HeaderWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

@SpringBootApplication
public class FlawTrackSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FlawTrackSystemApplication.class, args);
	}
	
	 @Bean
	    public WebSessionIdResolver webSessionIdResolver() {
	        HeaderWebSessionIdResolver resolver = new HeaderWebSessionIdResolver();
	        resolver.setHeaderName("X-Auth-Token"); // Custom header name for session ID
	        return resolver;
	    }

}
