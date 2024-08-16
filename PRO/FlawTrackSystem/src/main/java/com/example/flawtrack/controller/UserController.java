package com.example.flawtrack.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.flawtrack.model.User;
import com.example.flawtrack.repository.UserRepository;
import com.example.flawtrack.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping
public class UserController 
{
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("message", "Validation failed");
            errors.put("errors", bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            error -> error.getField(),
                            error -> error.getDefaultMessage())
                    ));
            return ResponseEntity.badRequest().body(errors);
        }

        // Save user to database
        userRepo.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
	
//	@PostMapping("/login")
//    public ResponseEntity<Object> loginUser(@RequestBody User loginUser) {
//        // Retrieve user from database based on email (assuming email is unique)
//        User user = userRepo.findByEmail(loginUser.getEmail());
//
//        // Check if user exists and password matches
//        if (user == null || !user.getPassword().equals(loginUser.getPassword())) {
//            // Unauthorized (401) if user not found or password doesn't match
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//        }
//
//        // Login successful
//        return ResponseEntity.ok("Login successful");
//    }
	
//	 @PostMapping("/login")
//	    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> credentials) {
//	        String email = credentials.get("email");
//	        String password = credentials.get("password");
//
//	        User user = userService.findByEmail(email);
//
//	        if (userService.checkPassword(user, password)) {
//	            Map<String, Object> response = new HashMap<>();
//	            response.put("id", user.getId());
//	            response.put("message", "Login successful");
//
//	            return ResponseEntity.ok(response);
//	        } else {
//	            Map<String, Object> response = new HashMap<>();
//	            response.put("error", "Invalid credentials");
//
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//	        }
//	    }
	
	
	
	

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> credentials, HttpSession session) {
	    String email = credentials.get("email");
	    String password = credentials.get("password");

	    User user = userService.findByEmail(email);

	    Map<String, Object> response = new HashMap<>();

	    if (user != null && userService.checkPassword(user, password)) {
	        session.setAttribute("userId", user.getId()); // Set user ID in session
	        response.put("id", user.getId());
	        response.put("message", "Login successful");
	        return ResponseEntity.ok(response);
	    } else {
	        response.put("error", "Invalid credentials");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	    }
	}

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logoutUser(HttpSession session) {
        session.invalidate(); // Invalidate the session
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return ResponseEntity.ok(response);
    }
	
	
	
	@GetMapping("/session")
	public ResponseEntity<Object> getSession(HttpSession session) {
	    String user = (String) session.getAttribute("user");
	    if (user != null) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("user", user);
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
	    }
	}

	
	@GetMapping("/userpage")
    public ModelAndView userPage(HttpSession session) {
        String user = (String) session.getAttribute("user");
        if (user != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/userpage.html");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/userpage.html");
        }
    }
//	
//	@GetMapping("/userstatus")
//    public ModelAndView userStatus(HttpSession session) {
//        String user = (String) session.getAttribute("user");
//        if (user != null) {
//            ModelAndView modelAndView = new ModelAndView();
//            modelAndView.setViewName("redirect:/userstatus.html");
//            return modelAndView;
//        } else {
//            return new ModelAndView("redirect:/userpage.html");
//        }
//    }

//    @PostMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "Logged out successfully";
//    }

}
