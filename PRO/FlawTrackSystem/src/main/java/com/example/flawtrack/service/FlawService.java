package com.example.flawtrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.flawtrack.model.Flaw;
import com.example.flawtrack.model.User;
import com.example.flawtrack.repository.FlawRepository;
import com.example.flawtrack.repository.UserRepository;

@Service
public class FlawService {

    @Autowired
    private FlawRepository flawRepository;
    
    @Autowired
    private UserRepository userRepository;

    public List<Flaw> getAllFlaws() {
        return flawRepository.findAll();
    }

    public Flaw getFlawById(Long id) {
        return flawRepository.findById(id).orElse(null);
    }
    
//    public Flaw createFlaw(Flaw flaw) {
//        if (userId == null) {
//            throw new IllegalArgumentException("User ID must not be null");
//        }
//        
//        User user = userRepository.findById(userId)
//                            .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
//        flaw.setUser(user);  // Assuming Flaw entity has a setUser method
//        return flawRepository.save(flaw);
//    }
    
    public Flaw createFlaw(Flaw flaw) {
        // Ensure that the Flaw object is not null
        if (flaw == null) {
            throw new IllegalArgumentException("Flaw data must not be null");
        }

        // Retrieve the User associated with the Flaw
        User user = flaw.getUser();
        if (user == null) {
            throw new IllegalArgumentException("User must be associated with the Flaw");
        }

        // Ensure that the User exists
        User existingUser = userRepository.findById(user.getId())
                                 .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + user.getId()));

        // Set the User on the Flaw object
        flaw.setUser(existingUser);

        // Save the Flaw to the repository
        return flawRepository.save(flaw);
    }

    public void deleteFlaw(Long id) {
        flawRepository.deleteById(id);
    }
    
    public List<Flaw> getFlawsByUser(String email) {
        return flawRepository.findByLoggedBy(email);
    }

    public List<Flaw> getFlawsByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return flawRepository.findByUserId(userId);
    }
    
    public Flaw updateFlaw(Long id, Flaw updatedFlaw) {
        Flaw flaw = flawRepository.findById(id).orElseThrow(() -> new RuntimeException("Flaw not found"));
        flaw.setApplication(updatedFlaw.getApplication());
        flaw.setModule(updatedFlaw.getModule());
        flaw.setDescription(updatedFlaw.getDescription());
        flaw.setAssignedTo(updatedFlaw.getAssignedTo());
        flaw.setStatus(updatedFlaw.getStatus());
        return flawRepository.save(flaw);
    }
	
}