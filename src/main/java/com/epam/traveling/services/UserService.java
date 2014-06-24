/**
 * 
 */
package com.epam.traveling.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.traveling.entities.Country;
import com.epam.traveling.entities.Hotel;
import com.epam.traveling.entities.User;
import com.epam.traveling.repositories.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public void addUser(User user) {
		userRepository.save(user);
	}
	
	@Transactional
	public void save(User user) {
		userRepository.save(user);
	}
	
	@Transactional
	public User findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	@Transactional
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	
	public User find(Integer id) {
		return userRepository.findOne(id);
	}

	/*
	 * public List<User1> findAllUsers() { return userRepository.findAll(); }
	 */

	/*
	 * public void addUser(User1 user1) { userRepository.save(user1); }
	 */

}
