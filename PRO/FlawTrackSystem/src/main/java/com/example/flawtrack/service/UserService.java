package com.example.flawtrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flawtrack.model.User;
import com.example.flawtrack.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepository;
	
	public boolean resetPassword(String email, String newPassword)
	{
		if(userRepository.existsByemail(email))
		{
			userRepository.updatePassword(email,newPassword);
			return true;
		}
		return false;
	}
	
	public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	public boolean checkPassword(User user, String password) {
		// You should hash the password and compare with the stored hashed password
        return user != null && user.getPassword().equals(password);
	}

	public User findUserById(Long userId) {
		
		return userRepository.findUserById(userId);
	}
	
	public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setPhoneno(updatedUser.getPhoneno());
        user.setRole(updatedUser.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

	public List<User> findAllUsers() {
		
		return userRepository.findAll();
	}

	public User saveUser(User user) {
		
		return userRepository.save(user);
	}

}
