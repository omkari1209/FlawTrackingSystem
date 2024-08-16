package com.example.flawtrack.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flawtrack.service.UserService;

@RestController
@RequestMapping
public class PasswordResetController 
{
	@Autowired
	private UserService userService;
	
	@PostMapping("/reset-password")
	public ResponseEntity<Map<String,Object>> resetPaasword(@RequestBody Map<String,String> request)
	{
		String email=request.get("username");
		String newPassword=request.get("newPassword");
		
		boolean success=userService.resetPassword(email, newPassword);
		Map<String,Object> response=new HashMap<>();
		response.put("success", success);
		return ResponseEntity.ok(response);
	}

}
