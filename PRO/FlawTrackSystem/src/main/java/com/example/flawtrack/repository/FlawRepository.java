package com.example.flawtrack.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flawtrack.model.Flaw;
import com.example.flawtrack.model.User;

@Repository
public interface FlawRepository extends JpaRepository<Flaw, Long> 
{
	List<Flaw> findByLoggedBy(String loggedBy);

	List<Flaw> findByUser(User user);

	List<Flaw> getFlawsByUserId(Integer userId);

	List<Flaw> findByUserId(Long userId);


	
}
