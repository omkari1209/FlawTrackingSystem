package com.example.flawtrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.flawtrack.model.Flaw;
import com.example.flawtrack.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	boolean existsByemail(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.password=:password WHERE u.email=:email")
	void updatePassword(@Param("email")String email,@Param("password")String password);
	User findUserById(Long userId);
	

}
