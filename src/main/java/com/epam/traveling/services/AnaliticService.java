/**
 * 
 */
package com.epam.traveling.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.traveling.entities.Analitic;
import com.epam.traveling.entities.Country;
import com.epam.traveling.entities.Hotel;
import com.epam.traveling.entities.User;
import com.epam.traveling.repositories.AnaliticRepository;
import com.epam.traveling.repositories.UserRepository;

@Controller
@Service
@Transactional
public class AnaliticService {
	@Autowired
	private AnaliticRepository analiticRepository;

	public List<Analitic> findAllUsers() {
		return analiticRepository.findAll();
	}
	
	@Transactional
	public void save(Analitic analitic) {
		analiticRepository.save(analitic);
	}
	
	@Transactional
	public Analitic findByEmailAndPassword(String email, String password) {
		return analiticRepository.findByEmailAndPassword(email, password);
	}
	
	@Transactional
	public Analitic findByEmail(String email) {
		return analiticRepository.findByEmail(email);
	}
	
	public Analitic find(Integer id) {
		return analiticRepository.findOne(id);
	}

	/*
	 * public List<User1> findAllUsers() { return userRepository.findAll(); }
	 */

	/*
	 * public void addUser(User1 user1) { userRepository.save(user1); }
	 */

}
