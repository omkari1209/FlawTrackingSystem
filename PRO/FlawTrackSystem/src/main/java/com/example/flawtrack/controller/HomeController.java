package com.example.flawtrack.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class HomeController 
{
	 @GetMapping("/")
	    public ResponseEntity<String> viewHome() throws IOException {
	        ClassPathResource htmlFile = new ClassPathResource("templates/layout.html");
	        String htmlContent = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.TEXT_HTML);
	        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
	    }
	 
	  @GetMapping("/admin")
	    public ResponseEntity<String> viewAdmin() throws IOException {
	        ClassPathResource htmlFile = new ClassPathResource("templates/admin.html");
	        String htmlContent = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.TEXT_HTML);
	        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
	    }

	    @GetMapping("/register")
	    public ResponseEntity<String> viewRegister() throws IOException {
	        ClassPathResource htmlFile = new ClassPathResource("templates/register.html");
	        String htmlContent = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.TEXT_HTML);
	        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
	    }

	    @GetMapping("/login")
	    public ResponseEntity<String> viewLogin() throws IOException {
	        ClassPathResource htmlFile = new ClassPathResource("templates/login.html");
	        String htmlContent = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.TEXT_HTML);
	        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
	    }
	    
	    @GetMapping("/home")
	    public ResponseEntity<String> viewHome1() throws IOException {
	    	 ClassPathResource htmlFile = new ClassPathResource("templates/home.html");
		        String htmlContent = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);

		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.TEXT_HTML);
		        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
	    }
	    
	    
	    @GetMapping("/about")
	    public ResponseEntity<String> viewAbout() throws IOException {
	        ClassPathResource htmlFile = new ClassPathResource("templates/about.html");
	        String htmlContent = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.TEXT_HTML);
	        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
	    }
	    
	    @GetMapping("/contact")
	    public ResponseEntity<String> viewContact() throws IOException {
	        ClassPathResource htmlFile = new ClassPathResource("templates/contact.html");
	        String htmlContent = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.TEXT_HTML);
	        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
	    }
	    
	    @GetMapping("/status")
	    public ResponseEntity<String> viewStatus() throws IOException {
	        ClassPathResource htmlFile = new ClassPathResource("templates/status.html");
	        String htmlContent = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.TEXT_HTML);
	        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
	    }
	    
	}


