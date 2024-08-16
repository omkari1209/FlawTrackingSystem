package com.example.flawtrack.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flawtrack.config.AdminConfig;
import com.example.flawtrack.model.Admin;
import com.example.flawtrack.model.Flaw;
import com.example.flawtrack.model.User;
import com.example.flawtrack.service.AdminService;
import com.example.flawtrack.service.FlawService;
import com.example.flawtrack.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FlawService flawService;
	
	
	@Autowired
	 private AdminConfig adminConfig;
	
	@PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin admin, HttpSession session) {
        if (admin.getUsername().equals(adminConfig.getDefaultUsername()) && admin.getPassword().equals(adminConfig.getDefaultPassword())) {
            session.setAttribute("adminLoggedIn", true);
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }
	
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @PostMapping
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.addAdmin(admin);
        return ResponseEntity.ok(savedAdmin);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
    

    @GetMapping("/flaws")
    public ResponseEntity<List<Flaw>> getAllFlaws() {
        List<Flaw> flaws = flawService.getAllFlaws();
        return ResponseEntity.ok(flaws);
    }
    
  

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        Admin updatedAdmin = adminService.updateAdmin(id, admin);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/flaws/{id}")
    public ResponseEntity<Flaw> updateFlaw(@PathVariable Long id, @RequestBody Flaw flaw) {
        Flaw updatedFlaw = flawService.updateFlaw(id, flaw);
        return ResponseEntity.ok(updatedFlaw);
    }

    @DeleteMapping("/flaws/{id}")
    public ResponseEntity<Void> deleteFlaw(@PathVariable Long id) {
        flawService.deleteFlaw(id);
        return ResponseEntity.noContent().build();
    }
}
