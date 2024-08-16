package com.example.flawtrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flawtrack.model.Flaw;
import com.example.flawtrack.model.User;
import com.example.flawtrack.repository.FlawRepository;
import com.example.flawtrack.repository.UserRepository;
import com.example.flawtrack.service.FlawService;
import com.example.flawtrack.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/flaws")
public class FlawController {

    @Autowired
    private FlawService flawService;
    
    @Autowired
    private FlawRepository flawRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private UserService userService;

    @PostMapping
//    public ResponseEntity<?> createFlaw(@RequestBody FlawRequest flawRequest) {
//        if (flawRequest.getUserId() == null) {
//            return ResponseEntity.badRequest().body("User ID is required");
//        }
//        flawService.createFlaw(flawRequest.getFlaw(), flawRequest.getUserId());
//        return ResponseEntity.ok().build();
//    }
    
   
    public ResponseEntity<?> createFlaw(@RequestBody FlawRequest flawRequest) {
        if (flawRequest.getUserId() == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"User ID is required\"}");
        }

        Flaw flaw = flawRequest.getFlaw();
        if (flaw == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"Flaw data is required\"}");
        }

        try {
            User user = userService.findUserById(flawRequest.getUserId());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"User not found\"}");
            }

            flaw.setUser(user);
            flawService.createFlaw(flaw);

            return ResponseEntity.ok("{\"message\": \"Flaw created successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred: " + e.getMessage() + "\"}");
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Flaw>> getAllFlaws() {
        List<Flaw> flaws = flawService.getAllFlaws();
        return new ResponseEntity<>(flaws, HttpStatus.OK);
    }
    
    @GetMapping("/myflaws")
    public ResponseEntity<?> getMyFlaws(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId"); // Cast to Long
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        try {
            List<Flaw> flaws = flawService.getFlawsByUserId(userId);
            return ResponseEntity.ok(flaws);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching flaws");
        }
    }
    
    
    public static class FlawRequest {
        private Flaw flaw;
        private Long userId;

        public Flaw getFlaw() {
            return flaw;
        }

        public void setFlaw(Flaw flaw) {
            this.flaw = flaw;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    }
